import java.util.*;

public class Documento {
    private final UUID id;
    private String titulo;
    private String contenido;
    private Usuario propietario;
    private final List<Fragmento> fragmentos;

    public Documento(String titulo, String contenido) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.contenido = contenido;
        this.fragmentos = new ArrayList<>();
    }

    // Métodos del análisis
    public Fragmento crearFragmento(int inicio, int fin) {
        if (inicio < 0 || fin > contenido.length() || inicio >= fin)
            throw new IllegalArgumentException("Rango inválido");
        Fragmento f = new Fragmento(this, inicio, fin);
        fragmentos.add(f);
        return f;
    }

    public List<Fragmento> getFragmentos() {
        return Collections.unmodifiableList(fragmentos);
    }

    public String getContenido() { return contenido; }

    // Getters útiles para ResumenDTO
    public UUID getId() { return id; }
    public String getTitulo() { return titulo; }
}
