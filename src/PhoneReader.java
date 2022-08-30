import exceptions.FileNotReachableException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PhoneReader {

    public static final String DEFAULT_REGEX = "(\\([0-9]{3}\\) [0-9]{3}-[0-9]{4})|([0-9]{3}-[0-9]{3}-[0-9]{4})";

    private File file;
    private List<String> phones;
    private String regex;

    public PhoneReader(File file, String regex) throws FileNotReachableException {
        if (!file.exists() || !file.canRead()) {
            throw new FileNotReachableException();
        }
        this.regex = regex;
        setFile(file);
    }

    public PhoneReader(String fileName, String regex) throws FileNotReachableException {
        this.regex = regex;
        setFile(openFile(fileName));
    }

    public PhoneReader(File file) throws FileNotReachableException {
        this(file, DEFAULT_REGEX);
    }

    public PhoneReader(String fileName) throws FileNotReachableException {
        this(fileName, DEFAULT_REGEX);
    }

    public PhoneReader() {
        this.file = null;
        this.regex = DEFAULT_REGEX;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) throws FileNotReachableException {
        if (!file.exists() || !file.canRead()) {
            throw new FileNotReachableException();
        }
        this.file = file;
        List<String> strings = readFile();
        processPhones(strings);
    }

    public List<String> getPhones() {
        return List.copyOf(phones);
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public void setDefaultRegex() {
        this.regex = DEFAULT_REGEX;
    }

    private File openFile(String fileName) throws FileNotReachableException {
        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            throw new FileNotReachableException();
        }
        return file;
    }

    private List<String> readFile() {

        List<String> strings = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {

            String str;

            while ((str = buffer.readLine()) != null) {
                strings.add(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return strings;
    }

    private void processPhones(List<String> strings) {

        phones = new ArrayList<>();

        for (String str : strings) {
            if (str.matches(regex)) {
                phones.add(str);
            }
        }
    }
}
