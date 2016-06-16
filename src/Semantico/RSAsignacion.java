
package Semantico;


public class RSAsignacion extends RegistroExpresion{
   private Registro expresion;
   private RSVariable id;

    public RSAsignacion(Registro expresion, RSVariable id, int linea) {
        super(linea,id.getTipo());
        this.expresion = expresion;
        this.id = id;
    }

    public Registro getExpresion() {
        return expresion;
    }

    public String getId() {
        return id.getNombre();
    }
}
