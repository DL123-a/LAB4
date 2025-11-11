package LAB4;

import javax.swing.*;
import java.awt.*;

public class PanelResumen extends JPanel {
    private Controlador controlador;
    private JTextArea areaResumen;

    public PanelResumen(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Resumen General", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        areaResumen = new JTextArea();
        areaResumen.setEditable(false);

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> controlador.mostrarPanelCargarTexto());

        add(titulo, BorderLayout.NORTH);
        add(new JScrollPane(areaResumen), BorderLayout.CENTER);
        add(botonVolver, BorderLayout.SOUTH);
    }

    public void actualizarResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario: ").append(controlador.getUsuarioActual().getNombre()).append("\n");
        sb.append("Rol: ").append(controlador.getUsuarioActual().getRol()).append("\n\n");

        sb.append("=== Documentos cargados ===\n");
        for (Documento d : controlador.getDocumentos()) {
            sb.append("- ").append(d.getTitulo()).append("\n");
        }

        sb.append("\n=== Fragmentos creados ===\n");
        for (Fragmento f : controlador.getFragmentos()) {
            sb.append("- \"").append(f.getTexto()).append("\" | Etiqueta: ").append(f.getEtiqueta().getTipo()).append("\n");
        }

        areaResumen.setText(sb.toString());
    }
}


