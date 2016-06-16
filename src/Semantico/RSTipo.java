package Semantico;

public class RSTipo extends Registro{

    private String nombre;
    public RSTipo(String nombre, int linea) {
        super(linea);
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
