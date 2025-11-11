public class Documento {
    private String titulo;
    private FuenteTexto fuente;

    public Documento(String titulo, FuenteTexto fuente) {
        this.titulo = titulo;
        this.fuente = fuente;
    }

    public String getTitulo() {
        return titulo;
    }

    public FuenteTexto getFuente() {
        return fuente;
    }
}


