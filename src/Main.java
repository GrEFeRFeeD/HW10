import exceptions.FileNotReachableException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        // Task1
        System.out.println("Task 1:");
        printFile(new File("task1_phones.txt"));
        System.out.println("\nCorrect phones:");
        try {
            PhoneReader phoneReader = new PhoneReader("task1_phones.txt");
            System.out.println(phoneReader.getPhones());
        } catch (FileNotReachableException e) {
            System.out.println(e.getMessage());
        }

        //Task2
        System.out.println("\nTask 2:");
        printFile(new File("task2_users.txt"));
        List<User> users = UserReader.getUsersFromFile("task2_users.txt");
        File file = UserJSONWriter.writeToFile(users);
        printFile(file);

        //Task3
        System.out.println("\nTask 3:");
        printFile(new File("task3_words.txt"));
        List<Map.Entry<String, Integer>> wordFreqList = WordCounter.getWordsFrequency(new File("task3_words.txt"));
        System.out.println("Words frequency:");
        for (Map.Entry<String, Integer> entry : wordFreqList) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static void printFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            System.out.println("File \"" + file.getName() + "\":");
            while (scanner.hasNextLine()) {
                System.out.println("\t" + scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
