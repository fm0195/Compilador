
package Semantico;

public abstract class Registro {
    protected int linea;

    public Registro( int linea) {
        this.linea = linea;
    }


    public int getLinea() {
        return linea;
    }
    //public abstract String getCodigoASM(); 
}
