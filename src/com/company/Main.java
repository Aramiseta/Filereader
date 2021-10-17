package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    public static void find(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));
        String longest_word = sc.next().replaceAll("[-+.^:,]", "");
        String current;
        String shortest_word = longest_word;
        Set<String> setWords = new HashSet<>();
        long count = 1L;
        setWords.add(longest_word);

        while (sc.hasNext()) {
            current = sc.next().replaceAll("[-+.^:,]", "");
            count += current.length();
            setWords.add(current);
            if (current.length() > longest_word.length()) {
                longest_word = current;
            }
            if (current.length() < shortest_word.length()) {
                shortest_word = current;
            }

        }

        System.out.println("Longest word: " + longest_word);
        System.out.println("Shortest word: " + shortest_word);
        System.out.println("Average size of word: " + findAverage(count, setWords.size()));
        saveSet(setWords);

    }

    public static void saveSet(Set<String> setWords) throws IOException {
        List<String> wordsList = new ArrayList<String>(setWords);
        Collections.sort(wordsList);
        BufferedWriter writer = new BufferedWriter(new FileWriter("allWords.txt"));
        writer.write(String.valueOf(wordsList));
        writer.close();
        System.out.println("File name: allWords.txt");
    }

    public static Long findAverage(long count, int counter) {
        return count / counter;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, put path to the file: ");
        String path = scanner.nextLine();

        Long wordCount = Files.lines(Paths.get(path))
                .flatMap(line -> Arrays.stream(line.split("[\\n\\r\\s]+")))
                .count();

        System.out.println("Number of words in a file: " + wordCount);
        find(path);

    }
}
