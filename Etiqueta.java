import java.util.UUID;

public class Etiqueta {
    private final UUID id;
    private String nombre;
    private String color;

    public Etiqueta(String nombre, String color) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.color = color;
    }

    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public String getColor() { return color; }
}

