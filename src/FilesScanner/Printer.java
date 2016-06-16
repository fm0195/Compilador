/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesScanner;

import java.util.ArrayList;
import java.util.HashMap;
import scanner.IImprimible;
import scanner.Token;

public class Printer {
  private HashMap<String, ArrayList<Token>> mapTokens = new HashMap<>();
  ArrayList<Token> listaTokens;
  int index;
  
  public void addToken(Token newToken){
    listaTokens = mapTokens.get(newToken.getValue());
    if(listaTokens != null){
      if(listaTokens.contains(newToken)){
        index = listaTokens.indexOf(newToken);
        listaTokens.get(index).increaseLineAparitions();
      }else {
        listaTokens.add(newToken);
      }
    }else{
      ArrayList<Token> newList = new ArrayList<Token>();
      newList.add(newToken);
      mapTokens.put(newToken.getValue(), newList);
    }
  }
  
  public void printTokens(){
    ArrayList<String> set = new ArrayList<>();
    String result = "";
    set.addAll(mapTokens.keySet());
    set.sort(null);
    for (String set1 : set) {
      result += mapTokens.get(set1).get(0).toString()+"\n\tLinea: ";
      for (Token token : mapTokens.get(set1)) {
        result += token.getLineNumber() + (token.getLineAparitions() == 1 ? " " : "("+token.getLineAparitions()+") ");
      }
      System.out.println(result);
      result = "";
    }
  }
  public void impimirDatos(String titulo, ArrayList<IImprimible> listaDatos)
  {
      System.out.println("RESULTADO: "+titulo);
      for (IImprimible listaDato : listaDatos) {
          System.out.println("\t--->"+listaDato.toString());
      }
      if (listaDatos.size()==0){
          System.out.println("\t<NO OCURRIERON ERRORES>\n");
      }
  }
  
}
