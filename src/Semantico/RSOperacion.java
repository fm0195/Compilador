
package Semantico;

public class RSOperacion extends RegistroExpresion{
    
    private Registro valor1;
    private Registro valor2;
    private RSOperador operador;
    
    public RSOperacion(Registro valor1, Registro valor2, RSOperador operador, int linea,String tipo) {
        super(linea,tipo);
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.operador = operador;
    }
    
}
