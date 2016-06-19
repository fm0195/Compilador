

package Semantico;

import java.util.ArrayList;

public class RSIf extends Registro{
    private ArrayList<Registro> codigo= new ArrayList();
    private RSIf siguienteIF=null;
    private boolean isElse;
    
    public RSIf( int linea,ArrayList<Registro> codigo) {
        super(linea);
        this.codigo=codigo;
    }

    public void setSiguienteIF(RSIf siguienteIF) {
        this.siguienteIF = siguienteIF;
    }

    public RSIf getSiguienteIF() {
        return siguienteIF;
    }

    public ArrayList<Registro> getCodigo() {
        return codigo;
    }

    public boolean isIsElse() {
        return isElse;
    }

    public void setIsElse(boolean isElse) {
        this.isElse = isElse;
    }
    
}
