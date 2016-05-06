import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Flyme on 2016/5/5.
 */
public class ProjectCodeLineCount {

    public static int totalCodeLine(String path) throws IOException {
        return countFile(getAllFileInDir(getJavaCodeLineCount(path)));
    }

    private static File getJavaCodeLineCount(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            List<File> files = getAllFileInDir(file);
            return file;
        } else {
            throw new IOException("Wrong path");
        }
    }

    private static List<File> getAllFileInDir(File target) {
        LinkedList<File> fileList = new LinkedList<>();
        LinkedList<File> dirs = new LinkedList<>();
        dirs.push(target);
        while (!dirs.isEmpty()) {
            target = dirs.pop();
            File[] files = target.listFiles();
            for (File file : files) {
                if (!file.isDirectory()) {
                    if (file.getName().endsWith(".java"))
                        fileList.push(file);
                } else {
                    dirs.push(file);
                }
            }
        }
        return fileList;
    }

    private static int countFile(List<File> files) {
        int totalCount = 0;
        for (File file : files) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader br = new BufferedReader(reader);
                System.out.println("Loading file:[" + file.getName() + "]");
                String s;
                while ((s = br.readLine()) != null) {
                    //empty line won't be counted
                    if (s.trim().length() != 0)
                        totalCount++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return totalCount;
    }
}

