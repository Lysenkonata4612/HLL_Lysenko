import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.lang.System;

public class FileSearch
{
    /**
     * Главный метод программы.
     * Принимает строку для поиска в качестве аргумента командной строки.
     * Получает список имен файлов в текущей директории и запускает для каждого файла
     * отдельный поток, который ищет вхождения строки в файле.
     * По завершении всех потоков выводит на экран информацию о найденных вхождениях.
     */

    public static void main(String[] args)
    {

        File currentDir = new File(".");
        File[] files = currentDir.listFiles();
        List<String> fileNames = new ArrayList<>();

        // Фильтруем и добавляем только текстовые файлы
        for (File file : files)
        {
            if (file.isFile() && file.getName().endsWith(".txt"))
            {
                fileNames.add(file.getName());
            }
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите искомую подстроку:");
        String substring = scan.nextLine();
        scan.close();

        // Выводим список доступных файлов
        System.out.println("Available files: " + fileNames);

        // Создаем списки для хранения поисковых потоков и результатов поиска
        List<Thread> threads = new ArrayList<>();
        BlockingQueue<SearchResult> results = new LinkedBlockingQueue<>();

        // Создаем поисковый поток для каждого файла и запускаем его
        for (String name : fileNames)
        {
            Thread thread = new Thread(new FileSearcher(substring, name, results));
            thread.start();
            threads.add(thread);
        }

        // Создаем поток для чтения списка с результатами и вывода их на экран и запускаем его
        Thread outputThread = new Thread(new ResultsOutput(threads, results));
        outputThread.start();
   }

    /**
     * Класс для поиска вхождений строки в файле в отдельном потоке.
     */
    static class FileSearcher implements Runnable
    {
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
        public FileSearcher(String substring, String fileName, BlockingQueue<SearchResult> results)
        {
            this.substring = substring;
            this.fileName = fileName;
            this.results = results;
        }
        @Override
        public void run()
        {
            try
            {
                // Создаем объект BufferedReader для чтения файла построчно
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                int lineNumber = 1;
                // Ищем подстроку в каждой строке файла
                while ((line = reader.readLine()) != null)
                {
                    int index = line.indexOf(substring);
                    if (index >= 0)
                    {
                        // Если найдено совпадение, добавляем результат в очередь
                        results.put(new SearchResult(substring, fileName, index, lineNumber));
                    }
                    lineNumber++;
                }
                reader.close();
            }
            catch (IOException | InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Класс вывода результатов поиска  на экран.
     */
    static class ResultsOutput implements Runnable
    {
        private final List<Thread> threads;
        private final BlockingQueue<SearchResult> results;
        private int NumEndThreads;
        private int CurrIndex;
        private int LastIndex;
        /**
         * Конструктор класса.
         *
         * @param threads   список поисковых потоков
         * @param results хранит результаты поиска.
         */
        public ResultsOutput(List<Thread> threads, BlockingQueue<SearchResult> results)
        {
            this.threads = threads;
            this.results = results;
            this.NumEndThreads = 0;
        }
        @Override
        public void run()
        {
            while(true)
            {
                // вывод полученных результатов
                for (SearchResult result : results)
                {

                        System.out.printf("Found '%s' in file '%s' at line %d, position %d\n", result.substring, result.fileName, result.lineNumber, result.index);
                        results.remove(result);
                }

                    // проверка завершения работы поисковых потоков
                    for (Thread thread : threads)
                    {
                        if (thread.getState() == Thread.State.TERMINATED) NumEndThreads++;
                    }
                    if (threads.size() == NumEndThreads) break;
            }
        }
    }

    /**
     * Класс SearchResult представляет собой хранилище результата поиска вхождения строки в файле.
     * Он содержит информацию о найденной подстроке, имени файла, индексе первого вхождения
     * и номере строки, в которой найдено вхождение.
     */
    private static class SearchResult
    {

        private final String substring;
        private final String fileName;
        private final int index;
        private final int lineNumber;

        /**
         *Создает новый объект SearchResult с заданными параметрами.
         * @param substring найденная подстрока.
         * @param fileName имя файла, в котором найдено вхождение.
         * @param index индекс первого вхождения подстроки в строке файла.
         * @param lineNumber номер строки файла, в которой найдено вхождение.
         */
        public SearchResult( String substring, String fileName, int index, int lineNumber)
        {
            this.substring = substring;
            this.fileName = fileName;
            this.index = index;
            this.lineNumber = lineNumber;
        }
    }
}