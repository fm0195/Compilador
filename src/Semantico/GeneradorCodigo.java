
package Semantico;

import FilesScanner.ManagerFile;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneradorCodigo {
    private StringBuffer variablesBuffer;
    private StringBuffer codigoBuffer;
    private String path;
    private HashMap<String, String> hashVariables;
    private int labelCounter;

  public GeneradorCodigo(String path) {
    int index;
    index = path.lastIndexOf(".");
    this.path = path.substring(0, index)+".asm";
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
  public void generarFunciones(ArrayList<Funcion> funciones){
    hashVariables = new HashMap<>();
    for (Funcion funcion : funciones) {
      generarVariablesLocales(funcion.getVariablesLocales(), hashVariables);
      generarCodigo(funcion.getCodigo());
    }
  }
  private void generarVariablesLocales(ArrayList<RSId> variables, HashMap<String, String> hashVariables)
  {
    for (RSId variable : variables) {
      String nombreCodigo = variable.getNombre();
      String nombreAsm = "var_fun_"+nombreCodigo;
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
      case "boolean":
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
    
    public void generateAsmFile() throws FileNotFoundException, IOException{
        String codigo = variablesBuffer.toString()+codigoBuffer.toString();
        ManagerFile managerFile = new ManagerFile();
        managerFile.createFile(path, codigo);
    }

    private void generarCodigo(ArrayList<Registro> codigo) {
        for (Registro exp : codigo) {
            if (exp instanceof RSIf) {
                //codigo del if
            }
            if (exp instanceof RSAsignacion) {
                RSAsignacion asignacion = (RSAsignacion) exp;
                generarExpresion(asignacion.getExpresion());
                String variableCodigo = hashVariables.get(asignacion.getId());
                codigoBuffer.append("mov ").append(variableCodigo).append(", R8\n");
            }
        }
    }

    private void generarExpresion(Registro expresion) {
        if (expresion instanceof RSVariable) {
            RSVariable variable = (RSVariable) expresion;
            String res;
            String variableCodigo = hashVariables.get(variable.getNombre());
            codigoBuffer.append("mov R8,").append(variableCodigo).append("\n");
        }
        if (expresion instanceof RSDataObject) {
            RSDataObject variable = (RSDataObject) expresion;
            String res="";
            if ("float".equals(variable.getTipo())) {
                res += "fld "+variable.getValor()+"\n";
                res += "";
            }else{
                
            }
        }
    }
    
    
}
    
