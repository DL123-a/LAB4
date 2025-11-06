import java.util.*;

public class Controlador {
    private final List<Documento> documentos = new ArrayList<>();
    private final List<Etiqueta> tags = new ArrayList<>();

    // Del análisis
    public Documento importarDocumento(String titulo, String texto) {
        Documento d = new Documento(titulo, texto);
        documentos.add(d);
        return d;
    }

    public List<Documento> listarDocumentos() {
        return Collections.unmodifiableList(documentos);
    }

    public Fragmento crearFragmento(Documento doc, int inicio, int fin) {
        return doc.crearFragmento(inicio, fin);
    }

    public void aplicarTag(Fragmento frag, Etiqueta etiqueta) {
        // Evitar duplicados: si ya hay un Coding con esa etiqueta, no agregar otro
        boolean existe = frag.getCodings().stream()
                .anyMatch(c -> c.getEtiqueta().equals(etiqueta));
        if (!existe) frag.agregarCoding(new Coding(frag, etiqueta));
    }

    public void quitarTag(Fragmento frag, Etiqueta etiqueta) {
        frag.quitarCodingPorTag(etiqueta);
    }

    public Etiqueta crearTag(String nombre) {
        Etiqueta e = new Etiqueta(nombre, "#CCCCCC");
        tags.add(e);
        return e;
    }

    // Resumen por etiqueta
    public ResumenDTO obtenerResumenPorTag(Etiqueta etiqueta) {
        List<ResumenDTO.ItemResumen> items = new ArrayList<>();
        int totalPalabras = 0;

        for (Documento d : documentos) {
            for (Fragmento f : d.getFragmentos()) {
                boolean tiene = f.getCodings().stream()
                        .anyMatch(c -> c.getEtiqueta().equals(etiqueta));
                if (tiene) {
                    String texto = f.getTexto();
                    items.add(new ResumenDTO.ItemResumen(
                            f.getId(), texto, d.getTitulo(), f.getInicio(), f.getFin()
                    ));
                    totalPalabras += contarPalabras(texto);
                }
            }
        }
        return new ResumenDTO(items.size(), totalPalabras, items);
    }

    // Resumen por documento
    public ResumenDTO obtenerResumenPorDocumento(Documento doc) {
        List<ResumenDTO.ItemResumen> items = new ArrayList<>();
        int totalPalabras = 0;

        for (Fragmento f : doc.getFragmentos()) {
            String texto = f.getTexto();
            items.add(new ResumenDTO.ItemResumen(
                    f.getId(), texto, doc.getTitulo(), f.getInicio(), f.getFin()
            ));
            totalPalabras += contarPalabras(texto);
        }
        return new ResumenDTO(items.size(), totalPalabras, items);
    }

    // Utilidad local (privada) para el totalPalabras requerido por el análisis
    private int contarPalabras(String s) {
        if (s == null || s.isBlank()) return 0;
        int count = 0;
        boolean enPalabra = false;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                if (enPalabra) { count++; enPalabra = false; }
            } else {
                enPalabra = true;
            }
        }
        if (enPalabra) count++;
        return count;
    }
}
