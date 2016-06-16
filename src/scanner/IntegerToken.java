package scanner;

public class IntegerToken extends LiteralToken{
    
    public IntegerToken(String pValue, int pLineNumber,int pId) {
        super(pValue, pLineNumber, pId);
    }
    
    public String toString(){
      return super.toString()+ "Integer";
    }
    
}
