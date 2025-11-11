import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nombre;
    private Rol rol;

    private List<String> etiquetasAplicadas;

    public Usuario(String nombre, Rol rol) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.rol = rol;
        this.etiquetasAplicadas = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public List<String> getEtiquetasAplicadas() {
        return etiquetasAplicadas;
    }


    public void agregarEtiqueta(Fragmento fragmento, String etiquetaTexto) {
        if (fragmento == null || etiquetaTexto == null || etiquetaTexto.trim().isEmpty()) return;

        etiquetasAplicadas.add(etiquetaTexto);

        fragmento.setEtiqueta(etiquetaTexto);
        System.out.println("Etiqueta '" + etiquetaTexto + "' aplicada por " + nombre + " al fragmento.");
    }

    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}
