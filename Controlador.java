import java.util.*;
public class Controlador {
    private List<Documento> documentos = new ArrayList<>();
    private List<Etiqueta> etiquetas = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario registrarUsuario(String nombre, Rol rol) {
        Usuario u = new Usuario(nombre, rol);
        usuarios.add(u);
        return u;
    }
    public Documento importarFuente(FuenteTexto fuente, Usuario propietario) {
        Documento doc = new Documento(fuente.obtenerNombre(), fuente.obtenerContenido(), propietario);
        documentos.add(doc);
        return doc;
    }
    public List<Documento> listarDocumentos() { return documentos; }
    public Etiqueta crearEtiqueta(String nombre, TipoEtiqueta tipo) {
        for (Etiqueta e : etiquetas) if (e.getNombre().equalsIgnoreCase(nombre)) return e;
        Etiqueta e = new Etiqueta(nombre, tipo);
        etiquetas.add(e);
        return e;
    }
    public Etiqueta crearEtiqueta(String nombre) { return crearEtiqueta(nombre, TipoEtiqueta.OTRO); }
    public List<Etiqueta> listarEtiquetas() { return etiquetas; }
    public Fragmento crearFragmento(Documento doc, int inicio, int fin) { return doc.crearFragmento(inicio, fin); }
    public void aplicarEtiquetaAFragmento(Fragmento frag, Etiqueta etiqueta, Usuario autor) {
        boolean ya = frag.getCodings().stream().anyMatch(c -> c.getEtiqueta().getId().equals(etiqueta.getId()));
        if (!ya) frag.agregarCoding(new Coding(frag, etiqueta, autor));
    }
    public void quitarEtiquetaDeFragmento(Fragmento frag, Etiqueta etiqueta) { frag.quitarCodingPorEtiqueta(etiqueta); }

    public ResumenDTO obtenerResumenPorEtiqueta(Etiqueta etiqueta) {
        ResumenDTO dto = new ResumenDTO();
        int tc = 0, tp = 0;
        for (Documento doc : documentos) {
            for (Fragmento frag : doc.getFragmentos()) {
                boolean tiene = frag.getCodings().stream().anyMatch(c -> c.getEtiqueta().getId().equals(etiqueta.getId()));
                if (tiene) {
                    tc++;
                    String texto = frag.getTexto();
                    tp += contarPalabras(texto);
                    dto.addItem(new ItemResumen(frag.getId().toString(), texto, doc.getTitulo(), frag.getInicio(), frag.getFin()));
                }
            }
        }
        dto.setTotalCitas(tc);
        dto.setTotalPalabras(tp);
        return dto;
    }

    public ResumenDTO obtenerResumenPorDocumento(Documento doc) {
        ResumenDTO dto = new ResumenDTO();
        int tc = 0, tp = 0;
        for (Fragmento frag : doc.getFragmentos()) {
            tc++;
            String texto = frag.getTexto();
            tp += contarPalabras(texto);
            dto.addItem(new ItemResumen(frag.getId().toString(), texto, doc.getTitulo(), frag.getInicio(), frag.getFin()));
        }
        dto.setTotalCitas(tc);
        dto.setTotalPalabras(tp);
        return dto;
    }

    public double porcentajeTextoPorEtiqueta(Etiqueta etiqueta) {
        int totalEti = 0;
        int totalGlobal = 0;
        for (Documento doc : documentos) {
            if (doc.getContenido() != null && !doc.getContenido().isEmpty()) {
                totalGlobal += contarPalabras(doc.getContenido());
            }
            for (Fragmento frag : doc.getFragmentos()) {
                boolean tiene = frag.getCodings().stream().anyMatch(c -> c.getEtiqueta().getId().equals(etiqueta.getId()));
                if (tiene) totalEti += contarPalabras(frag.getTexto());
            }
        }
        if (totalGlobal == 0) return 0.0;
        return (totalEti * 100.0) / totalGlobal;
    }

    public static class MetricasDocumento {
        public Documento documento;
        public int totalFragmentos;
        public List<Etiqueta> etiquetasUsadas;
        public Etiqueta etiquetaMasFrecuente;
    }

    public List<MetricasDocumento> metricasPorDocumento() {
        List<MetricasDocumento> res = new ArrayList<>();
        for (Documento doc : documentos) {
            MetricasDocumento md = new MetricasDocumento();
            md.documento = doc;
            md.totalFragmentos = doc.getFragmentos().size();
            Map<Etiqueta, Integer> conteo = new HashMap<>();
            for (Fragmento frag : doc.getFragmentos()) {
                for (Coding c : frag.getCodings()) {
                    Etiqueta et = c.getEtiqueta();
                    conteo.put(et, conteo.getOrDefault(et, 0)+1);
                }
            }
            md.etiquetasUsadas = new ArrayList<>(conteo.keySet());
            Etiqueta mas = null; int max = 0;
            for (Map.Entry<Etiqueta,Integer> en : conteo.entrySet()) {
                if (en.getValue() > max) { max = en.getValue(); mas = en.getKey(); }
            }
            md.etiquetaMasFrecuente = mas;
            res.add(md);
        }
        return res;
    }

    private int contarPalabras(String texto) {
        if (texto == null || texto.isEmpty()) return 0;
        return texto.trim().split("\s+").length;
    }
}
