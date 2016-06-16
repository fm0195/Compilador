
package scanner;
import java.io.IOException;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public abstract class Token implements Comparable<Token>, Scanner,IImprimible{
    protected String _Value;
    protected int _LineNumber;
    protected int _LineAparitions;
    protected int _id;

    public Token(String _Value, int _LineNumber, int pId) {
        this._Value = _Value;
        this._LineNumber = _LineNumber+1;
        this._LineAparitions = 1;
        this._id = pId;
    }
    public boolean equals(Object pObject){
      if(pObject == null)
         return false;
      if (!Token.class.isAssignableFrom(pObject.getClass())) {
        return false;
      }
      Token pToken;
      pToken = (Token) pObject;
      return _Value.equals(pToken._Value) && _LineNumber==pToken._LineNumber;
    }
    public int hashCode(){
      int hash = 1;
      return 37 * (this._Value.hashCode() + this._LineNumber) + hash;
    }
    
    public String getValue(){
      return _Value;
    }
    
    public void increaseLineAparitions(){
      this._LineAparitions++;
    }
    
    public int compareTo(Token obj){
      return this._Value.compareTo(obj.getValue());
    }
    @Override
    public String toString(){
      return "Valor: "+this._Value+"\tLinea: "+_LineNumber;
    }

  public int getLineNumber() {
    return _LineNumber;
  }

  public int getLineAparitions() {
    return _LineAparitions;
  }

  public int getId() {
    return _id;
  }
  public Symbol next_token() throws IOException{
    return (Symbol) new ComplexSymbolFactory().newSymbol(_Value, _id, new Symbol(_id,_LineNumber, _LineNumber, _Value));
  }
   
}

