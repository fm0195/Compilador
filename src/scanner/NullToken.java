package scanner;

public class NullToken extends LiteralToken{
    
    public NullToken(String pValue, int pLineNumber,int pId) {
        super(pValue, pLineNumber, pId);
    }
    
    public String toString(){
      return super.toString()+"Null";
    }
    
}
