import java.util.UUID;
public class Coding {
    private UUID id;
    private Fragmento fragmento;
    private Etiqueta etiqueta;
    private Usuario autor;
    public Coding(Fragmento fragmento, Etiqueta etiqueta, Usuario autor) {
        this.id = UUID.randomUUID();
        this.fragmento = fragmento;
        this.etiqueta = etiqueta;
        this.autor = autor;
    }
    public UUID getId() { return id; }
    public Fragmento getFragmento() { return fragmento; }
    public Etiqueta getEtiqueta() { return etiqueta; }
    public Usuario getAutor() { return autor; }
}

