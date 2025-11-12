public class Fragmento {
    private final Integer inicio;      // null si se creó por contenido directo
    private final Integer fin;         // null si se creó por contenido directo
    private final Documento documento; // puede ser null si el fragmento fue creado solo con contenido
    private final String contenidoDirecto; // no-null si se creó por contenido directo

    // etiqueta aplicada al fragmento (puede ser null)
    private String etiqueta;

    /** Constructor por índices (usa el documento para extraer el texto). */
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
        this.etiqueta = null;
    }

    /** Constructor por contenido (texto ya extraído) y opcional referencia a documento. */
    public Fragmento(String contenido, Documento documento) {
        this.inicio = null;
        this.fin = null;
        this.documento = documento;
        this.contenidoDirecto = contenido != null ? contenido : "";
        this.etiqueta = null;
    }

    /** Constructor simple por contenido sin documento. */
    public Fragmento(String contenido) {
        this(contenido, null);
    }

    /** Devuelve el texto completo del fragmento. */
    public String getTexto() {
        if (contenidoDirecto != null) return contenidoDirecto;
        if (documento == null || documento.getFuente() == null) return "";
        String t = documento.getFuente().getTexto();
        if (inicio == null || fin == null) return "";
        if (inicio < 0 || fin > t.length() || inicio >= fin) return "";
        return t.substring(inicio, fin);
    }

    /** Versión corta para listados (hasta 120 chars). */
    public String getTextoResumen() {
        String txt = getTexto();
        if (txt == null) return "";
        if (txt.length() > 120) txt = txt.substring(0, 120) + "...";
        if (etiqueta != null && !etiqueta.isEmpty()) {
            return "[" + etiqueta + "] " + txt;
        }
        return txt;
    }

    /** Si el fragmento se creó por índices, devuelve inicio; si no, devuelve 0. */
    public int getInicio() {
        return inicio != null ? inicio : 0;
    }

    /** Si el fragmento se creó por índices, devuelve fin; si no, devuelve la longitud del contenido directo. */
    public int getFin() {
        if (fin != null) return fin;
        return contenidoDirecto != null ? contenidoDirecto.length() : 0;
    }

    /** Asigna una etiqueta al fragmento. */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /** Devuelve la etiqueta aplicada (puede ser null). */
    public String getEtiqueta() {
        return etiqueta;
    }

    /** 🔹 NUEVO: getter para acceder al Documento asociado (puede ser null). */
    public Documento getDocumento() {
        return documento;
    }

    @Override
    public String toString() {
        return getTexto();
    }
}
