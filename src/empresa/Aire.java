package empresa;

public class Aire extends Congelado {
    
    private double por_nitro,por_oxige,por_co2,por_vapAgua;

    public void setPor_nitro(double por_nitro) {
        this.por_nitro = por_nitro;
    }

    public void setPor_oxige(double por_oxige) {
        this.por_oxige = por_oxige;
    }

    public void setPor_co2(double por_co2) {
        this.por_co2 = por_co2;
    }

    public void setPor_vapAgua(double por_vapAgua) {
        this.por_vapAgua = por_vapAgua;
    }


    public void imprimirProducto () {
        super.imprimirProducto();
        System.out.println("% Nitrogeno: "+this.por_nitro);
        System.out.println("% Oxigeno: "+this.por_oxige);
        System.out.println("% CO2: "+this.por_co2);
        System.out.println("% Vap. Agua: "+this.por_vapAgua);        
    }



}

