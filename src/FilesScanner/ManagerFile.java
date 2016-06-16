
package FilesScanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;


public class  ManagerFile {

    public ManagerFile() {
    }
    public File openFile(String pPath){
        return new File(pPath);
    }
    public Reader getReader(String pPath) throws FileNotFoundException {
        FileReader myFile = new FileReader(pPath);
        return new BufferedReader(myFile);
    }
    
    public void createFile(String path, String body) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(new FileOutputStream(path))) {
          out.print(body);
        }
    }
}
