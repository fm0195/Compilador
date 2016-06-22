
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
    private HashMap<String, String> hashVariables=new HashMap<>();
    private int labelCounter=0;
    private int tempCounter=1;
    boolean potenciaEntera=false;
    boolean potenciaFlotante=false;

  public GeneradorCodigo(String path) {
    int index;
    index = path.lastIndexOf(".");
    this.path = path.substring(0, index)+".asm";
    variablesBuffer = new StringBuffer("section .data\n\ttemp_float dd 0\n");
    codigoBuffer = new StringBuffer("section .text\n\tglobal _start\n");
    tempCounter--;
  }
  private String generarLabelIF(){
      String label = "Label-IF-ELSE"+labelCounter;
      labelCounter++;
      return label;
  }
  
  public void generarVariablesGlobales(ArrayList<RSId> variables)
  {
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
    if (funciones!=null){
        for (Funcion funcion : funciones) {
          codigoBuffer.append(funcion.getNombre()+":\n");
          generarParametros(funcion.getParametros(), funcion.getNombre());
          generarVariablesLocales(funcion.getVariablesLocales(), funcion.getNombre());
          generarCodigo(funcion.getCodigo());
          codigoBuffer.append("\tret\n");
        }
        hashVariables.clear();
        codigoBuffer.append("_start:\n");
    }
  }
  private void generarVariablesLocales(ArrayList<RSId> variables, String nombreFuncion)
  {
    for (RSId variable : variables) {
      String nombreCodigo = variable.getNombre();
      String nombreAsm = "var_fun_"+nombreFuncion+"_"+nombreCodigo;
      String linea = "\t"+nombreAsm + " " + tipoDato(variable.getTipo()) + " " + "0\n";
      variablesBuffer.append(linea);
      hashVariables.put(nombreCodigo, nombreAsm);
    }
  }
  private void generarParametros(ArrayList<RSId> parametros, String nombreFuncion)
  {
    for (RSId variable : parametros) {
      String nombreCodigo = variable.getNombre();
      String nombreAsm = "par_fun_"+nombreFuncion+"_"+nombreCodigo;
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
        if(potenciaEntera){
            String res = "\npotenciaEntera proc\n" +
                            "\tmov eax,ebx\n" +
                            "\tdec ecx\n" +
                            "\tcmp exc,0\n" +
                            "\tjle fin\n" +
                            "ciclo:\n" +
                            "\timul eax,ebx\n" +
                            "\tloop ciclo\n" +
                            "fin:\n" +
                            "\tret\n" +
                            "potenciaEntera endproc\n";
            codigoBuffer.append(res);
        }
        if(potenciaFlotante){
            String res = "\npotenciaFlotante proc\n" +
                        "\tfyl2x                        \n" +
                        "\tf2xm1                        \n" +
                        "\tfld1                        \n" +
                        "\tfaddp st(1),st(0)        \n" +
                        "\tret                        \n" +
                        "potenciaFlotante endp\n";
            codigoBuffer.append(res);
        }
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
                    switch(asignacion.getTipo()){
                        case "int":
                            res +="\tmov eax,"+literal.getValor()+"\n";
                            res += literal.isNegative() ? "\tneg eax\n" : "";
                            res += "\tmov ["+variable+"], eax\n";
                            break;
                        case "float":
                            res+="\tfld "+literal.getValor()+"\n";
                            res+="\tfstp ["+variable+"]\n";
                            res += literal.isNegative() ? "\tmov eax,"+"["+variable+"]"+"\n\tneg eax\n" +"\tmov ["+variable+"],eax\n": "";
                            break;
                    }
                    codigoBuffer.append(res);
                }
                else if (expresion instanceof RSVariable) {
                    RSVariable literal = (RSVariable) expresion;
                    String variableCodigo = hashVariables.get(literal.getNombre());
                    String res="";
                    res +="\tmov eax"+", ["+variableCodigo+"]\n";
                    res += literal.isNegative() ? "\tneg eax\n" : "";
                    res +="\tmov ["+variable+"], eax\n";
                    codigoBuffer.append(res);
                }
                else if (expresion instanceof RSOperacion){
                    int i = tempCounter;
                    switch (asignacion.getTipo()) {
                        case "int":
                            {
                                String res="";
                                res += revertir(generarExpresionEntero(expresion, "[temp"+tempCounter+"]"));
                                res += "\tmov eax, [temp"+i+"]\n";
                                res += "\tmov ["+variable+"], eax\n";
                                codigoBuffer.append(res);
                                break;
                            }
                        case "float":
                            {
                                String res="";
                                res += revertir(generarExpresionFlotante(expresion, "[temp"+tempCounter+"]"));
                                res += "\tmov eax, [temp"+i+"]\n";
                                res += "\tmov ["+variable+"], eax\n";
                                codigoBuffer.append(res);
                                break;
                            }
                    }
                }
            }
        }
    }

    private String generarExpresionEntero(Registro expresion, String destino) {
        String res="";
        RSOperacion operacion = (RSOperacion) expresion;
        if (esTerminal(operacion.getValor1()) && esTerminal(operacion.getValor2())) {
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            res += operacion.isNegative() ? "\tneg eax\n" : "";
            Registro v1 = operacion.getValor1();
            Registro v2 = operacion.getValor2();
            String valor1 = v1 instanceof RSDataObject ? (String)((RSDataObject)v1).getValor() : "["+hashVariables.get(((RSVariable)v1).getNombre())+"]";
            String valor2 = v2 instanceof RSDataObject ? (String)((RSDataObject)v2).getValor() : "["+hashVariables.get(((RSVariable)v2).getNombre())+"]";
            res += generarCodigoOperacionEntera(valor1, v1.isNegative(), valor2, v2.isNegative(), operacion.getOperador());
        } else if (esTerminal(operacion.getValor1()) && !esTerminal(operacion.getValor2())) {
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            res += operacion.isNegative() ? "\tneg eax\n" : "";
            Registro v1 = operacion.getValor1();
            String valor1 = v1 instanceof RSDataObject ? (String)((RSDataObject)v1).getValor() : "["+hashVariables.get(((RSVariable)v1).getNombre())+"]";
            res += generarCodigoOperacionEntera(valor1, v1.isNegative(), "[temp"+tempCounter+"]",false, operacion.getOperador())+generarExpresionEntero(operacion.getValor2(), "[temp"+tempCounter+"]");
        }
        else if (!esTerminal(operacion.getValor1()) && esTerminal(operacion.getValor2())) {
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            res += operacion.isNegative() ? "\tneg eax\n" : "";
            Registro v2 = operacion.getValor2();
            String valor2 = v2 instanceof RSDataObject ? (String)((RSDataObject)v2).getValor() : "["+hashVariables.get(((RSVariable)v2).getNombre())+"]";
            res += generarCodigoOperacionEntera("[temp"+tempCounter+"]", false,valor2, v2.isNegative(), operacion.getOperador())+generarExpresionEntero(operacion.getValor1(), "[temp"+tempCounter+"]");
        }
        else if (!esTerminal(operacion.getValor1()) && !esTerminal(operacion.getValor2())) {
            agregarVariableTemporal("dw");
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            res += operacion.isNegative() ? "\tneg eax\n" : "";
            res += generarCodigoOperacionEntera("[temp"+tempCounter+"]",false, "[temp"+(tempCounter+1)+"]", false, operacion.getOperador())+generarExpresionEntero(operacion.getValor1(), "[temp"+tempCounter+"]")+generarExpresionEntero(operacion.getValor2(), "[temp"+(tempCounter+1)+"]");
        }
        return res;
    }
    private String generarExpresionFlotante(Registro expresion, String destino) {
        String res="";
        RSOperacion operacion = (RSOperacion) expresion;
        if (esTerminal(operacion.getValor1()) && esTerminal(operacion.getValor2())) {
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            res += operacion.isNegative() ? "\tneg eax\n" : "";
            Registro v1 = operacion.getValor1();
            Registro v2 = operacion.getValor2();
            String valor1 = v1 instanceof RSDataObject ? (String)((RSDataObject)v1).getValor() : "["+hashVariables.get(((RSVariable)v1).getNombre())+"]";
            String valor2 = v2 instanceof RSDataObject ? (String)((RSDataObject)v2).getValor() : "["+hashVariables.get(((RSVariable)v2).getNombre())+"]";
            res += generarCodigoOperacionFlotante(valor1, v1.isNegative(), valor2, v2.isNegative(), operacion.getOperador());
        } else if (esTerminal(operacion.getValor1()) && !esTerminal(operacion.getValor2())) {
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            res += operacion.isNegative() ? "\tneg eax\n" : "";
            Registro v1 = operacion.getValor1();
            String valor1 = v1 instanceof RSDataObject ? (String)((RSDataObject)v1).getValor() : "["+hashVariables.get(((RSVariable)v1).getNombre())+"]";
            res += generarCodigoOperacionFlotante(valor1, v1.isNegative(), "[temp"+tempCounter+"]",false, operacion.getOperador())+generarExpresionFlotante(operacion.getValor2(), "[temp"+tempCounter+"]");
        }
        else if (!esTerminal(operacion.getValor1()) && esTerminal(operacion.getValor2())) {
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            res += operacion.isNegative() ? "\tneg eax\n" : "";
            Registro v2 = operacion.getValor2();
            String valor2 = v2 instanceof RSDataObject ? (String)((RSDataObject)v2).getValor() : "["+hashVariables.get(((RSVariable)v2).getNombre())+"]";
            res += generarCodigoOperacionFlotante("[temp"+tempCounter+"]", false,valor2, v2.isNegative(), operacion.getOperador())+generarExpresionFlotante(operacion.getValor1(), "[temp"+tempCounter+"]");
        }
        else if (!esTerminal(operacion.getValor1()) && !esTerminal(operacion.getValor2())) {
            agregarVariableTemporal("dw");
            agregarVariableTemporal("dw");
            res += "\tmov "+destino+",eax\n";
            res += operacion.isNegative() ? "\tneg eax\n" : "";
            res += generarCodigoOperacionFlotante("[temp"+tempCounter+"]",false, "[temp"+(tempCounter+1)+"]", false, operacion.getOperador())+generarExpresionFlotante(operacion.getValor1(), "[temp"+tempCounter+"]")+generarExpresionFlotante(operacion.getValor2(), "[temp"+(tempCounter+1)+"]");
        }
        return res;
    }
    
    private boolean esTerminal(Registro op)
    {
        return (op instanceof RSDataObject) || (op instanceof RSVariable);
    }
    
    private void agregarVariableTemporal(String tamaño){
        String res = "\ttemp"+ tempCounter+++" "+tamaño+" 0\n";
        variablesBuffer.append(res);
    }

    private String generarCodigoOperacionEntera(String valor1, boolean neg1, String valor2, boolean neg2, String operador) {
        String res="";
        switch(operador)
        {
            case "+":
                res+="\tadd eax,ebx\n";
                res += neg2 ? "\tneg ebx\n" : "";
                res += neg1 ? "\tneg eax\n" : "";
                res += "\tmov ebx,"+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "-":
                res+="\tsub eax,ebx\n";
                res += neg2 ? "\tneg ebx\n" : "";
                res += neg1 ? "\tneg eax\n" : "";
                res += "\tmov ebx,"+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "*":
                res+="\timul eax,ebx\n";
                res += neg2 ? "\tneg ebx\n" : "";
                res += neg1 ? "\tneg eax\n" : "";
                res += "\tmov ebx,"+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "/":
                res+="\tidiv ebx\n";
                res += neg2 ? "\tneg ebx\n" : "";
                res += neg1 ? "\tneg eax\n" : "";
                res += "\tmov ebx,"+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "//":
                res+="\tidiv ebx\n";
                res += neg2 ? "\tneg ebx\n" : "";
                res += neg1 ? "\tneg eax\n" : "";
                res += "\tmov ebx,"+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "%":
                res+="\tmov eax, edx\n";
                res+="\tidiv ebx\n";
                res += neg2 ? "\tneg ebx\n" : "";
                res += neg1 ? "\tneg eax\n" : "";
                res += "\tmov ebx,"+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "**":
                potenciaEntera = true;
                res+="\tcall potenciaEntera\n";
                res+="\tmov eax,0\n";
                res += neg2 ? "\tneg ecx\n" : "";
                res += neg1 ? "\tneg ebx\n" : "";
                res += "\tmov ecx,"+valor2+"\n";
                res+="\tmov ebx,"+valor1+"\n";
                break;
        }
        return res;
    }
    private String generarCodigoOperacionFlotante(String valor1, boolean neg1, String valor2, boolean neg2, String operador) {
        String res="";
        switch(operador)
        {
            case "+":
                res+="\tmov eax,[temp_float]\n";
                res+="\tfstp [temp_float]\n";
                res+="\tfadd \n";
                res += neg2 ? "\tfsub\n\tfld 0\n"+"\tfld "+valor2+"\n" : "\tfld "+valor2+"\n";
                res += neg1 ? "\tfsub\n\tfld 0\n"+"\tfld "+valor1+"\n" : "\tfld "+valor1+"\n";
                break;
            case "-":
                res+="\tmov eax,[temp_float]\n";
                res+="\tfstp [temp_float]\n";
                res+="\tfsub \n";
                res += neg1 ? "\tfsub\n\tfld 0\n"+"\tfld "+valor1+"\n" : "\tfld "+valor1+"\n";
                res += neg2 ? "\tfsub\n\tfld 0\n"+"\tfld "+valor2+"\n" : "\tfld "+valor2+"\n";
                break;
            case "*":
                res+="\tmov eax,[temp_float]\n";
                res+="\tfstp [temp_float]\n";
                res+="\tfmul \n";
                res += neg1 ? "\tfsub\n\tfld 0\n"+"\tfld "+valor1+"\n" : "\tfld "+valor1+"\n";
                res += neg2 ? "\tfsub\n\tfld 0\n"+"\tfld "+valor2+"\n" : "\tfld "+valor2+"\n";
                break;
            case "/":
                res+="\tmov eax,[temp_float]\n";
                res+="\tfstp [temp_float]\n";
                res+="\tfdiv \n";
                res += neg1 ? "\tfsub\n\tfld 0\n"+"\tfld "+valor1+"\n" : "\tfld "+valor1+"\n";
                res += neg2 ? "\tfsub\n\tfld 0\n"+"\tfld "+valor2+"\n" : "\tfld "+valor2+"\n";
                break;
            case "//":
                res+="\tidiv ebx\n";
                res += neg2 ? "\tneg ebx\n" : "";
                res += neg1 ? "\tneg eax\n" : "";
                res += "\tmov ebx,"+valor2+"\n";
                res += "\tmov eax,"+valor1+"\n";
                break;
            case "%":
                res+="\tmov eax, edx\n";
                res+="\tidiv ebx\n";
                res += neg2 ? "\tneg ebx\n" : "";
                res += neg1 ? "\tneg eax\n" : "";
                res += "\tmov ebx,"+valor2+"\n";
                res+="\tmov eax,"+valor1+"\n";
                break;
            case "**":
                potenciaFlotante=true;
                res+="\tmov eax,[temp_float]\n";
                res+="\tfstp [temp_float]\n";
                res+="\tcall potenciaFlotante \n";
                res += neg1 ? "\tfsub\n\tfld 0\n"+"\tfld "+valor1+"\n" : "\tfld "+valor1+"\n";
                res += neg2 ? "\tfsub\n\tfld 0\n"+"\tfld "+valor2+"\n" : "\tfld "+valor2+"\n";
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
                codigoBuffer.append("\t;operaciones para obtener el valor boleano");
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
    
