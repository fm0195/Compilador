/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

/**
 *
 * @author jjime
 */
public abstract class LiteralToken extends Token {
    
    public LiteralToken(String pValue, int pLineNumber,int pId) {
        super(pValue, pLineNumber, pId);
    }
    public String toString(){
      return super.toString()+"Literal ";
    }
}
