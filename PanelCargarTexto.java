import javax.swing.*;
import java.awt.*;

public class PanelCargarTexto extends JPanel {
    private final Controlador controlador;
    private final JTextField campoTitulo;
    private final JTextArea areaTexto;
    private final JButton btnCargar, btnIrFragmentos;

    public PanelCargarTexto(Controlador controlador) {
        this.controlador = controlador;
        setBorder(BorderFactory.createTitledBorder("Cargar Documento"));
        setLayout(new BorderLayout(6,6));
        setPreferredSize(new Dimension(420, 300));

        campoTitulo = new JTextField();
        areaTexto = new JTextArea(8, 30);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);

        btnCargar = new JButton("Cargar documento");
        btnIrFragmentos = new JButton("Ir a fragmentos");

        btnCargar.addActionListener(e -> {
            String titulo = campoTitulo.getText().trim();
            String texto = areaTexto.getText();
            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un título válido.");
                return;
            }
            controlador.importarDocumento(titulo, texto);
            JOptionPane.showMessageDialog(this, "Documento cargado correctamente.");
        });

        btnIrFragmentos.addActionListener(e -> JOptionPane.showMessageDialog(this, "Baja al panel de Fragmentos."));

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Título:"), BorderLayout.WEST);
        top.add(campoTitulo, BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.add(btnCargar);
        south.add(btnIrFragmentos);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }
}
