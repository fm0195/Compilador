
package scanner;

public class StringToken extends LiteralToken {
    
    public StringToken(java.lang.String pValue, int pLineNumber,int pId) {
        super(pValue, pLineNumber, pId);
    }
    
    public String toString(){
      return super.toString()+"String";
    }
    
}
