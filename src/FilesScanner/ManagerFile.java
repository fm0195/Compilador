
package FilesScanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;


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
}
