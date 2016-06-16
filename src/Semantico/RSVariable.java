
package Semantico;

public class RSVariable extends RegistroExpresion{
    private String nombre;
    private boolean isIndexada=false;
    private RegistroExpresion indice;

    public RSVariable(String nombre, boolean isIndexada, RegistroExpresion indice, int linea, String tipo) {
        super(linea, tipo);
        this.nombre = nombre;
        this.isIndexada = isIndexada;
        this.indice = indice;
    }
    public RSVariable(String nombre, int linea, String tipo) {
        super(linea, tipo);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
