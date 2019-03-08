package lesson_20;

import java.io.*;

public class FileUtils {
    //////////////////////////////////////////////////////////////////////////////////
    public String write(String fileName, String text) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new RuntimeException("Where my fuckin' file?");
                }
            }
            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                out.print(text);
            }
        } catch (IOException ololo) {
            throw new RuntimeException(ololo);
        }
        return fileName;
    }

    //////////////////////////////////////////////////////////////////////////////////
    public String read(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        exists(fileName);
        try {
            File file = new File(fileName);

            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    //////////////////////////////////////////////////////////////////////////////////
    public void update(String nameFile, String newText) throws FileNotFoundException {
        exists(nameFile);
        StringBuilder sb = new StringBuilder();
        String oldFile = read(nameFile);
        sb.append(oldFile);
        sb.append(newText);
        write(nameFile, sb.toString());
    }

    private void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) throw new FileNotFoundException(file.getName());
    }
}
