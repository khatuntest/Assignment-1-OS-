import java.util.Arrays;
import java.util.Collections;
import java.io.File;
public class reverseLister {
    public static void lsReverse() {
        File f;
        f = new File(System.getProperty("user.dir"));
        File[] list = f.listFiles();
        if (list != null) {
            Arrays.sort(list, Collections.reverseOrder());

            for (File file : list) {

                if (file.isDirectory()) {
                    System.out.println("    " + file.getName());
                }
                else {
                    System.out.println(file.getName());
                }
            }
        }
    }
}



