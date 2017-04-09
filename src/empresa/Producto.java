package empresa;

//import static empresa.TestHerencia.arc1;
import static empresa.Archivo.buscarDatos;
import static empresa.Archivo.cargarDatos;
import static empresa.Archivo.guardarDatos;
import static empresa.TestHerencia.ingresarDatos;
import static empresa.TestHerencia.leer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producto implements Serializable {
    private String fec_cad,fec_env,pais_ori, nombre, cod;
    private int num_lot, can;
   

    public String getNombre() {
        return nombre;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }     

    public void setFec_cad(String fec_cad) {
        this.fec_cad = fec_cad;
    }

    public void setFec_env(String fec_env) {
        this.fec_env = fec_env;
    }

    public void setNum_lot(int num_lot) {
        this.num_lot = num_lot;
    }

    public void setPais_ori(String pais_ori) {
        this.pais_ori = pais_ori;
    }
   
    public void imprimirProducto () //Funcion para imprimir los datos del producto 
    {
        System.out.println("\tInformación");    
        System.out.println("Codigo: "+this.cod);
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Cantidad: "+this.can);
        System.out.println("Numero Lote: "+this.num_lot);
        System.out.println("Fecha Envio: "+this.fec_env);
        System.out.println("Fecha Caducidad: "+this.fec_cad);
        System.out.println("Pais Origen: "+this.pais_ori);
    }

    public void eliminarProducto (String cod, File arc) //Funcion para eliminar un producto
    {
        String op;
        int x;
        Vector vec = cargarDatos(arc);
        x = buscarDatos(cod,0,arc);        
        if(x != -1){
            do{                
                System.out.println("Esta seguro de eliminarlo si/no?");
                op = leer.nextLine().toLowerCase();
                if (!op.equalsIgnoreCase("si") && !op.equalsIgnoreCase("no") )
                    System.out.println("Entrada invalida");
            }while(!op.equalsIgnoreCase("si") && !op.equalsIgnoreCase("no"));
            if(op.equalsIgnoreCase("si")){
                vec.remove(x);
                guardarDatos(vec,arc);
                System.out.println("Producto Eliminado");
            }                
            else
                System.out.println("Accion cancelada");            
        }
    }

    public void modificarProducto (String cod,File arc) //Funcion para modificar un producto
    {
        String op, obj;
        int x, pos;
        Producto aux = new Producto();
        Vector vec = cargarDatos(arc);
        x = buscarDatos(cod,0,arc);        
        if(x != -1){
            do{                
                System.out.println("Desea modificarlo si/no?");
                op = leer.nextLine().toLowerCase();
                if (!op.equalsIgnoreCase("si") && !op.equalsIgnoreCase("no") )
                    System.out.println("Entrada invalida");
            }while(!op.equalsIgnoreCase("si") && !op.equalsIgnoreCase("no"));
            
            if(op.equalsIgnoreCase("si")){
                aux = (Producto)vec.get(x);
                aux.setCod("null");
                vec.setElementAt(aux, x);
                guardarDatos(vec,arc);
                obj= aux.getClass().getName();
                if(obj.equalsIgnoreCase("empresa.Frescos"))
                    ingresarDatos(1, 1,1);
                else if (obj.equalsIgnoreCase("empresa.Refrigerado")){
                    ingresarDatos(1, 2,1);
                }
                else if (obj.equalsIgnoreCase("empresa.Aire")){
                    ingresarDatos(2,1,1);
                }else if (obj.equalsIgnoreCase("empresa.Agua")){
                    ingresarDatos(2,2,1);
                }else
                    ingresarDatos(2,3,1);                              
                vec= cargarDatos(arc);
                aux= (Producto)vec.lastElement();
                pos =vec.lastIndexOf(aux);
                vec.setElementAt(aux, x);
                vec.remove(pos);             
                guardarDatos(vec,arc);
                System.out.println("Producto Modificado");
            }                
            else
                System.out.println("Accion cancelada");            
        }
        
    }


}

