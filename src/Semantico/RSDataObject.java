
package Semantico;

public class RSDataObject extends RegistroExpresion{
    
    private Object valor;
    public RSDataObject(int linea,Object valor,String tipo) {
        super(linea,tipo);
        this.valor=valor;
    }
    public RSDataObject(int linea,String tipo) {
        super(linea,tipo);
    }
    
    public Object getValor() {
        return valor;
    }
}
