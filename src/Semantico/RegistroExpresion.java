
package Semantico;

public abstract class RegistroExpresion extends Registro{
    
    private String tipo;
    private boolean isNegative=false;
    public RegistroExpresion(int linea,String tipo) {
        super(linea);
        this.tipo=tipo;
    }
    public String getTipo(){
        return tipo;
    }
    public void negative(){
        isNegative=true;
    }
    public boolean getIsNegative(){
        return isNegative;
    }
}
