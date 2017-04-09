package empresa;
public class Refrigerado extends Producto {

    private String cod_super;
    private double temp_recom;

    public void setCod_super(String cod_super) {
        this.cod_super = cod_super;
    }

    public void setTemp_recom(double temp_recom) {
        this.temp_recom = temp_recom;
    }

    public void imprimirProducto () {
        super.imprimirProducto();
        System.out.println("Cod. Supervicion: "+this.cod_super);
        System.out.println("Tem. Recomendada: "+this.temp_recom);        
    }  
   
    

}

