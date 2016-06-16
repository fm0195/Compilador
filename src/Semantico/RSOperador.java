
package Semantico;

public class RSOperador extends Registro{
    private String valor;
    private boolean isAritmetico;

    public RSOperador(String valor, boolean isAritmetico, int linea) {
        super(linea);
        this.valor = valor;
        this.isAritmetico = isAritmetico;
    }

    public String getValor() {
        return valor;
    }

    public boolean isIsAritmetico() {
        return isAritmetico;
    }
    
    
}
