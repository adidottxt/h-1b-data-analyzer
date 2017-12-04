import java.io.FileReader;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class Test {

    public static void main (String[] args) {
        try {
            
            CSVReader reader = new CSVReader(new FileReader("2008-test.csv"));
            String[] nextLine;
            while((nextLine=reader.readNext()) != null) {
                if(nextLine != null) {
                    System.out.println(Arrays.toString(nextLine));
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        
        System.out.println("CSV read complete");
    }
}
