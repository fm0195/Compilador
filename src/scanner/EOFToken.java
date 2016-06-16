package scanner;

public class EOFToken extends Token{
    
    public EOFToken(String pValue, int pLineNumber,int pId) {
        super(pValue, pLineNumber, pId);
    }
    
    public String toString(){
      return super.toString()+"EOF";
    }
}
