package Semantico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import scanner.IImprimible;

public class Analizador {
    private HashMap<String, RSId> variablesGlobales = new HashMap<>();
    private HashMap<String, Funcion> funciones = new HashMap<>();
    private ArrayList<IImprimible> errores= new ArrayList();
    private Stack<Registro> pilaSemantica= new Stack();
    private ArrayList<Registro> codigoPrincipal= new ArrayList();

    public Analizador() {
    }
    
    public void recordarTipo(String nombre, int linea){
        RSTipo tipo= new RSTipo(nombre, linea);
        pilaSemantica.push(tipo);
    }
    public void recordarID(String nombre, int linea){
        RSId id= new RSId(nombre, linea);
        pilaSemantica.push(id);
    }
    public void recordarLiteral(Object literal, int linea, String tipo){
        RSDataObject objecto=new RSDataObject(linea, literal,tipo);
        pilaSemantica.push(objecto);
    }
    public void validaVariable(){
        if (!(pilaSemantica.peek()instanceof ErrorSemantico)){
            String nombre=((RSVariable)pilaSemantica.peek()).getNombre();
            if (!(variablesGlobales.get(nombre).isIsAsignada())){
                ErrorSemantico e = new ErrorSemantico("variable "+nombre+", no asignada", ((RSVariable)pilaSemantica.peek()).getLinea());
                getErrores().add(e);
                pilaSemantica.pop();
                pilaSemantica.push(e);
            }
        }
    }
    public void ajustarPila(){
        if (!(pilaSemantica.peek()instanceof ErrorSemantico)){
            Registro r = pilaSemantica.remove(pilaSemantica.size()-2);
            pilaSemantica.push(r);
        }
    }
    public void recordarVariable(int linea, String nombre){
        if (variablesGlobales.containsKey(nombre)){
                String tipo = ((RSId)variablesGlobales.get(nombre)).getTipo();
                RSVariable variable=new RSVariable(nombre,linea,tipo);
                pilaSemantica.push(variable);
        }else{
            ErrorSemantico e = new ErrorSemantico(" variable "+nombre+", no definida", linea);
            getErrores().add(e);
            pilaSemantica.push(e);
        }
    }
    public void recordarVariableIndexada(int linea, String nombre){
        if (variablesGlobales.containsKey(nombre)){
                String tipo = ((RSId)variablesGlobales.get(nombre)).getTipo();
                if (ValidarOperacion.getInstance().isIndexable(tipo)){
                    RegistroExpresion expresion =(RegistroExpresion) pilaSemantica.pop();
                    RSVariable variable=new RSVariable(nombre,true,expresion,linea,tipo);
                    pilaSemantica.push(variable);
                }else{
                    ErrorSemantico e = new ErrorSemantico("Error, tipo "+tipo+", no es indexable", linea);
                    getErrores().add(e);
                    pilaSemantica.push(e);
                }
        }else{
            ErrorSemantico e = new ErrorSemantico(" variable "+nombre+", no definida", linea);
            getErrores().add(e);
            pilaSemantica.pop();
            pilaSemantica.push(e);
        }
    }
    public void recordarLista(int linea){
        RSDataObject lista= new RSDataObject(linea,tempLista,"list");
        pilaSemantica.add(lista);
        tempLista= new ArrayList<>();
    }
    public void agregarLista(){
        Registro elemento = pilaSemantica.pop();
        tempLista.add(elemento);
    }
    public void recordarOperador(String valor, int linea){
        RSOperador operador=new RSOperador(valor, true, linea);
        pilaSemantica.push(operador);
    }
    private RSOperador buscarOperador(){
        for(int contador=pilaSemantica.size()-1;contador>=0;contador--){
           if(pilaSemantica.get(contador) instanceof RSOperador){
               return (RSOperador)pilaSemantica.get(contador);
           }
       }
       return null;
    }
    private RSTipo buscarTipo(){
       for(int contador=pilaSemantica.size()-1;contador>=0;contador--){
           if(pilaSemantica.get(contador) instanceof RSTipo){
               return (RSTipo)pilaSemantica.get(contador);
           }
       }
       return null;
    }
    private RSTipo buscarTipoParametro(){
       for(int contador=pilaSemantica.size()-1;contador>=0;contador--){
           if(pilaSemantica.get(contador) instanceof RSTipo){
               return (RSTipo)pilaSemantica.remove(contador);
           }
       }
       return null;
    }
    
