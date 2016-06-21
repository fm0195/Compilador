
package Semantico;

public abstract class Registro {
    protected int linea;
    private boolean isNegative=false;
    public Registro( int linea) {
        this.linea = linea;
    }
    public int getLinea() {
        return linea;
    }
    public void negative(){
        isNegative=true;
    }
    public boolean isNegative(){
        return isNegative;
    }
    //public abstract String getCodigoASM(); 
}
