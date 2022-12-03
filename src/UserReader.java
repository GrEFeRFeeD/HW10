import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class UserReader {

  public static List<User> getUsersFromFile(String fileName) {
    List<User> users = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(fileName))) {

      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] fields = line.split(" ");

        if (fields.length == 2) {
          String name = fields[0];
          int age = Integer.parseInt(fields[1]);
          users.add(new User(name, age));
        }
      }

    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }

    return users;
  }
}
