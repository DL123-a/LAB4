public class Etiqueta {
    private final TipoEtiqueta tipo;

    public Etiqueta(TipoEtiqueta tipo) {
        this.tipo = tipo;
    }

    public TipoEtiqueta getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return tipo.toString();
    }
}

