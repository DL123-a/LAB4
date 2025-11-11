public class ItemResumen {
    private String fragId;
    private String texto;
    private String docTitulo;
    private int inicio;
    private int fin;
    public ItemResumen(String fragId, String texto, String docTitulo, int inicio, int fin) {
        this.fragId = fragId; this.texto = texto; this.docTitulo = docTitulo; this.inicio = inicio; this.fin = fin;
    }
    public String getFragId() { return fragId; }
    public String getTexto() { return texto; }
    public String getDocTitulo() { return docTitulo; }
    public int getInicio() { return inicio; }
    public int getFin() { return fin; }
}

