
package Semantico;

import java.util.ArrayList;

public class RSIf extends Registro{
    private ArrayList<Registro> codigo= new ArrayList();
    private RSIf siguienteIF=null;

    public RSIf( int linea,ArrayList<Registro> codigo) {
        super(linea);
        this.codigo=codigo;
    }

    public void setSiguienteIF(RSIf siguienteIF) {
        this.siguienteIF = siguienteIF;
    }
    
}
