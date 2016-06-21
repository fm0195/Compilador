
package Semantico;

public abstract class RegistroExpresion extends Registro{
    
    private String tipo;

    public RegistroExpresion(int linea,String tipo) {
        super(linea);
        this.tipo=tipo;
    }
    public String getTipo(){
        return tipo;
    }
    
}
