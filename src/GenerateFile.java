import java.io.File;
import java.io.FileWriter;

/**
 * Created by jiaweizhang on 9/29/16.
 */
public class GenerateFile {
    public static void main(String args[]) {
        StringBuilder sb = new StringBuilder();
        String ten = "abcdeabcde";
        for (int i=0; i<10000000; i++) {
            sb.append(ten);
        }
        long unixTime = System.currentTimeMillis() / 1000L;

        File f = new File("sonar100MB.txt");
        try(FileWriter writer = new FileWriter(f.getAbsoluteFile())){
            writer.append(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