    public void crearOperacion(){        
        ErrorSemantico error=errorEnoperacion();
        if (error==null){
            RSOperador operador = (RSOperador) pilaSemantica.pop();
            RegistroExpresion valor2=(RegistroExpresion)pilaSemantica.pop();
            RegistroExpresion valor1=(RegistroExpresion)pilaSemantica.pop();
            Registro operacion;
            if (ValidarOperacion.getInstance().validarOp(valor1.getTipo(), operador.getValor(), valor2.getTipo())){
                String tipo =ValidarOperacion.getInstance().getTipo(valor1.getTipo(), operador.getValor(), valor2.getTipo());
                operacion = new RSOperacion(valor1, valor2, operador,valor1.getLinea(),tipo);
            }else{
                operacion= new ErrorSemantico("no se puede operar con \""+operador.getValor()+"\" los tipos: "+valor1.getTipo()+" y "+valor2.getTipo(), valor1.getLinea());
                errores.add((ErrorSemantico)operacion);
            }
            pilaSemantica.push(operacion);
        }
    }
 
    public void insertarVariablesGlobales(){
            Registro registro= pilaSemantica.pop();
            RSTipo tipo = buscarTipo();
            while(registro instanceof RSId){
                RSId id=(RSId)registro;
                if(variablesGlobales.containsKey(id.getNombre())){
                    ErrorSemantico e= new ErrorSemantico(" variable "+id.getNombre()+" global ya definida", id.getLinea());
                    getErrores().add(e);
                }else{
                    id.setTipo(tipo.getNombre());
                    variablesGlobales.put(id.getNombre(), id);
                }
                registro= pilaSemantica.pop();
            }
    }
        
