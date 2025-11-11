import java.util.UUID;
public class Usuario {
    private UUID id;
    private String nombre;
    private Rol rol;
    public Usuario(String nombre, Rol rol) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.rol = rol;
    }
    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public Rol getRol() { return rol; }
}
