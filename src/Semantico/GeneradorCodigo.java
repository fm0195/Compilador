
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
    private int labelCounter=0;
    private int tempCounter=1;

  public GeneradorCodigo(String path) {
    int index;
    index = path.lastIndexOf(".");
    this.path = path.substring(0, index)+".asm";
    variablesBuffer = new StringBuffer("section .data\n");
    codigoBuffer = new StringBuffer("section .text\n\tglobal _start\n");
  }
  private String generarLabelIF(){
      String label = "Label-IF-ELSE"+labelCounter;
      labelCounter++;
      return label;
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
    for (Funcion funcion : funciones) {
      codigoBuffer.append(funcion.getNombre()+":\n");
      hashVariables = new HashMap<>();
      generarVariablesLocales(funcion.getVariablesLocales(), hashVariables);
      generarCodigo(funcion.getCodigo());
      codigoBuffer.append("\tret\n");
    }
    codigoBuffer.append("_start:\n");
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

    public void generarCodigo(ArrayList<Registro> codigo) {
        for (Registro exp : codigo) {
            if (exp instanceof RSIf) {
                generarIF((RSIf)exp);
            }
            if (exp instanceof RSAsignacion) {
                RSAsignacion asignacion = (RSAsignacion) exp;
                Registro expresion = asignacion.getExpresion();
                String variable = hashVariables.get(asignacion.getId());
                if (expresion instanceof RSDataObject) {
                    RSDataObject literal = (RSDataObject) expresion;
                    String res="";
                    switch(literal.getTipo()){
                        case "int":
                            res += "\tmov "+variable+", "+literal.getValor()+"\n";
                            break;
                        case "float":
                            res+="\tfld "+literal.getValor()+"\n";
                            res+="\tfstp "+variable+"\n";
                            break;
                    }
                    codigoBuffer.append(res);
                }
                else if (expresion instanceof RSVariable) {
                    RSVariable literal = (RSVariable) expresion;
                    String variableCodigo = hashVariables.get(literal.getNombre());
                    String res="";
                    res +="\tmov eax"+", "+variableCodigo+"\n";
                    res +="\tmov "+variable+", eax\n";
                    codigoBuffer.append(res);
                }
                else if (expresion instanceof RSOperacion){
                    int i = tempCounter;
                    if (asignacion.getTipo().equals("int")) {
                        String res="";
                        res += revertir(generarExpresionEntero(expresion, "temp"+tempCounter));
                        res+="\tmov "+variable+", "+"temp"+i+"\n";
                        codigoBuffer.append(res);
                    }
                    else if (asignacion.getTipo().equals("float")) {
                        String res="";
                        res += generarExpresionFlotante(expresion, "temp"+tempCounter);
                        res += "\tfstp "+variable+"\n";
                        codigoBuffer.append(res);
                    }
                }
            }
        }
    }

    private String generarExpresionEntero(Registro expresion, String destino) {
        String res="";
        RSOperacion operacion = (RSOperacion) expresion;
        if (esTerminal(operacion.getValor1()) && esTerminal(operacion.getValor2())) {
            int dest = tempCounter;
            res += "\tmov "+destino+",eax\n";
            Registro v1 = operacion.getValor1();
            Registro v2 = operacion.getValor2();
            String valor1 = v1 instanceof RSDataObject ? (String)((RSDataObject)v1).getValor() : hashVariables.get(((RSVariable)v1).getNombre());
            String valor2 = v2 instanceof RSDataObject ? (String)((RSDataObject)v2).getValor() : hashVariables.get(((RSVariable)v2).getNombre());
            res += generarCodigoOperacionEntera(valor1, valor2, operacion.getOperador());
        } else if (esTerminal(operacion.getValor1()) && !esTerminal(operacion.getValor2())) {
            int dest = tempCounter;
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            Registro v1 = operacion.getValor1();
            String valor1 = v1 instanceof RSDataObject ? (String)((RSDataObject)v1).getValor() : hashVariables.get(((RSVariable)v1).getNombre());
            res += generarCodigoOperacionEntera(valor1, "temp"+dest, operacion.getOperador())+generarExpresionEntero(operacion.getValor2(), "temp"+dest);
        }
        else if (!esTerminal(operacion.getValor1()) && esTerminal(operacion.getValor2())) {
            int dest = tempCounter;
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            Registro v2 = operacion.getValor2();
            String valor2 = v2 instanceof RSDataObject ? (String)((RSDataObject)v2).getValor() : hashVariables.get(((RSVariable)v2).getNombre());
            res += generarCodigoOperacionEntera("temp"+dest, valor2, operacion.getOperador())+generarExpresionEntero(operacion.getValor1(), "temp"+dest);
        }
        else if (!esTerminal(operacion.getValor1()) && !esTerminal(operacion.getValor2())) {
            int dest = tempCounter;
            agregarVariableTemporal("dw");
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            res += generarCodigoOperacionEntera("temp"+dest, "temp"+(dest+1), operacion.getOperador())+generarExpresionEntero(operacion.getValor1(), "temp"+dest)+generarExpresionEntero(operacion.getValor2(), "temp"+(dest+1));
        }
        return res;
    }
    private String generarExpresionFlotante(Registro expresion, String destino) {
        return null;
    }
    
    private boolean esTerminal(Registro op)
    {
        return (op instanceof RSDataObject) || (op instanceof RSVariable);
    }
    
    private void agregarVariableTemporal(String tamaño){
        String res = "\ttemp"+ tempCounter+++" "+tamaño+" 0\n";
        variablesBuffer.append(res);
    }

    private String generarCodigoOperacionEntera(String valor1, String valor2, String operador) {
        String res="";
        switch(operador)
        {
            case "+":
                res+="\tadd eax,"+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "-":
                res+="\tsub eax,"+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "*":
                res+="\timul eax,"+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "/":
                res+="\tidiv "+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "//":
                res+="\tidiv "+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "%":
                res+="\tmov eax, edx\n";
                res+="\tidiv "+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
        }
        return res;
    }

    private String revertir(String string) {
        String[] parts=string.split("\n");
        String res="";
        for (String part : parts) {
            res = part + "\n" + res;
        }
        return res;
    }
    
    private void generarIF(RSIf actual){
        String etiqueta= generarLabelIF();
        String etiquetaSalida=generarLabelIF();
        String etiquetaSiguiente="";
        while(actual!=null){            
            codigoBuffer.append(etiqueta+":\n");
            etiqueta= generarLabelIF();
            if(!actual.isIsElse()){
                codigoBuffer.append(";operaciones para obtener el valor boleano");
                codigoBuffer.append("\n\t;cmp eax, resultadoBooleano\n");
                codigoBuffer.append("\tje "+etiqueta+"\n");
                if(actual.getSiguienteIF()==null){
                    codigoBuffer.append("\tjne "+etiquetaSalida+"\n");
                }else{
                    etiquetaSiguiente=generarLabelIF();
                    codigoBuffer.append("\tjne "+etiquetaSiguiente+"\n");
                }
                codigoBuffer.append(etiqueta+":\n");
            }
            generarCodigo(actual.getCodigo());
            if(!actual.isIsElse()){
                codigoBuffer.append("\tjmp "+etiquetaSalida+"\n");
            }
            etiqueta=etiquetaSiguiente;
            actual=actual.getSiguienteIF();
        }
        codigoBuffer.append(etiquetaSalida+":\n");
    }
    
}
    
