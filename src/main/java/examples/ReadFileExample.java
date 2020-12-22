package examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFileExample {

    public static void readFile() throws IOException {
        File diary = new File("/Users/baskuis/projects/simpilearn/mydiary.txt");
        FileInputStream stream = new FileInputStream(diary);
        int r = 0;
        while((r = stream.read()) != -1)
        {
            System.out.print((char)r);
        }
    }

}
