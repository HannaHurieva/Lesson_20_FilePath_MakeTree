package lesson_20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FilePath {
    public static void main(String[] args) {
        try {
            init();
            makeTree();
        } catch (FileNotFoundException e) {
            System.out.println("Файл с данными не найден!");
        }
    }

    public static void init() throws FileNotFoundException {
        FileUtils fileUtils = new FileUtils();
        String allData = fileUtils.read("src/main/input.txt");
        String[] dataDirectories = allData.split("\n");
        for (int i = 0; i < dataDirectories.length; i++) {
            String[] dataPathAbsolute = dataDirectories[i].split("\\\\");
            FileUtils newFile = new FileUtils();
            String nameFile = dataPathAbsolute[dataPathAbsolute.length - 1];
            String text = "";
            for (int j = 0; j < dataPathAbsolute.length; j++) text += dataPathAbsolute[j] + "\n";
            String dataPath = newFile.write("src/main/" + nameFile + ".txt", text);
        }
    }

    public static void makeTree() throws FileNotFoundException {
        TreeMap<String> root = new TreeMap<>();
        Scanner scanner = new Scanner(new File("src/main/input.txt"));
        while (scanner.hasNextLine()) {
            root.put(scanner.nextLine().split("\\\\"));
        }
        System.out.println(root);
    }
}