package parser;

import java.util.ArrayList;
import scanner.IImprimible;
import scanner.Token;


public class Nodo implements IImprimible{
    ArrayList<Nodo> hijos =new ArrayList<Nodo>();
    String valor;
    private boolean error;
    
    public Nodo(String valor, Nodo... nodos) {
        this.valor = valor;
        for (Nodo nodo : nodos) {
            hijos.add(nodo);
        }
    }
    public Nodo(String valor){
        this.valor=valor;
    }
    public Nodo(){
    }
    public boolean isError(){
        return error;
    }    
    public void setError(boolean error) {
        this.error = error;
    }
    public String toString(int cantidad){
        String resultado="";
        resultado+=this.valor == null ? "" : repeat(" ", cantidad)+ "|->" + this.valor +"\n";
        for (Nodo hijo : hijos) {
            if (hijo!=null){
                resultado+=hijo.toString(cantidad+2);
            }
        }
        return resultado;
    }
    private String repeat(String caracter,int cantidad){
        String resultado="";
        for (int contador = 0; contador < cantidad; contador++) {
            resultado+=caracter;
        }
        return resultado;
    }
    @Override
    public String toString(){
        return valor;
    }
}