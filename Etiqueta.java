import java.util.UUID;
public class Etiqueta {
    private UUID id;
    private String nombre;
    private TipoEtiqueta tipo;
    private String color;
    public Etiqueta(String nombre, TipoEtiqueta tipo) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.tipo = tipo;
    }
    public Etiqueta(String nombre, TipoEtiqueta tipo, String color) {
        this(nombre, tipo);
        this.color = color;
    }
    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public TipoEtiqueta getTipo() { return tipo; }
    public String getColor() { return color; }
    public String toString() { return nombre; }
}


