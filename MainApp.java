
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainApp extends JFrame {

    public MainApp() {
        setTitle("Analisis cualitativo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        Controlador controlador = new Controlador();
        Usuario usuario = controlador.registrarUsuario("Usuario", Rol.ADMIN);

        // etiquetas base
        controlador.crearEtiqueta("Trabajo", TipoEtiqueta.TEMA);
        controlador.crearEtiqueta("Familia", TipoEtiqueta.TEMA);
        controlador.crearEtiqueta("Emocion", TipoEtiqueta.SENTIMIENTO);

        // creamos los paneles una sola vez
        PanelCargarTexto panelCargar = new PanelCargarTexto(controlador, usuario);
        PanelFragmentos panelFrag = new PanelFragmentos(controlador, usuario);
        PanelResumen panelRes = new PanelResumen(controlador, usuario);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Cargar texto", panelCargar);
        tabs.addTab("Fragmentos", panelFrag);
        tabs.addTab("Resumen", panelRes);

        // cuando cambie de tab, refrescamos los paneles que dependen de la lista
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int idx = tabs.getSelectedIndex();
                if (idx == 1) { // Fragmentos
                    panelFrag.refrescarDatos();
                } else if (idx == 2) { // Resumen
                    panelRes.refrescarDatos();
                }
            }
        });

        add(tabs, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}
