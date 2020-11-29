public class Pronostico {

    private String ciudad;
    private String fecha;
    private double temperatura;
    private int humedad;
    private int probabilidadNubes;
    private int velocidadViento;
    private String pronosticoTiempo;

    public int getProbabilidadNubes() {
        return probabilidadNubes;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public String getPronosticoTiempo() {
        return pronosticoTiempo;
    }

    public void setPronosticoTiempo(String pronosticoTiempo) {
        this.pronosticoTiempo = pronosticoTiempo;
    }

    public int getVelocidadViento() {
        return velocidadViento;
    }

    public void setVelocidadViento(int velocidadViento) {
        this.velocidadViento = velocidadViento;
    }

    public void setProbabilidadNubes(int probabilidadNubes) {
        this.probabilidadNubes = probabilidadNubes;
    }

    public Pronostico(String ciudad, String fecha, double temperatura, int humedad, int probabilidadNubes, int velocidadViento,
            String pronosticoTiempo) {
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.probabilidadNubes = probabilidadNubes;
        this.velocidadViento = velocidadViento;
        this.pronosticoTiempo = pronosticoTiempo;
    }

}
