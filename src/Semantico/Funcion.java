package Semantico;

import java.util.ArrayList;
import java.util.HashMap;
import java_cup.runtime.Symbol;


public class Funcion {
    private ArrayList<RSId> parametros = new ArrayList<>();
    private HashMap<String,RSId> variablesLocales = new HashMap<>();
    private ArrayList<Registro> codigo = new  ArrayList<>();
    private RSId nombre;

    public Funcion(RSId nombre,ArrayList<RSId> parametros,HashMap<String,RSId> variablesLocales,
                    ArrayList<Registro> codigo ) {
        this.nombre = nombre;
        this.parametros=parametros;
        this.variablesLocales=variablesLocales;
        this.codigo=codigo;
    }  
    public int getTotalParametros(){
        return parametros.size();
    }
    public boolean validaparametro(int numero, RegistroExpresion param){
        return parametros.get(parametros.size()-1-numero).getTipo().equals(param.getTipo());
    }

    public ArrayList<RSId> getVariablesLocales() {
      return new ArrayList<>(variablesLocales.values());
    }

    public ArrayList<Registro> getCodigo() {
        return codigo;
    }
    public String toString(){
        ArrayList<RSId> variables=getVariablesLocales();
        String res = "FUNCIÓN\n";
        res+="NOMBRE DE FUNCIÓN: "+nombre.getNombre()+"\n";
        
        res+="------------------------------------------------------------------------\n";
        res+="PARÁMETROS:\n";
        for (RSId parametro : parametros) {
            res+=parametro.toString();
        }
        res+="------------------------------------------------------------------------\n";
        res+="VARIABLES LOCALES\n";
        for (RSId variable : variables) {
            res+=variable.toString();
        }
        res+="------------------------------------------------------------------------\n";
        return res;
    }
    public String getNombre(){
        return nombre.getNombre();
    }

    public ArrayList<RSId> getParametros() {
        return parametros;
    }
}
