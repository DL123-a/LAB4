import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelCargarTexto extends JPanel {
    private Controlador controlador;
    private JTextArea areaTexto;
    private JTextField campoTitulo;

    public PanelCargarTexto(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Cargar Documento", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        campoTitulo = new JTextField("Título del documento");
        areaTexto = new JTextArea("Escriba o pegue aquí el texto...");
        JButton botonGuardar = new JButton("Guardar Documento");
        JButton botonFragmento = new JButton("Ir a Fragmentos");
        JButton botonResumen = new JButton("Ver Resumen");

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonGuardar);
        panelBotones.add(botonFragmento);
        panelBotones.add(botonResumen);

        add(titulo, BorderLayout.NORTH);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        add(campoTitulo, BorderLayout.SOUTH);
        add(panelBotones, BorderLayout.PAGE_END);

        botonGuardar.addActionListener(e -> {
            Documento doc = new Documento(campoTitulo.getText(), new FuenteTextoPlano(areaTexto.getText()));
            controlador.agregarDocumento(doc);
            JOptionPane.showMessageDialog(this, "Documento guardado correctamente.");
        });

        botonFragmento.addActionListener(e -> controlador.mostrarPanelFragmento());
        botonResumen.addActionListener(e -> controlador.mostrarPanelResumen());
    }
}
