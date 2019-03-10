package lesson_20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
            File file = new File(dataDirectories[i]);
            List<String> result = new ArrayList<>();
            File parentDir = file.getParentFile();
            File nameDir = new File("src/main/java/out/" + parentDir.getName() + ".txt");
            if (!nameDir.exists()) {
                getListPaths(parentDir, result);
                createFile(parentDir, result);
            }
        }
    }


    public static void getListPaths(File folder, List<String> result) {
        for (final File item : folder.listFiles()) {

            if (item.isDirectory()) {
                getListPaths(item, result);
            }

            if (item.isFile()) {
                result.add(item.getName());
//                result.add(item.getAbsolutePath());
            }
        }
    }

    public static void createFile(File file, List<String> result) throws FileNotFoundException {
        FileUtils newFile = new FileUtils();
        String nameFile = file.getName();
        String text = "";
        for (String element : result) {
            text += element + "\n";
        }
        newFile.write("src/main/java/out/" + nameFile + ".txt", text);
    }

    public static void makeTree() throws FileNotFoundException {
        TreeMap<String> root = new TreeMap<>();
        Scanner scanner = new Scanner(new File("src/main/input.txt"));
        while (scanner.hasNextLine()) {
            root.put(scanner.nextLine().split("\\\\"));
        }
//        System.out.println(root);
        root.print();
    }
}