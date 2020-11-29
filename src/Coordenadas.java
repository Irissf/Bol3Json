public class Coordenadas {
    private double longitud;
    private double latitud;

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public Coordenadas(double longitud, double latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

	public Coordenadas() {
	}
}
