
package empresa;

import static empresa.Archivo.arc1;
import static empresa.Archivo.arc2;
import static empresa.Archivo.buscarDatos;
import static empresa.Archivo.buscarDatosE;
import static empresa.Archivo.cargarDatos;
import static empresa.Archivo.guardarDatos;
import java.io.File;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestHerencia {
    public static Scanner leer = new Scanner(System.in);
    
    public static void main(String[] args) { 
       try{//Comprueba la existencia de los archivos y los crea si no existen
            if(!arc1.exists())
                arc1.createNewFile();
            if(!arc2.exists())
                arc2.createNewFile();
        }catch(IOException e1){
            System.err.println(e1.getMessage());
        }
        menuPrograma();
    } 
    
    public static double validarNumero()//Funcion para validar en caso de que se lea una letra en vez de numero
    {
        double num = 0;
        boolean verdad; 
        do{
            try{  
            verdad = true;
            num=leer.nextDouble();
            }catch(InputMismatchException e1){
                verdad = false;
                System.err.println("Error, ingrese nuevamente:");
                leer.nextLine();
            }
        }while(!verdad);
        return num;
    }
    public static String validarCodigo(File arc)//Funcion para validar que el codigo no se repita en los productos o envios
    {
        int x;
        String cod;
        leer.nextLine();
        do{
            System.out.println("Codigo:");
            cod = leer.nextLine();
            if (arc.getName().equalsIgnoreCase("Productos.obj"))
                x = buscarDatos(cod,1,arc);
            else
                x = buscarDatosE(cod,1,arc);
            if(x != -1)
                System.out.println("Codigo invalido, ya existe");
         }while(x!=-1);
        return cod;
    }

    public static String validarFecha(String formato, String mensaje) //Funcion para validar el ingreso correcto de las fechas
    {         
       Date fec = null; 
       String fec_en = null;
       boolean verdad = false; 
       do{
            try{
                System.out.println(mensaje);
                fec_en = leer.nextLine();
                fec = new SimpleDateFormat ("dd/MM/yyyy").parse(fec_en); //Convierte la fecha de String a Date
                String fec_sal = new SimpleDateFormat ("dd/MM/yyyy").format(fec);//Covierte la fecha de Date a String 
                if (fec_en.equals(fec_sal)) //Si la fecha de entrada es igual a la de salida
                    verdad = true;                                      // y si la fecha es igual o anterior a la f. actual                            
                else
                    System.out.println("Fecha invalida");
           }catch (Exception e1){  
               System.out.println("Error:"+ e1);
           }
       }while(!verdad);
       return fec_en;
    }    
    
    public static String validarFechaCaducidad(String fec_env)//Funcion para validar que la fecha de caducidad sea despues que la de envasado
    { 
       Date fec1 = null, fec2=null;                             
       String fec_sal = null;
       boolean verdad = false; 
       do{
            try{
                fec_sal=validarFecha("dd/MM/yyyy","Fecha Caducidad dd/MM/yyyy:");
                fec1 = new SimpleDateFormat ("dd/MM/yyyy").parse(fec_env); //Convierte la fecha de String a Date
                fec2 = new SimpleDateFormat ("dd/MM/yyyy").parse(fec_sal);
                if (fec2.after(fec1))
                    verdad = true;                                   // y si la fecha es igual o anterior a la f. actual                            
                else
                    System.out.println("Fecha de caducidad invalida");
           }catch (Exception e1){  
               System.out.println("Error:"+ e1);
           }
       }while(!verdad);
       return fec_sal ;
    }
    
    public static double validarRango(String mensaje, int x, int y)//Funcion para ue valida el rango de un numero
    {
        double op;
        do{
            if(!mensaje.equalsIgnoreCase(""))
                System.out.println(mensaje);        
            op = validarNumero();
            if(op < x || op > y)
                System.out.println("Valor invalido");
        }while(op < x || op > y);
        return op;
    }
    
    public static void ingresarDatos(int men, int op, int can)//Funcion para ingresar los datos del produducto
    {        
        int num_lot, num, ca;
        double tem_rec;
        boolean verdad = false;
        String fec_env, fec_cad, pais, cod_sup, nombre,cod;
        //Declaracion y creacion de objetos
        Frescos fre = new Frescos();
        Refrigerado ref = new Refrigerado();
        Agua agu = new Agua();
        Nitrogeno nit = new Nitrogeno();
        Aire air = new Aire();
        //Ingreso de datos de acuerdo al numero a ingresar
        for(int x=0; x < can; x++){
            Vector pro = cargarDatos(arc1); // vector axuliar que se carga con los datos del fichero
            cod = validarCodigo(arc1);                        
            System.out.println("Nombre:");
            nombre = leer.nextLine();
            ca=(int)validarRango("Cantidad: ",1,100);
            System.out.println("Número Lote: ");
            num_lot = (int)validarNumero();
            leer.nextLine();
            fec_env = validarFecha("dd/MM/yyyy","Fecha Envasado en dd/MM/yyyy : ");     
            fec_cad = validarFechaCaducidad(fec_env);
            System.out.println("Pais Origen: ");
            pais = leer.nextLine();     
            if (men ==1)
                switch(op){
                    case 1:{
                        //Construccion de los productos frescos
                        fre.setCan(ca);
                        fre.setCod(cod);
                        fre.setNombre(nombre);
                        fre.setNum_lot(num_lot);
                        fre.setFec_cad(fec_cad);
                        fre.setFec_env(fec_env);
                        fre.setPais_ori(pais);
                        pro.add(fre);
                        break;
                    }
                    case 2:{
                        //Construccion de los productos refrigerados
                        ref.setCan(ca);
                        ref.setCod(cod);
                        ref.setNombre(nombre);
                        ref.setNum_lot(num_lot);
                        ref.setFec_cad(fec_cad);
                        ref.setFec_env(fec_env);
                        ref.setPais_ori(pais);
                        System.out.println("Cod. Supervicion: ");
                        ref.setCod_super(leer.nextLine());
                        ref.setTemp_recom(validarRango("Tem. Recomendada: ",-20,100));
                        pro.add(ref);
                        break;
                    }
                }
            else{
                System.out.println("Temp. Recomendada: ");
                switch(op){
                    case 1:{
                        //Construccion de los productos de aire
                        air.setCan(ca);
                        air.setCod(cod);
                        air.setNombre(nombre);
                        air.setCod(cod);
                        air.setFec_cad(fec_cad);
                        air.setFec_env(fec_env);
                        air.setNum_lot(num_lot);
                        air.setPais_ori(pais);
                        air.setTemp_recom(validarRango("",0,100));
                        air.setPor_nitro(validarRango("% Nitrogeno:",0,100));
                        air.setPor_oxige(validarRango("% Oxigeno:",0,100));
                        air.setPor_co2(validarRango("% CO2: ",0,100));
                        air.setPor_vapAgua(validarRango("% Vap. Agua: ",0,100));
                        pro.add(air);
                        break;
                    }
                    case 2:{
                        //Construccion de los productos de agua
                        agu.setCan(ca);
                        agu.setNombre(nombre);
                        agu.setCod(cod);
                        agu.setFec_cad(fec_cad);
                        agu.setFec_env(fec_env);
                        agu.setNum_lot(num_lot);
                        agu.setPais_ori(pais);
                        agu.setTemp_recom(validarRango("",0,100));
                        agu.setSalinidad(validarRango("Salinidad: ",0,100));
                        pro.add(agu);
                        break;                        
                    }
                    case 3:{
                        //Construccion de los productos de nitrogeno
                        nit.setCan(ca);
                        nit.setCod(cod);
                        nit.setNombre(nombre);
                        nit.setFec_cad(fec_cad);
                        nit.setFec_env(fec_env);
                        nit.setNum_lot(num_lot);
                        nit.setPais_ori(pais);
                        nit.setTemp_recom(validarRango("",0,100));
                        leer.nextLine();
                        System.out.println("Metodo: ");
                        nit.setMetodo(leer.nextLine());
                        nit.setTiem_exp(validarRango("Tie. Exposicion en horas: ",0,100));
                        pro.add(nit);
                        break;
                    }
                }
            }
            guardarDatos(pro,arc1); //Guarda los datos actualizados al fichero
            System.out.println("Producto ingresado con éxito");
        }
        
        
        
    }
    
    public static void menuPrograma() //Funcion para manejar el menu del programa
    {
        int op;
        Producto pr1= new Producto();
        System.out.println("\tGestion de empresa");
        System.out.println("1)Realizar Envio");
        System.out.println("2)Ingresar Producto");
        System.out.println("3)Aministrar");
        System.out.println("4)Salir");
        op = (int)validarRango("Opcion:",1,4);
        switch(op){
            case 1:{
                menuIngresoEnvio();
                break;
            }
            case 2: {
                menuIngreso();
                break;
            }
            case 3:{
                menuAdministracion();
                break;
                    
            }
            case 4:{
                break;
            }
            default:
                break;
        }  
    }
    
    public static void menuIngreso() //Funcion para manejar los menus de los ingresos
    {        
        int op,num = 0;
        System.out.println("\tTipo de productos");
        System.out.println("1)Productos Frescos");
        System.out.println("2)Productos Refrigerados");
        System.out.println("3)Productos Congelados");
        System.out.println("4)Regresar");
        op = (int)validarRango("Opcion:",1,4);
        if (op !=3 && op != 4)
            num = (int)validarRango("Cantidad a ingresar:",1,10);
        switch(op){
            case 1:{                
                ingresarDatos(1,op,num);
                menuIngreso();
                break;
            }
            case 2: {
                ingresarDatos(1,op,num);
                menuIngreso();
                break;
            }
            case 3:{
                System.out.println("\tPoductos Congelados");
                System.out.println("1)Por Aire");
                System.out.println("2)Por Agua");
                System.out.println("3)Por Nitrogeno");
                System.out.println("4)Regresar");
                op = (int)validarRango("Opcion:",1,4);
                if(op!=4)
                    num = (int)validarRango("Cantidad a ingresar:",1,10);
                switch(op){
                    case 1:{                        
                        ingresarDatos(2,op,num);
                        menuIngreso();
                        break;                        
                    }
                    case 2:{
                        ingresarDatos(2,op,num);
                        menuIngreso();
                        break;
                    }
                    case 3:{
                        ingresarDatos(2,op,num);
                        menuIngreso();
                        break;
                    }                    
                    default :{
                        menuIngreso();
                    }
                }
            }
            default:{
                menuPrograma();
            }
                
        }
    }           
    
    public static void menuAdministracion() //Funcion para manejar el menu de administracion
    {
        int op, op1 = 0;
        Producto pr1= new Producto();
        EnvioDeProductos env1 = new EnvioDeProductos();
        String cod = null;
        System.out.println("\tAdministrar");
        System.out.println("1)Envios");
        System.out.println("2)Productos");
        System.out.println("3)Regresar");
        op = (int)validarRango("Opcion:",1,3);
        if(op!=3){
            System.out.println("\tOperaciones");
            System.out.println("1)Eliminar");
            System.out.println("2)Buscar");
            System.out.println("3)Modificar");
            System.out.println("4)Consultar");
            System.out.println("5)Regresar");
            op1 = (int)validarRango("Opcion:",1,5);
            if(op1 !=5 && op1 !=4){
                leer.nextLine();
                System.out.println("Codigo: ");
                cod = leer.nextLine();
            }
        }    
        switch(op){
            case 1:{//Si administra envios
                switch(op1){
                    case 1:{
                        env1.eliminarEnvio(cod, arc2); //Elmina un envio
                        break;
                    }
                    case 2:{
                        int x = buscarDatosE(cod,0 ,arc2); //Imprime un envio 
                        if(x ==-1)                                   
                            System.out.println("Envio no encontrado");
                        break;
                    }
                    case 3:{
                        env1.modificarEnvio(cod, arc2); //Modifica un envio
                        break;
                    }
                    case 4:{
                        consultar(arc2);
                        break;
                    }
                }
                menuAdministracion(); //Abre el menu de administracion
                break;    
            }
            case 2:{//Si administra productos
                switch(op1){
                    case 1:{
                        pr1.eliminarProducto(cod,arc1); //Elimina un Producto 
                        break;  
                    }
                    case 2:{
                        int x = buscarDatos(cod,0,arc1); //Imprime un Producto
                        if(x ==-1)                                   
                            System.out.println("Producto no encontrado");
                        break;    
                        }
                    case 3: {
                        pr1.modificarProducto(cod,arc1); //Modifica un producto
                        break;
                    }
                    case 4:{
                        consultar(arc1);
                        break;
                    }
                }
                menuAdministracion(); //Retorna al menu de administracion
                break;
            }
            default:{
                 menuPrograma(); //Retorna al menu del programa
            }
        }
    }
    
    public static void menuIngresoEnvio() //Funcion para manejar el menu de ingreso de envios 
    {
        int n,pos,can;
        //Declaracion de objetos y vectores
        Producto pro;
        Vector vec, ven = new Vector(), temp = cargarDatos(arc2);
        EnvioDeProductos env = new EnvioDeProductos(); 
        //Entrada de informacion
        System.out.println("\tEnvio");
        leer.nextLine();
        env.setCod_envio(validarCodigo(arc2));
        System.out.println("Medio: ");
        env.setMed_envio(leer.nextLine());
        vec = cargarDatos(arc1);
        n = (int)validarRango("Numero de productos diferentes a ingresar",1,vec.size());
        for(int x=0; x<n; x++){
            do{
                System.out.printf("Codigo del producto[%d]: ",x+1);
                leer.nextLine();
                pos = buscarDatos(leer.nextLine(), 1,arc1);
                if(pos ==-1)
                    System.out.println("Codigo invalido");
            }while(pos==-1); //Valida que el producto exista
            pro = (Producto)vec.get(pos);
            //Imprime los datos del producto
            System.out.println("Codigo: "+pro.getCod()+"\tNombre: "+pro.getNombre()+"\tCantidad: "+pro.getCan());
            System.out.printf("Cantidad del producto[%d]: ",x+1);
            can = (int)validarRango("",1,pro.getCan()); //Ingresa la cantidad 
            pro.setCan(pro.getCan()-can);
            vec.setElementAt(pro, pos);
            guardarDatos(vec,arc1);
            pro.setCan(can);
            ven.add(pro);            
        }
        env.setProductos(ven);
        temp.add(env);
        guardarDatos(temp,arc2);
        System.out.println("Envio registrado con éxito");
        menuPrograma();
    } 
    
    public static void consultar(File arc){
        int x;
        Vector pro = new Vector(); 
        boolean verdad = false;        
        Producto aux ;
        EnvioDeProductos env;
        pro=cargarDatos(arc);
        for( x=0; x< pro.size(); x++){
            if (arc.getName().equalsIgnoreCase("Productos.obj")){
                aux = (Producto)pro.get(x);
                aux.imprimirProducto();
            }else{
                env =(EnvioDeProductos)pro.get(x);
                env.imprimirEnvio();
            }
                
        }
    }
}

