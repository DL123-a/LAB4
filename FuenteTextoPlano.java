public class FuenteTextoPlano implements FuenteTexto {
    private final String texto;

    public FuenteTextoPlano(String texto) {
        this.texto = texto != null ? texto : "";
    }

    @Override
    public String getTexto() {
        return texto;
    }
}
