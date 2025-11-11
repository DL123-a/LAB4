import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelFragmento extends JPanel {
    private final Controlador controlador;
    private final JTextField campoInicio, campoFin;
    private final JButton btnCrear, btnAplicarEtiqueta, btnListar;
    private final JComboBox<TipoEtiqueta> comboEtiquetas;

    public PanelFragmento(Controlador controlador) {
        this.controlador = controlador;
        setBorder(BorderFactory.createTitledBorder("Fragmentos y Etiquetas"));
        setLayout(new BorderLayout(6,6));
        setPreferredSize(new Dimension(420, 300));

        campoInicio = new JTextField(6);
        campoFin = new JTextField(6);
        btnCrear = new JButton("Crear fragmento");
        btnAplicarEtiqueta = new JButton("Aplicar etiqueta a fragmento");
        btnListar = new JButton("Mostrar fragmentos (ventana)");

        comboEtiquetas = new JComboBox<>(TipoEtiqueta.values());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Inicio:"));
        top.add(campoInicio);
        top.add(new JLabel("Fin:"));
        top.add(campoFin);
        top.add(btnCrear);

        JPanel middle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        middle.add(new JLabel("Etiqueta:"));
        middle.add(comboEtiquetas);
        middle.add(btnAplicarEtiqueta);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.add(btnListar);

        add(top, BorderLayout.NORTH);
        add(middle, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        btnCrear.addActionListener(e -> {
            try {
                int inicio = Integer.parseInt(campoInicio.getText().trim());
                int fin = Integer.parseInt(campoFin.getText().trim());
                Fragmento f = controlador.crearFragmento(inicio, fin);
                JOptionPane.showMessageDialog(this, "Fragmento creado:\n" + f.getTexto());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Inicio y fin deben ser números enteros.");
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnAplicarEtiqueta.addActionListener(e -> {
            List<Fragmento> frags = controlador.listarFragmentos();
            if (frags.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay fragmentos para etiquetar.");
                return;
            }
            String[] opciones = new String[frags.size()];
            for (int i = 0; i < frags.size(); i++) {
                String txt = frags.get(i).getTexto();
                if (txt.length() > 60) txt = txt.substring(0,60) + "...";
                opciones[i] = (i+1) + ") " + txt;
            }
            int seleccionado = JOptionPane.showOptionDialog(
                    this,
                    "Seleccione fragmento",
                    "Fragmentos",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );
            if (seleccionado < 0) return;
            TipoEtiqueta tipo = (TipoEtiqueta) comboEtiquetas.getSelectedItem();
            controlador.aplicarEtiquetaAFragmento(seleccionado, tipo);
            JOptionPane.showMessageDialog(this, "Etiqueta aplicada al fragmento.");
        });

        btnListar.addActionListener(e -> {
            List<Fragmento> frags = controlador.listarFragmentos();
            if (frags.isEmpty()) {
                JOptionPane.showMessageDialog(this, "(No hay fragmentos)");
                return;
            }
            StringBuilder sb = new StringBuilder();
            int n = 1;
            for (Fragmento f : frags) {
                sb.append(n++).append(") [").append(f.getInicio()).append("-").append(f.getFin()).append("] ");
                String t = f.getTexto();
                if (t.length() > 150) t = t.substring(0,150) + "...";
                sb.append(t).append("\n\n");
            }
            JTextArea ta = new JTextArea(sb.toString());
            ta.setEditable(false);
            JScrollPane sp = new JScrollPane(ta);
            sp.setPreferredSize(new Dimension(600, 300));
            JOptionPane.showMessageDialog(this, sp, "Fragmentos existentes", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
