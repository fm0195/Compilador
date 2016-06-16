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
        this.nombre = this.nombre;
        this.parametros=parametros;
        this.variablesLocales=variablesLocales;
        this.codigo=codigo;
    }  
    public int getTotalParametros(){
        return parametros.size();
    }
    public boolean validaparametro(int numero, RegistroExpresion param){
        return parametros.get(numero).getTipo()==param.getTipo();
    }
}
