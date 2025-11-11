public enum TipoEtiqueta {
    RESTOS_OSEOS("Restos óseos"),
    CERAMICA("Cerámica"),
    SITIO_ARQUEOLOGICO("Sitio arqueológico"),
    CONTEXTO_HISTORICO("Contexto histórico"),
    RITUALES_FUNERARIOS("Rituales funerarios"),
    ARTE_RUPRESTRE("Arte rupestre"),
    ESTRUCTURA_SOCIAL("Estructura social"),
    ANALISIS_GENERICO("Análisis genético"),
    OTRO("Otro");

    private final String descripcion;
    TipoEtiqueta(String d) { this.descripcion = d; }
    @Override public String toString() { return descripcion; }
}
