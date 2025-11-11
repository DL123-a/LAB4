import java.util.*;
public class Documento {
    private java.util.UUID id;
    private String titulo;
    private String contenido;
    private Usuario propietario;
    private java.util.List<Fragmento> fragmentos;
    public Documento(String titulo, String contenido, Usuario propietario) {
        this.id = java.util.UUID.randomUUID();
        this.titulo = titulo;
        this.contenido = contenido;
        this.propietario = propietario;
        this.fragmentos = new java.util.ArrayList<>();
    }
    public Documento(String titulo, String contenido) { this(titulo, contenido, null); }
    public Fragmento crearFragmento(int inicio, int fin) {
        if (inicio < 0 || fin > contenido.length() || inicio >= fin) {
            throw new IllegalArgumentException("rango invalido");
        }
        Fragmento f = new Fragmento(this, inicio, fin);
        fragmentos.add(f);
        return f;
    }
    public java.util.List<Fragmento> getFragmentos() { return fragmentos; }
    public String getContenido() { return contenido; }
    public String getTitulo() { return titulo; }
    public java.util.UUID getId() { return id; }
    public Usuario getPropietario() { return propietario; }
    public String toString() { return titulo; }
}

