import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class FileSearch {
    /**
     * Главный метод программы.
     * Принимает строку для поиска в качестве аргумента командной строки.
     * Получает список имен файлов в текущей директории и запускает для каждого файла
     * отдельный поток, который ищет вхождения строки в файле.
     * По завершении всех потоков выводит на экран информацию о найденных вхождениях.
     */
   public static void main(String[] args) throws InterruptedException {

       // Проверяем наличие аргументов командной строки
       if (args.length < 1)
       {
            System.out.println("Usage: FileSearch <substring>");
            System.exit(1);
       }

       // Получаем подстроку для поиска
       String substring = args[0];
       // Получаем список файлов в текущей директории
       File currentDir = new File(".");
       File[] files = currentDir.listFiles();
       List<String> fileNames = new ArrayList<>();

       // Фильтруем и добавляем только текстовые файлы
       for (File file : files) {
           if (file.isFile() && file.getName().endsWith(".txt")) {
                fileNames.add(file.getName());
           }
       }
       // Выводим список доступных файлов
       System.out.println("Available files: " + fileNames);
       // Создаем очередь для хранения результатов поиска и список потоков
       BlockingQueue<SearchResult> results = new LinkedBlockingQueue<>();
       List<Thread> threads = new ArrayList<>();
       // Создаем поток для каждого файла и запускаем его
       for (String name : fileNames) {
           Thread thread = new Thread(new FileSearcher(substring, name, results));
           thread.start();
           threads.add(thread);
       }
       // Ожидаем завершения всех потоков
       for (Thread thread : threads) {
            thread.join();
       }

       // Проверяем, были ли найдены результаты поиска
       if (results.isEmpty()) {
           System.out.println("No matches found");
       } else {
           // Выводим все результаты
           while (!results.isEmpty()) {

               SearchResult result = results.poll();
                   System.out.printf("Found '%s' in file '%s' at line %d, position %d\n",
                           substring, result.fileName, result.lineNumber, result.index);
           }
       }
   }
    /**
     * Класс для поиска вхождений строки в файле в отдельном потоке.
     */
    static class FileSearcher implements Runnable {
        private final String fileName;
        private final String substring;
        private final BlockingQueue<SearchResult> results;
        /**
         * Конструктор класса.
         *
         * @param substring строка, которую нужно найти.
         * @param fileName  имя файла, в котором нужно искать.
         * @param results   очередь для хранения результатов поиска.
         */
        public FileSearcher( String substring, String fileName, BlockingQueue<SearchResult> results) {
            this.substring = substring;
            this.fileName = fileName;
            this.results = results;
        }
        @Override
        public void run() {
            try {
                // Создаем объект BufferedReader для чтения файла построчно
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                int lineNumber = 1;
                // Ищем подстроку в каждой строке файла
                while ((line = reader.readLine()) != null) {
                    int index = line.indexOf(substring);
                    if (index >= 0) {
                        // Если найдено совпадение, добавляем результат в очередь
                        results.put(new SearchResult(substring, fileName, index, lineNumber));
                    }
                    lineNumber++;
                }
                reader.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Класс SearchResult представляет собой результат поиска вхождения строки в файле.
     * Он содержит информацию о найденной подстроке, имени файла, индексе первого вхождения
     * и номере строки, в которой найдено вхождение.
     */
    private static class SearchResult {

        private String substring;
        private String fileName;

        private int index;
        private int lineNumber;

        /**
         *Создает новый объект SearchResult с заданными параметрами.
         * @param substring найденная подстрока.
         * @param fileName имя файла, в котором найдено вхождение.
         * @param index индекс первого вхождения подстроки в строке файла.
         * @param lineNumber номер строки файла, в которой найдено вхождение.
         */
        public SearchResult( String substring, String fileName, int index, int lineNumber) {
            this.substring = substring;
            this.fileName = fileName;
            this.index = index;
            this.lineNumber = lineNumber;
        }
    }
}