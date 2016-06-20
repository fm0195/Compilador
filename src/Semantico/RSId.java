
package Semantico;

public class RSId extends Registro{
    private String tipo;
    private String nombre;
    private boolean isAsignada=false;
    public RSId(String nombre, int linea) {
        super(linea);
        this.tipo = tipo;
        this.nombre=nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getNombre() {
        return nombre;
    }

    public boolean isIsAsignada() {
        return isAsignada;
    }

    public void setAsignada() {
        this.isAsignada = true;
    }
    public String toString(){
        String res="Variable\n";
        res+="Nombre: "+nombre+"\n";
        res+="Tipo: "+tipo+"\n";
        res+="Línea: "+linea+"\n";
        res+="Fue asignada: "+isAsignada+"\n";
        res+="-----------------------------------------------------------------\n";
        return res;
    }
}
