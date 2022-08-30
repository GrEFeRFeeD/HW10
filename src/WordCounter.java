import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {

    public static List<Map.Entry<String, Integer>> getWordsFrequency(File file) {
        Map<String, Integer> map = new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (String str : line.split(" ")) {
                    if (map.containsKey(str)) {
                        map.put(str, map.get(str) + 1);
                    } else {
                        map.put(str, 1);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return mapToSortedList(map);
    }

    private static List<Map.Entry<String, Integer>> mapToSortedList(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(map.entrySet());
        sortedList.sort((o1, o2) -> o2.getValue() - o1.getValue());
        return sortedList;
    }

}
