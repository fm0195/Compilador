
package Semantico;

import scanner.IImprimible;

public class ErrorSemantico extends Registro implements IImprimible{
    String error;

    public ErrorSemantico(String error, int linea) {
        super(linea);
        this.error = error;
    }
    @Override
    public String toString(){
        return "Error: "+error+", en la línea: "+super.linea;
    }
}
