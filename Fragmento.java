public class Fragmento {
    private java.util.UUID id;
    private Documento documento;
    private int inicio;
    private int fin;
    private java.util.List<Coding> codings;
    public Fragmento(Documento documento, int inicio, int fin) {
        this.id = java.util.UUID.randomUUID();
        this.documento = documento;
        this.inicio = inicio;
        this.fin = fin;
        this.codings = new java.util.ArrayList<>();
    }
    public String getTexto() { return documento.getContenido().substring(inicio, fin); }
    public void agregarCoding(Coding c) { codings.add(c); }
    public void quitarCodingPorEtiqueta(Etiqueta etiqueta) {
        codings.removeIf(c -> c.getEtiqueta().getId().equals(etiqueta.getId()));
    }
    public java.util.List<Coding> getCodings() { return codings; }
    public java.util.UUID getId() { return id; }
    public Documento getDocumento() { return documento; }
    public int getInicio() { return inicio; }
    public int getFin() { return fin; }
    public String toString() { return getTexto(); }
}
