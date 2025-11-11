import java.util.*;
import javax.swing.*;

public class Controlador {
    private Usuario usuarioActual;
    private List<Documento> documentos;
    private List<Fragmento> fragmentos;
    private List<Etiqueta> etiquetas;

    private JFrame ventanaPrincipal;
    private PanelCargarTexto panelCargarTexto;
    private PanelFragmento panelFragmento;
    private PanelResumen panelResumen;

    public Controlador() {
        documentos = new ArrayList<>();
        fragmentos = new ArrayList<>();
        etiquetas = new ArrayList<>();
    }

    public void iniciarPrograma() {
        // Ventana principal
        ventanaPrincipal = new JFrame("Laboratorio 4 - Sistema de Codificación Antropológica");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(800, 600);

        // Login inicial
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
        Rol rol = (Rol) JOptionPane.showInputDialog(
            null,
            "Seleccione su rol:",
            "Inicio de sesión",
            JOptionPane.QUESTION_MESSAGE,
            null,
            Rol.values(),
            Rol.INVESTIGADOR
        );

        usuarioActual = new Usuario(nombre, rol);

        // Crear paneles
        panelCargarTexto = new PanelCargarTexto(this);
        panelFragmento = new PanelFragmento(this);
        panelResumen = new PanelResumen(this);

        ventanaPrincipal.add(panelCargarTexto);
        ventanaPrincipal.setVisible(true);
    }

    public void mostrarPanelCargarTexto() {
        cambiarPanel(panelCargarTexto);
    }

    public void mostrarPanelFragmento() {
        cambiarPanel(panelFragmento);
    }

    public void mostrarPanelResumen() {
        panelResumen.actualizarResumen();
        cambiarPanel(panelResumen);
    }

    private void cambiarPanel(JPanel nuevo) {
        ventanaPrincipal.getContentPane().removeAll();
        ventanaPrincipal.add(nuevo);
        ventanaPrincipal.revalidate();
        ventanaPrincipal.repaint();
    }

    public void agregarDocumento(Documento doc) {
        documentos.add(doc);
    }

    public void agregarFragmento(Fragmento frag) {
        fragmentos.add(frag);
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public List<Fragmento> getFragmentos() {
        return fragmentos;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
