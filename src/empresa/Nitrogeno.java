package empresa;

import java.util.Date;

public class Nitrogeno extends Congelado {

     
    private String metodo;     
    private double tiem_exp;

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public void setTiem_exp(double tiem_exp) {
        this.tiem_exp = tiem_exp;
    }
     
    public void imprimirProducto () {
        super.imprimirProducto();
        System.out.println("Metodo: "+this.metodo);
        System.out.println("Tie. Exposicion: "+this.tiem_exp);        
    }

}

