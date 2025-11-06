import java.util.UUID;

public class Usuario {
    private final UUID id;
    private String nombre;

    public Usuario(String nombre) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
    }

    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
