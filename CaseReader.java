import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.opencsv.CSVReader;


public class CaseReader {
    
    XSSFSheet testSheet;
    String filename;
    ArrayList<Case> cases = new ArrayList<Case>();
    
    /**
     * This is the constructor.
     * @param file is the csv file that we use and parse.
     * We call the readFile() method to add each line to the ArrayList lines.
     * @throws IOException 
     */
    public CaseReader(String file) throws IOException {
        
        filename = file;
        createCases();
    }
    
    public void createCases() throws IOException {
        System.out.println("Creating cases...");
        CSVReader reader = new CSVReader(new FileReader(filename));
        
        if (filename.contains("2008")) {

                String[] nextLine;
                String[] headerLine = reader.readNext();
                
                while((nextLine=reader.readNext()) != null) {
                    if(nextLine != null || nextLine != headerLine) {
                        Case newCase = new Case(nextLine, 2008);
                        cases.add(newCase);
                    }
                }
      
        } else if (filename.contains("2009")) {
            
            String[] nextLine;
            String[] headerLine = reader.readNext();
            while((nextLine=reader.readNext()) != null) {
                if(nextLine != null || nextLine != headerLine) {
                    Case newCase = new Case(nextLine, 2009);
                    cases.add(newCase);
                }
            }
        } else if (filename.contains("2010")) {
            
            String[] nextLine;
            String[] headerLine = reader.readNext();
            while((nextLine=reader.readNext()) != null) {
                if(nextLine != null || nextLine != headerLine) {
                    Case newCase = new Case(nextLine, 2010);
                    cases.add(newCase);
                }
            }
        } else if (filename.contains("2011")) {
            
            String[] nextLine;
            String[] headerLine = reader.readNext();
            while((nextLine=reader.readNext()) != null) {
                if (nextLine[4].equals("H-1B")) {
                    if(nextLine != null || nextLine != headerLine) {
                        Case newCase = new Case(nextLine, 2011);
                        cases.add(newCase);
                    }
                }
            }
        } else if (filename.contains("2012")) {
            
            String[] nextLine;
            String[] headerLine = reader.readNext();
            while((nextLine=reader.readNext()) != null) {
                if (nextLine[4].equals("H-1B")) {
                    if(nextLine != null || nextLine != headerLine) {
                        Case newCase = new Case(nextLine, 2012);
                        cases.add(newCase);
                    }
                }
            }
        } else if (filename.contains("2013")) {
            
            String[] nextLine;
            String[] headerLine = reader.readNext();
            
            while((nextLine=reader.readNext()) != null) {
                if(nextLine != null || nextLine != headerLine) {
                    if (nextLine[4].equals("H-1B")) {
                        if(Arrays.toString(nextLine).contains("\n")) {
                            System.out.println("hi");
                        }
                        Case newCase = new Case(nextLine, 2013);
                        cases.add(newCase);
                    }
                }
            }
        } else if (filename.contains("2014")) {
            
            String[] nextLine;
            String[] headerLine = reader.readNext();
            while((nextLine=reader.readNext()) != null) {
                if (nextLine[4].equals("H-1B")) {
                    if(nextLine != null || nextLine != headerLine) {
                        Case newCase = new Case(nextLine, 2014);
                        cases.add(newCase);
                    }
                }
            }
        } else if (filename.contains("2015")) {
            
            String[] nextLine;
            String[] headerLine = reader.readNext();
            while((nextLine=reader.readNext()) != null) {
                if (nextLine[4].equals("H-1B")) {
                    if(nextLine != null || nextLine != headerLine) {
                        Case newCase = new Case(nextLine, 2015);
                        cases.add(newCase);
                    }
                }
            }
        } else if (filename.contains("2016")) {
            
            String[] nextLine;
            String[] headerLine = reader.readNext();
            while((nextLine=reader.readNext()) != null) {
                if (nextLine[4].equals("H-1B")) {
                    if(nextLine != null || nextLine != headerLine) {
                        Case newCase = new Case(nextLine, 2016);
                        cases.add(newCase);
                    }
                }
            }
        } else if (filename.contains("2017")) {
            
            String[] nextLine;
            String[] headerLine = reader.readNext();
            while((nextLine=reader.readNext()) != null) {
                if (nextLine[4].equals("H-1B")) {
                    if(nextLine != null || nextLine != headerLine) {
                        Case newCase = new Case(nextLine, 2017);
                        cases.add(newCase);
                    }
                }
            }
        }       
        System.out.println("Done!");
        reader.close();
    }

    public ArrayList<Case> getCases() {
        return cases;
    } 
    
    public int getNumberOfCases() {
        return cases.size();
    }
    
    public Case getCase(int index) {
        return cases.get(index);
    }
    
}
