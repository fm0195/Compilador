
package Semantico;

import java.util.ArrayList;

public class RSLlamadoFuncion extends Registro{
    private RSId nombre;
    private ArrayList<RegistroExpresion> argumentos;

    public RSLlamadoFuncion(RSId nombre, ArrayList<RegistroExpresion> argumentos, int linea) {
        super(linea);
        this.nombre = nombre;
        this.argumentos = argumentos;
    }
      
    public String nombreFuncion(){
        return nombre.getNombre();
    }
    public int cantidadArgumentos(){
        return getArgumentos().size();
    }

    public ArrayList<RegistroExpresion> getArgumentos() {
        return argumentos;
    }
}
