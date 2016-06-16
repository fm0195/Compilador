
package Semantico;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneradorCodigo {
    private StringBuffer variablesBuffer;
    private StringBuffer codigoBuffer;
    private String path;
    private HashMap<String, String> hashVariables;

  public GeneradorCodigo(String path) {
    int index;
    index = path.indexOf(".");
    this.path = path.substring(0, index);
    variablesBuffer = new StringBuffer("section .data\n");
    codigoBuffer = new StringBuffer("section .text\n\tglobal _start\n_start:");
  }
  
  public void generarVariablesGlobales(ArrayList<RSId> variables)
  {
    hashVariables = new HashMap<>();
    for (RSId variable : variables) {
      String nombreCodigo = variable.getNombre();
      String nombreAsm = "var_"+nombreCodigo;
      String linea = "\t"+nombreAsm + " " + tipoDato(variable.getTipo()) + " " + "0\n";
      variablesBuffer.append(linea);
      hashVariables.put(nombreCodigo, nombreAsm);
    }
  }
  private String tipoDato(String tipo)
  {
    String res="";
    switch(tipo){
      case "int":
        res="dw";
        break;
      case "float":
        res = "dd";
        break;
      case "bool":
        res = "db";
        break;
      case "char":
        res = "db";
        break;
      case "string":
        res = "db";
        break;
      default:
        res="db";
    }
    return res;
  }
    
    
    
    
}
