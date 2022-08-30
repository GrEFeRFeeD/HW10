import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {

    private String name;
    private int age;

    public User() {}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    static class UserReader {

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

    static class UserJSONWriter {

        public static final String fileName = "user.json";

        public static File writeToFile(List<User> users) {

            File file = new File(fileName);

            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                StringBuilder sb = new StringBuilder();

                sb.append("[\n");
                for (User user : users) {
                    String serializedUser = getSerializedUser(user);
                    sb.append(serializedUser);
                }
                sb.delete(sb.length() - 2, sb.length()).append("\n]");

                writer.write(sb.toString());
                writer.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                return null;
            }

            return file;
        }

        public static String getSerializedUser(User user) {
            StringBuilder sb = new StringBuilder();

            sb.append("\t{\n\t\t\"name\": \"")
                    .append(user.getName())
                    .append("\",\n\t\t\"age\":")
                    .append(user.getAge())
                    .append("\n\t},\n");

            return sb.toString();
        }
    }
}
