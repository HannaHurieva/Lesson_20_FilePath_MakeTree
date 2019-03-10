package lesson_20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FilePath {
    public static void main(String[] args) {
        try {
            init();
//            makeTree();
        } catch (FileNotFoundException e) {
            System.out.println("Файл с данными не найден!");
        }
    }

    private static void init() throws FileNotFoundException {
        FileUtils fileUtils = new FileUtils();
        String allData = fileUtils.read("src/main/input.txt");
        String[] dataDirectories = allData.split("\n");
        for (String dataDirectory : dataDirectories) {
            File file = new File(dataDirectory);
            List<String> fileNameList = new ArrayList<>();
            TreeMap<String> tree = new TreeMap<>();
            File parentDir = file.getParentFile();
            File nameDir = new File("src/main/java/out/" + parentDir.getName() + ".txt");
            if (!nameDir.exists()) {
                makeTreeByFilePath(parentDir, fileNameList, tree);
                tree.print();
                createFile(parentDir, fileNameList);
            }
        }
    }

    private static void makeTreeByFilePath(File folder, List<String> result, TreeMap<String> root) {
        for (final File item : folder.listFiles()) {

            if (item.isDirectory()) {
                makeTreeByFilePath(item, result, root);
            }

            if (item.isFile()) {
                result.add(item.getName());
                String Path = item.getAbsolutePath();
                root.put(Path.split("\\\\"));
            }
        }
    }

    private static void createFile(File file, List<String> result) throws FileNotFoundException {
        FileUtils newFile = new FileUtils();
        String nameFile = file.getName();
        String text = "";
        for (String element : result) {
            text += element + "\n";
        }
        newFile.write("src/main/java/out/" + nameFile + ".txt", text);
    }
}