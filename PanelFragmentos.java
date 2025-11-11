import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelFragmentos extends JPanel {

    private Controlador controlador;
    private Usuario usuario;

    private JComboBox<Documento> cmbDocumentos;
    private JTextArea txtDocumento;
    private DefaultListModel<Fragmento> modeloFragmentos;
    private JList<Fragmento> listaFragmentos;
    private JComboBox<Etiqueta> cmbEtiquetas;
    private JTextField txtInicio;
    private JTextField txtFin;
    private JTextField txtNuevaEtiqueta;
    private JComboBox<String> cmbTipoEtiqueta;

    public PanelFragmentos(Controlador controlador, Usuario usuario) {
        this.controlador = controlador;
        this.usuario = usuario;
        initUI();
        cargarDocumentos();
        cargarEtiquetas();
    }

    // 👉 lo nuevo
    public void refrescarDatos() {
        cargarDocumentos();
        cargarEtiquetas();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cmbDocumentos = new JComboBox<>();
        cmbDocumentos.addActionListener(e -> mostrarDocumentoSeleccionado());
        panelTop.add(new JLabel("Documento:"));
        panelTop.add(cmbDocumentos);
        add(panelTop, BorderLayout.NORTH);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        txtDocumento = new JTextArea();
        txtDocumento.setEditable(false);
        txtDocumento.setLineWrap(true);
        txtDocumento.setWrapStyleWord(true);
        split.setLeftComponent(new JScrollPane(txtDocumento));

        modeloFragmentos = new DefaultListModel<>();
        listaFragmentos = new JList<>(modeloFragmentos);
        split.setRightComponent(new JScrollPane(listaFragmentos));
        split.setDividerLocation(400);
        add(split, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel(new GridLayout(2, 1));

        JPanel panelFrag = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtInicio = new JTextField(5);
        txtFin = new JTextField(5);
        JButton btnCrearFrag = new JButton("Crear fragmento");
        btnCrearFrag.addActionListener(this::crearFragmento);
        panelFrag.add(new JLabel("Inicio:"));
        panelFrag.add(txtInicio);
        panelFrag.add(new JLabel("Fin:"));
        panelFrag.add(txtFin);
        panelFrag.add(btnCrearFrag);

        JPanel panelEti = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cmbEtiquetas = new JComboBox<>();
        JButton btnAplicar = new JButton("Aplicar etiqueta");
        btnAplicar.addActionListener(this::aplicarEtiqueta);
        txtNuevaEtiqueta = new JTextField(10);
        cmbTipoEtiqueta = new JComboBox<>(new String[]{"TEMA","SENTIMIENTO","OTRO"});
        JButton btnCrearEti = new JButton("Crear etiqueta");
        btnCrearEti.addActionListener(this::crearEtiquetaNueva);
        panelEti.add(new JLabel("Etiquetas:"));
        panelEti.add(cmbEtiquetas);
        panelEti.add(btnAplicar);
        panelEti.add(new JLabel("Nueva:"));
        panelEti.add(txtNuevaEtiqueta);
        panelEti.add(cmbTipoEtiqueta);
        panelEti.add(btnCrearEti);

        panelBottom.add(panelFrag);
        panelBottom.add(panelEti);
        add(panelBottom, BorderLayout.SOUTH);
    }

    private void cargarDocumentos() {
        cmbDocumentos.removeAllItems();
        for (Documento d : controlador.listarDocumentos()) {
            cmbDocumentos.addItem(d);
        }
        mostrarDocumentoSeleccionado();
    }

    private void cargarEtiquetas() {
        cmbEtiquetas.removeAllItems();
        for (Etiqueta e : controlador.listarEtiquetas()) {
            cmbEtiquetas.addItem(e);
        }
    }

    private void mostrarDocumentoSeleccionado() {
        Documento doc = (Documento) cmbDocumentos.getSelectedItem();
        modeloFragmentos.clear();
        if (doc == null) {
            txtDocumento.setText("");
            return;
        }
        txtDocumento.setText(doc.getContenido());
        for (Fragmento f : doc.getFragmentos()) {
            modeloFragmentos.addElement(f);
        }
    }

    private void crearFragmento(ActionEvent e) {
        Documento doc = (Documento) cmbDocumentos.getSelectedItem();
        if (doc == null) return;
        try {
            int ini = Integer.parseInt(txtInicio.getText().trim());
            int fin = Integer.parseInt(txtFin.getText().trim());
            Fragmento f = controlador.crearFragmento(doc, ini, fin);
            modeloFragmentos.addElement(f);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Rango invalido");
        }
    }

    private void aplicarEtiqueta(ActionEvent e) {
        Fragmento frag = listaFragmentos.getSelectedValue();
        Etiqueta et = (Etiqueta) cmbEtiquetas.getSelectedItem();
        if (frag == null || et == null) {
            JOptionPane.showMessageDialog(this, "Seleccione fragmento y etiqueta");
            return;
        }
        controlador.aplicarEtiquetaAFragmento(frag, et, usuario);
        JOptionPane.showMessageDialog(this, "Etiqueta aplicada");
    }

    private void crearEtiquetaNueva(ActionEvent e) {
        String nombre = txtNuevaEtiqueta.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese nombre de etiqueta");
            return;
        }
        String tipoSel = (String) cmbTipoEtiqueta.getSelectedItem();
        TipoEtiqueta tipo;
        switch (tipoSel) {
            case "TEMA" -> tipo = TipoEtiqueta.TEMA;
            case "SENTIMIENTO" -> tipo = TipoEtiqueta.SENTIMIENTO;
            default -> tipo = TipoEtiqueta.OTRO;
        }
        Etiqueta et = controlador.crearEtiqueta(nombre, tipo);
        cargarEtiquetas();
        cmbEtiquetas.setSelectedItem(et);
        txtNuevaEtiqueta.setText("");
    }
}