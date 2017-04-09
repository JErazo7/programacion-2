package empresa;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.1BDFD44E-6A53-9BE1-73F7-85CB5B7DA5EB]
// </editor-fold> 
public class Agua extends Congelado {

    
    private double salinidad;

    public void setSalinidad(double salinidad) {
        this.salinidad = salinidad;
    }
     
    public void imprimirProducto () {
        super.imprimirProducto();
        System.out.println("Temp. Recomendada: "+getTemp_recom());
        System.out.println("Salinidad: "+this.salinidad);
    }
}

