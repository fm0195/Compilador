
package Semantico;

import java.util.HashMap;

public class ValidarOperacion {

    public static ValidarOperacion getInstance() {
        return instance;
    }
    private HashMap<String, HashMap<String,HashMap<String, HashMap<String, String>>>> MapaTiposOperadores=new HashMap<>();
    private HashMap<String,Boolean> indexable = new HashMap<>();
    private static ValidarOperacion instance=new ValidarOperacion();
    private  ValidarOperacion(){
        /*String*/
        HashMap<String, String> operador2String=new HashMap<>();
        operador2String.put("string", "string");
        operador2String.put("char", "string");
        HashMap<String,HashMap<String, String>> operadoresString =new HashMap<>();
        
        operadoresString.put("+",operador2String);
        
        operador2String=new HashMap<>();
        operador2String.put("int", "string");
        operador2String.put("int", "boolean");
        operadoresString.put("*",operador2String);
        
        operador2String=new HashMap<>();
        operador2String.put("string", "boolean");
        operador2String.put("boolean", "boolean");
        operador2String.put("int", "boolean");
        operador2String.put("float", "boolean");
        operador2String.put("list", "boolean");
        
        operadoresString.put("==",operador2String);
        operadoresString.put("and",operador2String);
        operadoresString.put("or",operador2String);
        operadoresString.put("!=",operador2String);
        operadoresString.put("<>",operador2String);
        operadoresString.put(">=",operador2String);
        operadoresString.put("<=",operador2String);
        operadoresString.put(">",operador2String);
        operadoresString.put("<",operador2String);
        operadoresString.put("|",operador2String);
        operadoresString.put("&",operador2String);
        
        HashMap<String,HashMap<String,HashMap<String, String>>> stringType = new HashMap<>();
        stringType.put("string", operadoresString);
        
        /*Char*/
        HashMap<String, String> operador2Char=new HashMap<>();
        operador2Char.put("string", "string");
        operador2Char.put("char", "string");
        HashMap<String,HashMap<String, String>> operadoresChar =new HashMap<>();
        
        operadoresChar.put("+",operador2Char);
        
        operador2Char=new HashMap<>();
        operador2Char.put("int", "string");
        operador2Char.put("boolean", "string");
        operadoresChar.put("*",operador2Char);
        
        operador2Char=new HashMap<>();
        operador2Char.put("string", "boolean");
        operador2Char.put("boolean", "boolean");
        operador2Char.put("int", "boolean");
        operador2Char.put("float", "boolean");
        operador2Char.put("list", "boolean");
        
        operadoresChar.put("==",operador2Char);
        operadoresChar.put("and",operador2Char);
        operadoresChar.put("or",operador2Char);
        operadoresChar.put("!=",operador2Char);
        operadoresChar.put("<>",operador2Char);
        operadoresChar.put(">=",operador2Char);
        operadoresChar.put("<=",operador2Char);
        operadoresChar.put(">",operador2Char);
        operadoresChar.put("<",operador2Char);
        operadoresChar.put("|",operador2Char);
        operadoresChar.put("&",operador2Char);
        
        HashMap<String,HashMap<String,HashMap<String, String>>> charType = new HashMap<>();
        charType.put("char", operadoresChar);
        
        /*LIST*/
        HashMap<String, String> operador2List=new HashMap<>();
        operador2List.put("list", "list");
        HashMap<String,HashMap<String, String>> operadoresList =new HashMap<>();
        
        operadoresList.put("+",operador2List);
        
        operador2List=new HashMap<>();
        operador2List.put("int", "list");
        operadoresList.put("*",operador2List);
        
        operador2List=new HashMap<>();
        operador2List.put("string", "boolean");
        operador2List.put("char", "boolean");
        operador2List.put("boolean", "boolean");
        operador2List.put("int", "boolean");
        operador2List.put("float", "boolean");
        operador2List.put("list", "boolean");
        
        operadoresList.put("==",operador2List);
        operadoresList.put("and",operador2List);
        operadoresList.put("or",operador2List);
        operadoresList.put("!=",operador2List);
        operadoresList.put("<>",operador2List);
        operadoresList.put(">=",operador2List);
        operadoresList.put("<=",operador2List);
        operadoresList.put(">",operador2List);
        operadoresList.put("<",operador2List);
        operadoresList.put("|",operador2List);
        operadoresList.put("&",operador2List);
        
        HashMap<String,HashMap<String,HashMap<String, String>>> lisType = new HashMap<>();
        lisType.put("list", operadoresList);
        
        
        /*Boolean*/
        HashMap<String, String> operador2Boolean=new HashMap<>();
        operador2Boolean.put("boolean", "int");
        operador2Boolean.put("int", "int");
        operador2Boolean.put("float", "float");
        
        HashMap<String,HashMap<String, String>> operadoresBoolean =new HashMap<>();
        
        operadoresBoolean.put("+",operador2Boolean);
        operadoresBoolean.put("-",operador2Boolean);
        operadoresBoolean.put("*",operador2Boolean);
        operadoresBoolean.put("/",operador2Boolean);
        operadoresBoolean.put("//",operador2Boolean);
        operadoresBoolean.put("**",operador2Boolean);
        operadoresBoolean.put("%",operador2Boolean);
        
        operador2Boolean=new HashMap<>();
        operador2Boolean.put("list", "list");
        operador2Boolean.put("string", "string");
        operador2Boolean.put("char", "char");
        operador2Boolean.put("boolean", "int");
        operador2Boolean.put("int", "int");
        operador2Boolean.put("float", "float");
        
        operadoresBoolean.put("*",operador2Boolean);
        
        operador2Boolean=new HashMap<>();
        operador2Boolean.put("boolean", "int");
        operador2Boolean.put("int", "int");
        
        operadoresBoolean.put("<<",operador2Boolean);
        operadoresBoolean.put(">>",operador2Boolean);       
        
        operador2Boolean=new HashMap<>();
        operador2Boolean.put("string", "boolean");
        operador2Boolean.put("char", "boolean");
        operador2Boolean.put("boolean", "boolean");
        operador2Boolean.put("int", "boolean");
        operador2Boolean.put("float", "boolean");
        operador2Boolean.put("list", "boolean");
        
        operadoresBoolean.put("==",operador2Boolean);
        operadoresBoolean.put("and",operador2Boolean);
        operadoresBoolean.put("or",operador2Boolean);
        operadoresBoolean.put("!=",operador2Boolean);
        operadoresBoolean.put("<>",operador2Boolean);
        operadoresBoolean.put(">=",operador2Boolean);
        operadoresBoolean.put("<=",operador2Boolean);
        operadoresBoolean.put(">",operador2Boolean);
        operadoresBoolean.put("<",operador2Boolean);
        operadoresBoolean.put("|",operador2Boolean);
        operadoresBoolean.put("&",operador2Boolean);
        
        HashMap<String,HashMap<String,HashMap<String, String>>> booleanType = new HashMap<>();
        booleanType.put("boolean", operadoresBoolean);
        
        /*Float*/
        HashMap<String, String> operador2Float=new HashMap<>();
        operador2Float.put("boolean", "float");
        operador2Float.put("int", "float");
        operador2Float.put("float", "float");
        
        HashMap<String,HashMap<String, String>> operadoresFloat =new HashMap<>();
        
        operadoresFloat.put("+",operador2Float);
        operadoresFloat.put("-",operador2Float);
        operadoresFloat.put("*",operador2Float);
        operadoresFloat.put("/",operador2Float);
        operadoresFloat.put("//",operador2Float);
        operadoresFloat.put("**",operador2Float);
        operadoresFloat.put("%",operador2Float);      
        
        operador2Float=new HashMap<>();
        operador2Float.put("string", "boolean");
        operador2Float.put("char", "boolean");
        operador2Float.put("boolean", "boolean");
        operador2Float.put("int", "boolean");
        operador2Float.put("float", "boolean");
        operador2Float.put("list", "boolean");
        
        operadoresFloat.put("==",operador2Float);
        operadoresFloat.put("and",operador2Float);
        operadoresFloat.put("or",operador2Float);
        operadoresFloat.put("!=",operador2Float);
        operadoresFloat.put("<>",operador2Float);
        operadoresFloat.put(">=",operador2Float);
        operadoresFloat.put("<=",operador2Float);
        operadoresFloat.put(">",operador2Float);
        operadoresFloat.put("<",operador2Float);
        operadoresFloat.put("|",operador2Float);
        operadoresFloat.put("&",operador2Float);
        
        HashMap<String,HashMap<String,HashMap<String, String>>> floatType = new HashMap<>();
        floatType.put("float", operadoresFloat);
        
        
        /*Int*/
        HashMap<String, String> operador2Int=new HashMap<>();
        operador2Int.put("boolean", "int");
        operador2Int.put("int", "int");
        operador2Int.put("float", "float");
        
        HashMap<String,HashMap<String, String>> operadoresInt =new HashMap<>();
        
        operadoresInt.put("+",operador2Int);
        operadoresInt.put("-",operador2Int);
        operadoresInt.put("/",operador2Int);
        operadoresInt.put("//",operador2Int);
        operadoresInt.put("**",operador2Int);
        operadoresInt.put("%",operador2Int);
        
        operador2Int=new HashMap<>();
        operador2Int.put("list", "list");
        operador2Int.put("string", "string");
        operador2Int.put("char", "string");
        operador2Int.put("boolean", "int");
        operador2Int.put("int", "int");
        operador2Int.put("float", "float");
        
        operadoresInt.put("*",operador2Int);
        
        operador2Int=new HashMap<>();
        operador2Int.put("boolean", "int");
        operador2Int.put("int", "int");
        
        operadoresInt.put("<<",operador2Int);
        operadoresInt.put(">>",operador2Int);       
        
        operador2Int=new HashMap<>();
        operador2Int.put("string", "boolean");
        operador2Int.put("char", "boolean");
        operador2Int.put("boolean", "boolean");
        operador2Int.put("int", "boolean");
        operador2Int.put("float", "boolean");
        operador2Int.put("list", "boolean");
        
        operadoresInt.put("==",operador2Int);
        operadoresInt.put("and",operador2Int);
        operadoresInt.put("or",operador2Int);
        operadoresInt.put("!=",operador2Int);
        operadoresInt.put("<>",operador2Int);
        operadoresInt.put(">=",operador2Int);
        operadoresInt.put("<=",operador2Int);
        operadoresInt.put(">",operador2Int);
        operadoresInt.put("<",operador2Int);
        operadoresInt.put("|",operador2Int);
        operadoresInt.put("&",operador2Int);
        
        HashMap<String,HashMap<String,HashMap<String, String>>> intType = new HashMap<>();
        intType.put("int", operadoresInt);
        
        
        MapaTiposOperadores.put("string", stringType);
        MapaTiposOperadores.put("char",charType);
        MapaTiposOperadores.put("list", lisType);
        MapaTiposOperadores.put("boolean", booleanType);
        MapaTiposOperadores.put("float", floatType);
        MapaTiposOperadores.put("int", intType);
        
        indexable.put("String",true);
        indexable.put("list",true);
        
    }
    public boolean validarOp(String valor1, String operador, String valor2){
        HashMap<String,HashMap<String,HashMap<String, String>>> type= MapaTiposOperadores.get(valor1);
        if (type.get(valor1).containsKey(operador)){
            HashMap<String, String> operador2=type.get(valor1).get(operador);
                return operador2.containsKey(valor2);
        }
        return false;
    }
    public String getTipo(String valor1, String operador, String valor2){
        return MapaTiposOperadores.get(valor1).get(valor1).get(operador).get(valor2);
    }
    public boolean isIndexable(String tipo){
        return indexable.containsKey(tipo);
    }
}