    private ErrorSemantico errorEnoperacion(){
        ErrorSemantico temp =null;
        for(int counter= pilaSemantica.size()-1;counter>pilaSemantica.size()-4;counter--){
            if (pilaSemantica.get(counter)instanceof ErrorSemantico){
                temp =(ErrorSemantico)pilaSemantica.get(counter);
                pilaSemantica.pop();
                pilaSemantica.pop();
                pilaSemantica.pop();
                pilaSemantica.push(temp);
                break;
            }
        }
        return temp;
    }
    public void recordarAutoAsignador(String operador, int linea){
        autoAsignacion=true;
        recordarOperador(operador, linea);
        tempAutoOperador=(RSOperador)pilaSemantica.pop();
        
    }
    public void agregarAsignacion(){
        if (!(pilaSemantica.peek() instanceof ErrorSemantico)){
            RSVariable var = (RSVariable)pilaSemantica.pop();
            if (variablesGlobales.containsKey(var.getNombre())){
              if (!(pilaSemantica.peek() instanceof ErrorSemantico)){
                    if(autoAsignacion){
                        if (variablesGlobales.get(var.getNombre()).isIsAsignada()){
                            pilaSemantica.push(var);
                            pilaSemantica.push(tempAutoOperador);
                            autoAsignacion=false;
                            crearOperacion();
                         }else{
                            ErrorSemantico e = new ErrorSemantico(" variable "+var.getNombre()+", no asignada", var.getLinea());
                            getErrores().add(e);
                            pilaSemantica.push(e);
                            return;
                        }
                    }
                    if (variablesGlobales.get(var.getNombre()).getTipo()==((RegistroExpresion)pilaSemantica.peek()).getTipo()){
                        variablesGlobales.get(var.getNombre()).setAsignada();
                        Registro asignacion= new RSAsignacion( pilaSemantica.pop(),var, var.getLinea());
                        pilaSemantica.push(asignacion);
                    }else{
                        ErrorSemantico e = new ErrorSemantico(" variable "+var.getNombre()+", tipo: "+variablesGlobales.get(var.getNombre()).getTipo()+
                                                    ", no puede ser asignada con un tipo: "+((RegistroExpresion)pilaSemantica.peek()).getTipo(), var.getLinea());
                        getErrores().add(e);
                        pilaSemantica.push(e);
                    }
              }
            }else{
                ErrorSemantico e = new ErrorSemantico(" variable "+var.getNombre()+", no definida", var.linea);
                getErrores().add(e);
                pilaSemantica.push(e);
            }
        }
    }
    public void agregarCodigo(){
        if ((pilaSemantica.peek() instanceof ErrorSemantico)){
            pilaSemantica.pop();
        }else{
            codigoPrincipal.add(pilaSemantica.pop());
        }
    }
    public void agregarParametros(){
        while(!pilaSemantica.isEmpty()){
            RSId id= (RSId)pilaSemantica.pop();
            if (validaParametro(id.getNombre())) {
                RSTipo tipo= buscarTipoParametro();
                id.setTipo(tipo.getNombre());
                parametrosFuncionTemp.add(id);
            }else{
                tempParametrosIncorrecta=true;
                ErrorSemantico e = new ErrorSemantico(" parametro "+id.getNombre()+", ya definido", id.linea);
                getErrores().add(e);
                pilaSemantica.pop();
            }
        }
    }
    public void crearFuncion(){
        RSId nombre =(RSId) pilaSemantica.pop();
        if(!(funciones.containsKey(nombre.getNombre()))){
            if (!tempParametrosIncorrecta){
                Funcion funcion = new Funcion(nombre, parametrosFuncionTemp, variablesGlobales,getCodigo());
                parametrosFuncionTemp= new ArrayList<>();
                variablesGlobales=new HashMap<>();
                tempParametrosIncorrecta=false;
                funciones.put(nombre.getNombre(), funcion);
            }
        }else{
            ErrorSemantico e = new ErrorSemantico(" función "+nombre.getNombre()+", ya definida", nombre.linea);
            getErrores().add(e);
        }
    }
    private boolean validaParametro(String id){
        for (RSId paraId : parametrosFuncionTemp) {
            if(paraId.getNombre().equals(id)){
                return false;
            }
        }
        return true;
    }
    private ArrayList<Registro> getCodigo(){
        ArrayList<Registro> Resultado=new  ArrayList<>();
        while (!pilaSemantica.isEmpty()) {
            Resultado.add(pilaSemantica.pop());
        }
        return Resultado;
    }
    public ArrayList<IImprimible> getErrores() {
        return errores;
    }
    public void agregarArgumento(){
        RegistroExpresion expresion=(RegistroExpresion)pilaSemantica.pop();
        argumentoTempLLamadoFuncion.add(expresion);
    }
    public void crearLlamdoFuncion(String nombre, int linea){
        RSId id = new RSId(nombre, linea);
        RSLlamadoFuncion llamdo = new RSLlamadoFuncion(id, argumentoTempLLamadoFuncion, linea);
        llamadosFuncion.add(llamdo);
        argumentoTempLLamadoFuncion=new ArrayList<>();
    }
    public void validarLlamdos(){
        for(RSLlamadoFuncion llamado:llamadosFuncion){
            if (funciones.containsKey(llamado.nombreFuncion())){
                Funcion f = funciones.get(llamado.nombreFuncion());
                if (f.getTotalParametros()==llamado.cantidadArgumentos()){
                    for(int counter =0; counter<llamado.cantidadArgumentos(); counter++){
                        if (!(f.validaparametro(counter, llamado.getArgumentos().get(counter)))){
                            ErrorSemantico e = new ErrorSemantico(" llamado de función "+llamado.nombreFuncion()+
                                    " no coinciden los tipos en los argumetos", llamado.getLinea());
                            getErrores().add(e);
                            break;
                        }
                    }
                }
                else{
                    ErrorSemantico e = new ErrorSemantico(" llamado de función "+llamado.nombreFuncion()+", posee cantidad"
                            + " errónea de argumentos", llamado.getLinea());
                getErrores().add(e);
                }
            }else{
                ErrorSemantico e = new ErrorSemantico(" función "+llamado.nombreFuncion()+", no definida", llamado.getLinea());
                getErrores().add(e);
            }
        }
    }
    private ArrayList<Registro> tempLista= new ArrayList();
    private boolean autoAsignacion= false;
    private boolean tempParametrosIncorrecta= false;
    private RSOperador tempAutoOperador;
    private ArrayList<RSId> parametrosFuncionTemp=new ArrayList<>();
    private ArrayList<RegistroExpresion> argumentoTempLLamadoFuncion= new ArrayList<>();
    private ArrayList<RSLlamadoFuncion> llamadosFuncion= new ArrayList<>();
}
