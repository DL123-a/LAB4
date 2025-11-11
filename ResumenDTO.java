import java.util.List;

public class ResumenDTO {
    private Usuario usuario;
    private List<Documento> documentos;
    private List<Fragmento> fragmentos;

    public ResumenDTO(Usuario usuario, List<Documento> documentos, List<Fragmento> fragmentos) {
        this.usuario = usuario;
        this.documentos = documentos;
        this.fragmentos = fragmentos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public List<Fragmento> getFragmentos() {
        return fragmentos;
    }
}
