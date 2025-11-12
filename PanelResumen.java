import javax.swing.*;
import java.awt.*;

public class PanelResumen extends JPanel {
    private Controlador controlador;
    private JTextArea areaResumen;

    public PanelResumen(Controlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Resumen General del Sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        areaResumen = new JTextArea();
        areaResumen.setEditable(false);
        areaResumen.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaResumen.setLineWrap(true);
        areaResumen.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(areaResumen);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    public void actualizarTexto() {
        areaResumen.setText(controlador.generarResumen());
    }
}
