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
    public static final String JAVA = ".java";
    public static final String C_LANGUAGE= ".c";
    public static final String CPP = ".cpp";
    public static final String JAVASCRIPT = ".js";
    public static final String PYTHON = ".py";
    public static final String HTML = ".html";
    public static final String CSS = ".css";

    public static int totalCodeLine(String path,String programLanguage) throws IOException {
        return countFile(getAllFileInDir(getDir(path),programLanguage));
    }

    public static int getTotalFile(String path,String programLanguage) throws IOException {
        return getAllFileInDir(getDir(path),programLanguage).size();
    }

    private static File getDir(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return file;
        } else {
            throw new IOException("Wrong path");
        }
    }

    private static List<File> getAllFileInDir(File target,String programLanguage) {
        LinkedList<File> fileList = new LinkedList<>();
        LinkedList<File> dirs = new LinkedList<>();
        dirs.push(target);
        while (!dirs.isEmpty()) {
            target = dirs.pop();
            File[] files = target.listFiles();
            for (File file : files) {
                if (!file.isDirectory()) {
                    if (file.getName().toLowerCase().endsWith(programLanguage))
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
            try (FileReader reader = new FileReader(file);
                BufferedReader br = new BufferedReader(reader)){
                
                System.out.println("Loading file:[" + file.getName() + "]");
                String s;
                while ((s = br.readLine()) != null) {
                    //empty line won't be counted
                    if (s.trim().length() != 0 )
                        totalCount++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return totalCount;
    }
}
