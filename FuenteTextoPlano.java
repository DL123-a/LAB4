public class FuenteTextoPlano implements FuenteTexto {
    private String nombre;
    private String contenido;
    public FuenteTextoPlano(String nombre, String contenido) {
        this.nombre = nombre;
        this.contenido = contenido;
    }
    public String obtenerContenido() { return contenido; }
    public String obtenerNombre() { return nombre; }
}

