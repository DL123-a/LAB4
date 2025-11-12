import java.awt.BorderLayout;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controlador extends JFrame {
    private Usuario usuarioActual;
    private List<Documento> documentos;
    private List<Fragmento> fragmentos;
    private PanelCargarTexto panelCargarTexto;
    private PanelFragmento panelFragmento;
    private PanelResumen panelResumen;

    public Controlador() {
        documentos = new ArrayList<>();
        fragmentos = new ArrayList<>();

        setTitle("Sistema de Codificación Antropológica");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        iniciarPrograma();
    }

    private void iniciarPrograma() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese su nombre de usuario:");
        if (nombre == null || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre para continuar.");
            System.exit(0);
        }

        usuarioActual = new Usuario(nombre, Rol.INVESTIGADOR);
        JOptionPane.showMessageDialog(this, "Bienvenido " + usuarioActual.getNombre() + " (" + usuarioActual.getRol() + ")");

        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        panelCargarTexto = new PanelCargarTexto(this);
        panelFragmento = new PanelFragmento(this);
        panelResumen = new PanelResumen(this);

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Cargar Texto", panelCargarTexto);
        pestañas.addTab("Fragmentos", panelFragmento);
        pestañas.addTab("Resumen", panelResumen);

        add(pestañas, BorderLayout.CENTER);
        setVisible(true);
    }

    // === MÉTODOS DE LÓGICA ===

    public void agregarFragmento(Fragmento frag) {
        fragmentos.add(frag);
        actualizarResumen();
    }

    public List<Fragmento> listarFragmentos() {
        return fragmentos;
    }

    /** Ahora permite elegir el documento del cual extraer el fragmento **/
    public Fragmento crearFragmento(int inicio, int fin) {
        if (documentos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay documentos cargados aún.");
            return null;
        }

        // Crear lista de títulos para elegir
        String[] titulos = documentos.stream().map(Documento::getTitulo).toArray(String[]::new);
        String seleccionado = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el documento del cual crear el fragmento:",
                "Elegir documento",
                JOptionPane.QUESTION_MESSAGE,
                null,
                titulos,
                titulos[0]);

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "No se seleccionó ningún documento.");
            return null;
        }

        Documento doc = documentos.stream()
                .filter(d -> d.getTitulo().equals(seleccionado))
                .findFirst()
                .orElse(null);

        if (doc == null) {
            JOptionPane.showMessageDialog(this, "Documento no encontrado.");
            return null;
        }

        FuenteTexto fuente = doc.getFuente();
        if (fuente == null) {
            JOptionPane.showMessageDialog(this, "El documento seleccionado no tiene texto.");
            return null;
        }

        String texto = fuente.getTexto();
        if (inicio < 0 || fin > texto.length() || inicio >= fin) {
            JOptionPane.showMessageDialog(this, "Rango inválido para el fragmento.");
            return null;
        }

        String subtexto = texto.substring(inicio, fin);
        Fragmento frag = new Fragmento(subtexto, doc);
        fragmentos.add(frag);
        actualizarResumen();
        return frag;
    }

    public Documento importarDocumento(String titulo, String texto) {
        FuenteTexto fuente = new FuenteTextoPlano(texto);
        Documento doc = new Documento(titulo, fuente, usuarioActual);
        documentos.add(doc);
        JOptionPane.showMessageDialog(this, "Documento \"" + titulo + "\" importado correctamente.");
        actualizarResumen();
        return doc;
    }

    public void aplicarEtiquetaAFragmento(int indice, TipoEtiqueta tipoEtiqueta) {
        if (indice < 0 || indice >= fragmentos.size()) {
            JOptionPane.showMessageDialog(null, "Índice de fragmento inválido.");
            return;
        }

        Fragmento frag = fragmentos.get(indice);
        String etiquetaTexto = tipoEtiqueta.name();
        usuarioActual.agregarEtiqueta(frag, etiquetaTexto);

        JOptionPane.showMessageDialog(null,
                "Etiqueta '" + etiquetaTexto + "' aplicada al fragmento:\n" + frag.getTextoResumen());

        actualizarResumen();
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public String generarResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resumen del sistema:\n\n");
        sb.append("Usuario activo: ").append(usuarioActual.getNombre())
          .append(" (").append(usuarioActual.getRol()).append(")\n\n");

        sb.append("Documentos cargados: ").append(documentos.size()).append("\n");
        for (Documento d : documentos) {
            sb.append(" - ").append(d.getTitulo()).append("\n");
        }

        sb.append("\nFragmentos creados: ").append(fragmentos.size()).append("\n");
        for (Fragmento f : fragmentos) {
            sb.append(" - [").append(f.getDocumento() != null ? f.getDocumento().getTitulo() : "Sin documento")
              .append("] ").append(f.getTextoResumen()).append("\n");
        }
        return sb.toString();
    }

    public void actualizarResumen() {
        if (panelResumen != null) {
            panelResumen.actualizarTexto();
        }
    }
}
