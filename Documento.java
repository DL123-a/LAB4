public class Documento {
    private final String titulo;
    private final FuenteTexto fuente;
    private final Usuario autor;

    public Documento(String titulo, FuenteTexto fuente, Usuario autor) {
        this.titulo = titulo != null ? titulo : "";
        this.fuente = fuente;
        this.autor = autor;
    }

    public Documento(String titulo, FuenteTexto fuente) {
        this(titulo, fuente, null);
    }

    public String getTitulo() {
        return titulo;
    }

    public FuenteTexto getFuente() {
        return fuente;
    }

    public Usuario getAutor() {
        return autor;
    }


    public String getTexto() {
        return fuente != null ? fuente.getTexto() : "";
    }

    @Override
    public String toString() {
        String autorStr = autor != null ? autor.toString() : "Sin autor";
        return "Título: " + titulo + "\nAutor: " + autorStr + "\n\n" + getTexto();
    }
}
