/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;


public class Archivo {
    public static File arc1 = new File("Productos.obj");
    public static File arc2 = new File("Envios.obj");
    private static FileInputStream dr = null ;
    private static ObjectInputStream rd = null;
    
    public static int buscarDatosE(String cod, int men, File arc)//Funcion par buscar datos en envios 
    {
        int x;
        Vector pro = new Vector(); 
        boolean verdad = false;        
        EnvioDeProductos aux ;
        pro=cargarDatos(arc);
        for( x=0; x< pro.size(); x++){
            aux = (EnvioDeProductos)pro.get(x);
            if(aux.getCod_envio().equalsIgnoreCase(cod)){
                if (men == 0)
                    aux.imprimirEnvio();
                verdad = true;
                break;
            }
        }        
        if(!verdad){
            x=-1;
        }            
        return x;        
    }
    public static int buscarDatos(String cod, int men, File arc) //Funcion para buscar datos en producto 
    {
        int x;
        Vector pro = new Vector(); 
        boolean verdad = false;        
        Producto aux ;
        pro=cargarDatos(arc);
        for( x=0; x< pro.size(); x++){
            aux = (Producto)pro.get(x);
            if(aux.getCod().equalsIgnoreCase(cod)){
                if (men == 0)
                    aux.imprimirProducto();
                verdad = true;
                break;
            }
        }        
        if(!verdad){
            x=-1;
        }            
        return x;        
    }
    
    public static Vector cargarDatos(File arc) //Funcion para cargar datos en un vector desde el archivo
    {
        Vector pro = new Vector();
        try{
            dr = new FileInputStream(arc);
            rd = new ObjectInputStream(dr);
            pro = (Vector)rd.readObject();            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        } 
        return pro;
    }
    
    public static void guardarDatos(Vector pro, File arc) //Funcion para guardar datos en el archivo
    {
        FileOutputStream rw = null;
        ObjectOutputStream wr = null;
        try{
            rw = new FileOutputStream(arc);
            wr = new ObjectOutputStream(rw);
            wr.writeObject(pro);
        }catch(IOException e1){
            System.err.println(e1.getMessage());
        }finally{
            try{
                if(wr!=null){
                    wr.close();
                    rw.close();
                }                    
            }catch(IOException e2){
                System.err.println(e2.getMessage());
            }
        }
    }
    
}
