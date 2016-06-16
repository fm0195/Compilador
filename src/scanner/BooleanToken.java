
package scanner;

import java_cup.runtime.Symbol;

public class BooleanToken extends LiteralToken{
    
    public BooleanToken(String pValue, int pLineNumber, int pId) {
        super(pValue, pLineNumber, pId);
    }
    
    public String toString(){
      return super.toString()+"Boolean";
    }
}
