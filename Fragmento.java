public class Fragmento {
    private final Integer inicio;      
    private final Integer fin;         
    private final Documento documento; 
    private final String contenidoDirecto; 
    private String etiqueta;

    public Fragmento(int inicio, int fin, Documento documento) {
        int len = documento != null && documento.getFuente() != null
                ? documento.getFuente().getTexto().length()
                : 0;
        int i = Math.max(0, Math.min(inicio, len));
        int f = Math.max(0, Math.min(fin, len));
        if (i > f) { int t = i; i = f; f = t; }
        this.inicio = i;
        this.fin = f;
        this.documento = documento;
        this.contenidoDirecto = null;
    }

    public Fragmento(String contenido, Documento documento) {
        this.inicio = null;
        this.fin = null;
        this.documento = documento;
        this.contenidoDirecto = contenido != null ? contenido : "";
    }

    public Fragmento(String contenido) {
        this(contenido, null);
    }

    public String getTexto() {
        if (contenidoDirecto != null) return contenidoDirecto;
        if (documento == null || documento.getFuente() == null) return "";
        String t = documento.getFuente().getTexto();
        if (inicio == null || fin == null) return "";
        if (inicio < 0 || fin > t.length() || inicio >= fin) return "";
        return t.substring(inicio, fin);
    }

    public String getTextoResumen() {
        String txt = getTexto();
        if (txt == null) return "";
        if (txt.length() > 120) txt = txt.substring(0, 120) + "...";
        if (etiqueta != null && !etiqueta.isEmpty()) {
            return "[" + etiqueta + "] " + txt;
        }
        return txt;
    }

    public int getInicio() {
        return inicio != null ? inicio : 0;
    }

    public int getFin() {
        if (fin != null) return fin;
        return contenidoDirecto != null ? contenidoDirecto.length() : 0;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return getTexto();
    }
}
