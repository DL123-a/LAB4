import java.util.*;

public class ResumenEtiqueta {
    public static class ItemResumen {
        public final UUID fragId;
        public final String texto;
        public final String docTitulo;
        public final int inicio;
        public final int fin;

        public ItemResumen(UUID fragId, String texto, String docTitulo, int inicio, int fin) {
            this.fragId = fragId;
            this.texto = texto;
            this.docTitulo = docTitulo;
            this.inicio = inicio;
            this.fin = fin;
        }
    }

    private final String etiquetaNombre;
    private final int totalCitas;
    private final int totalPalabras;
    private final List<ItemResumen> fragmentos;

    public ResumenEtiqueta(String etiquetaNombre, int totalCitas, int totalPalabras, List<ItemResumen> fragmentos) {
        this.etiquetaNombre = etiquetaNombre;
        this.totalCitas = totalCitas;
        this.totalPalabras = totalPalabras;
        this.fragmentos = Collections.unmodifiableList(new ArrayList<>(fragmentos));
    }

    public String getEtiquetaNombre() { return etiquetaNombre; }
    public int getTotalCitas() { return totalCitas; }
    public int getTotalPalabras() { return totalPalabras; }
    public List<ItemResumen> getFragmentos() { return fragmentos; }
}
