import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jiaweizhang on 10/1/2016.
 */
public class FileIOTest {
    public void run() {

        // 1MB
        System.out.println("Testing 1MB file");
        test("sonar1MB.txt");
        System.out.println();

        // 10MB
        System.out.println("Testing 10MB file");
        test("sonar10MB.txt");
        System.out.println();

        // 50MB
        System.out.println("Testing 50MB file");
        test("sonar50MB.txt");
        System.out.println();

        // 100MB
        System.out.println("Testing 100MB file");
        test("sonar100MB.txt");
        System.out.println();

    }

    public void test(String path) {
        // start time
        long startTime = System.currentTimeMillis();

        String contents = readFile(path);
        // finished reading file into memory
        long finishedReading = System.currentTimeMillis();

        System.out.println("Read  time (ms): " + (finishedReading - startTime));

        deleteFile("write_result.txt");
        long startWritingTime = System.currentTimeMillis();

        writeFile("write_result.txt", contents);
        // finished writing file to disk again
        long finishedWritingTime = System.currentTimeMillis();

        System.out.println("Write time (ms): " + (finishedWritingTime - startWritingTime));

        // remove file
        deleteFile("write_result.txt");
    }

    public void writeFile(String path, String contents) {
        File f = new File(path);
        try (FileWriter writer = new FileWriter(f.getAbsoluteFile())) {
            writer.append(contents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String path) {
        File xx = new File(path);
        if (xx.exists()) {
            xx.delete();
        }
    }

    static String readFile(String path) {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, Charset.defaultCharset());
    }

    public static void main(String args[]) {
        FileIOTest fileIOTest = new FileIOTest();
        fileIOTest.run();
    }
}
