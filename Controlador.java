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

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Cargar Texto", panelCargarTexto);
        pestañas.addTab("Fragmentos", panelFragmento);

        add(pestañas, BorderLayout.CENTER);
        setVisible(true);
    }

    public void agregarFragmento(Fragmento frag) {
    fragmentos.add(frag);
    }

    public List<Fragmento> listarFragmentos() {
        return fragmentos;
    }

    public Fragmento crearFragmento(int inicio, int fin) {
        if (documentos.isEmpty()) {
            System.out.println("No hay documentos importados.");
            return null;
        }

        Documento doc = documentos.get(documentos.size() - 1); 
        FuenteTexto fuente = doc.getFuente();

        if (fuente == null) {
            System.out.println("El documento no tiene fuente de texto.");
            return null;
        }

        String texto = fuente.getTexto();
        if (inicio < 0 || fin > texto.length() || inicio >= fin) {
            System.out.println("Rango inválido para el fragmento.");
            return null;
        }

        String subtexto = texto.substring(inicio, fin);
        Fragmento frag = new Fragmento(subtexto, doc);
        fragmentos.add(frag);
        return frag;
    }

    public Documento importarDocumento(String titulo, String texto) {
        FuenteTexto fuente = new FuenteTextoPlano(texto);
        Documento doc = new Documento(titulo, fuente, usuarioActual);
        documentos.add(doc);
        JOptionPane.showMessageDialog(this, "Documento \"" + titulo + "\" importado correctamente.");
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
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
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
            sb.append(" - ").append(f.getTexto()).append("\n");
        }
        return sb.toString();
    }
}
