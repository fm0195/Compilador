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
public class ReservedWordToken extends Token{
    
    public ReservedWordToken(String pValue, int pLineNumber,int pId) {
        super(pValue, pLineNumber, pId);
    }
    
    public String toString(){
      return super.toString()+"Reserved Word";
    }
    
}
