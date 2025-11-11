public class FuenteTextoPlano extends FuenteTexto {
    private String texto;

    public FuenteTextoPlano(String texto) {
        this.texto = texto;
    }

    @Override
    public String getTexto() {
        return texto;
    }
}

