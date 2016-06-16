package scanner;

public class FloatToken extends LiteralToken {
    
    public FloatToken(String pValue, int pLineNumber,int pId) {
        super(pValue, pLineNumber, pId);
    }
    
    public String toString(){
      return super.toString()+"Float";
    }
}
