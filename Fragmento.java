public class Fragmento {
    private String texto;
    private Etiqueta etiqueta;
    private Usuario autor;

    public Fragmento(String texto, Etiqueta etiqueta, Usuario autor) {
        this.texto = texto;
        this.etiqueta = etiqueta;
        this.autor = autor;
    }

    public String getTexto() {
        return texto;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public Usuario getAutor() {
        return autor;
    }
}

