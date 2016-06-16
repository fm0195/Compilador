
package scanner;

public class CharToken extends LiteralToken {
    
    public CharToken(String pValue, int pLineNumber,int pId) {
        super(pValue, pLineNumber, pId);
    }
    
    public String toString(){
      return super.toString()+"Char";
    }
    
}
