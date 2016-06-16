/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import FilesScanner.ManagerFile;
import FilesScanner.Printer;
import Scanner_Jflex.MyPythonScanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import javax.sound.midi.Soundbank;
import parser.Nodo;
import parser.Parser;
import scanner.Token;

/**
 *
 * @author jjime
 */
public class Main {

    /**java -jar java-cup-11b.jar -locations -parser Parser -xmlactions Rules.cup
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      try {
        FileReader fr =null;
        String pathPuntoMyPython="..\\Compilador\\docs\\Prueba 4.mypython";
        if (args.length >= 1) {
          pathPuntoMyPython = args[0];
        }
        fr = new FileReader(new File(pathPuntoMyPython));
        Parser p = new Parser(new MyPythonScanner(fr)); 
        Symbol res = p.parse();
        p.generarCodigo();
        Printer printer=new Printer();
        printer.impimirDatos("Errores Léxicos en el programa", p.erroresLexicos());
        printer.impimirDatos("Errores Sintácticos en el programa", p.erroresSintacticos());
        printer.impimirDatos("Errores Semánticos en el programa", p.erroresSemanticos());
          System.out.println("\n");
          System.out.println("ARBOL GENERADO POR EL PARSER");
        System.out.println(((Nodo)res.value).toString(0));
      } catch (FileNotFoundException ex) {
            System.out.println("Error en el archivo.");
       // Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
      } catch (Exception ex) {
        System.out.println("Error irrecuperable.");
        //ex.printStackTrace();
      }
    }
}