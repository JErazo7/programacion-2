package empresa;



import static empresa.Archivo.arc1;
import static empresa.Archivo.buscarDatosE;
import static empresa.Archivo.cargarDatos;
import static empresa.Archivo.guardarDatos;
import static empresa.TestHerencia.leer;
import static empresa.TestHerencia.menuIngresoEnvio;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class EnvioDeProductos implements Serializable {
    

    private String cod_envio;
    private String med_envio;
    private Vector productos;

    public void imprimirEnvio(){
        Producto pro;
        System.out.println("\tInformacion Envio");
        System.out.println("Codigo del envio: "+this.cod_envio);
        System.out.println("Medio: "+this.med_envio);
        System.out.println("Codigo: \tProducto: \tCantidad");
        for(int x=0; x < productos.size(); x++){
            pro = (Producto)productos.get(x);
            System.out.println(pro.getCod()+"\t\t"+pro.getNombre()+"\t\t"+pro.getCan());
        }     
    }
    
    public String getCod_envio() {
        return cod_envio;
    }

    public void setCod_envio(String cod_envio) {
        this.cod_envio = cod_envio;
    }

    public void setMed_envio(String med_envio) {
        this.med_envio = med_envio;
    }

    public void setProductos(Vector productos) {
        this.productos = productos;
    }  
    
    public void eliminarEnvio(String cod, File arc) //Funcion para eliminar un envio
    {
        String op;
        int x;
        Vector vec = cargarDatos(arc);
        x = buscarDatosE(cod,0,arc);        
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
                System.out.println("Envio eliminado");
            }                
            else
                System.out.println("Accion cancelada");            
        }
    }   

    public void modificarEnvio(String cod,File arc) //funcion para modificar un envio
    {
        Producto pro;
        String op, obj,codi;
        int x, pos, can;
        EnvioDeProductos aux;
        Vector vec = cargarDatos(arc),vec1 = null;
        x = buscarDatosE(cod,0,arc);        
        if(x != -1){
            do{                
                System.out.println("Desea modificarlo si/no?");
                op = leer.nextLine().toLowerCase();
                if (!op.equalsIgnoreCase("si") && !op.equalsIgnoreCase("no") )
                    System.out.println("Entrada invalida");
            }while(!op.equalsIgnoreCase("si") && !op.equalsIgnoreCase("no"));
            
            if(op.equalsIgnoreCase("si")){
                aux = (EnvioDeProductos)vec.get(x);
                aux.setCod_envio("null");
                vec.setElementAt(aux, x);
//                for(int y=0; y < this.productos.size(); y++){
//                    pro = (Producto)productos.get(y);
//                    can = pro.getCan();
//                    codi = pro.getCod();
//                    vec1 = cargarDatos(arc1);
//                    for(int z=0; z < vec1.size();z++){
//                        pro =(Producto)vec1.get(z);
//                        if (pro.getCod().equalsIgnoreCase(codi)){
//                            pro.setCan(can);
//                            vec1.setElementAt(pro, z);
//                        }
//                            
//                    }         
//                }     
//                guardarDatos(vec1,arc1);
                guardarDatos(vec,arc);
                menuIngresoEnvio();                              
                vec= cargarDatos(arc);
                aux= (EnvioDeProductos)vec.lastElement();
                pos =vec.lastIndexOf(aux);
                vec.setElementAt(aux, x);
                vec.remove(pos);             
                guardarDatos(vec,arc);
                System.out.println("Envio Modificado");
            }                
            else
                System.out.println("Accion cancelada");            
        }

    }
}

