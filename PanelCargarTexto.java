import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;

public class PanelCargarTexto extends JPanel {

    private Controlador controlador;
    private Usuario usuario;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaDocs;
    private JTextField txtTitulo;
    private JTextArea txtContenido;

    public PanelCargarTexto(Controlador controlador, Usuario usuario) {
        this.controlador = controlador;
        this.usuario = usuario;
        initUI();
        cargarLista();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new BorderLayout(5, 5));
        JPanel panelTitulo = new JPanel(new BorderLayout(5, 5));

        txtTitulo = new JTextField();
        panelTitulo.add(new JLabel("Titulo:"), BorderLayout.WEST);
        panelTitulo.add(txtTitulo, BorderLayout.CENTER);

        txtContenido = new JTextArea(6, 40);
        txtContenido.setLineWrap(true);
        txtContenido.setWrapStyleWord(true);
        JScrollPane scrollContenido = new JScrollPane(txtContenido);

        JButton btnGuardar = new JButton("Guardar documento");
        btnGuardar.addActionListener(this::guardarDocumento);

        panelForm.add(panelTitulo, BorderLayout.NORTH);
        panelForm.add(scrollContenido, BorderLayout.CENTER);
        panelForm.add(btnGuardar, BorderLayout.SOUTH);

        add(panelForm, BorderLayout.CENTER);

        modeloLista = new DefaultListModel<>();
        listaDocs = new JList<>(modeloLista);
        JScrollPane scrollLista = new JScrollPane(listaDocs);
        scrollLista.setPreferredSize(new Dimension(200, 0));
        add(scrollLista, BorderLayout.EAST);
    }

    private void guardarDocumento(ActionEvent e) {
        String titulo = txtTitulo.getText().trim();
        String contenido = txtContenido.getText().trim();
        if (titulo.isEmpty() || contenido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Titulo y contenido son requeridos");
            return;
        }
        FuenteTexto fuente = new FuenteTextoPlano(titulo, contenido);
        controlador.importarFuente(fuente, usuario);
        txtTitulo.setText("");
        txtContenido.setText("");
        cargarLista();
    }

    private void cargarLista() {
        modeloLista.clear();
        List<Documento> docs = controlador.listarDocumentos();
        for (Documento d : docs) {
            modeloLista.addElement(d.getTitulo());
        }
    }
}

