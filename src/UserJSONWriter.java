import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class UserJSONWriter {

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
