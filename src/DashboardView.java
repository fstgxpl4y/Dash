import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DashboardView extends JFrame {

    private JLabel lblInfo;
    private JPanel panelWidgets;
    private JButton btnListar;
    private JButton btnOrdenar;
    private JButton btnBuscar;
    private JTextField txtBuscar;

    public DashboardView() {
        setTitle("Dashboard Empresarial");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel barraSuperior = new JPanel(new BorderLayout());
        barraSuperior.setBackground(new Color(200, 206, 220));
        barraSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitulo = new JLabel("Dashboard Empresarial");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblInfo = new JLabel();
        barraSuperior.add(lblTitulo, BorderLayout.WEST);
        barraSuperior.add(lblInfo, BorderLayout.EAST);

        JPanel barraBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnListar = new JButton("Listar widgets");
        btnOrdenar = new JButton("Ordenar por memoria (KB)");
        txtBuscar = new JTextField(12);
        btnBuscar = new JButton("Buscar por ID / nombre");
        barraBotones.add(btnListar);
        barraBotones.add(btnOrdenar);
        barraBotones.add(txtBuscar);
        barraBotones.add(btnBuscar);

        JPanel arriba = new JPanel(new BorderLayout());
        arriba.add(barraSuperior, BorderLayout.NORTH);
        arriba.add(barraBotones, BorderLayout.SOUTH);

        panelWidgets = new JPanel(new GridLayout(0, 3, 10, 10));
        panelWidgets.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(arriba, BorderLayout.NORTH);
        add(new JScrollPane(panelWidgets), BorderLayout.CENTER);
    }

    public void mostrarWidgets(ArrayList<Renderizable> widgets) {
        panelWidgets.removeAll();
        for (Renderizable w : widgets) {
            panelWidgets.add(w.obtenerVista());
        }
        panelWidgets.revalidate();
        panelWidgets.repaint();
    }

    public void actualizarInfo(int cantidad, int memoriaTotal) {
        lblInfo.setText(cantidad + " widgets activos - " + memoriaTotal + " KB en uso");
    }

    public void mostrarLista(String texto) {
        JTextArea area = new JTextArea(texto, 15, 60);
        area.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Widgets activos", JOptionPane.PLAIN_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public String getTextoBuscar() {
        return txtBuscar.getText().trim();
    }

    public JButton getBtnListar() { return btnListar; }

    public JButton getBtnOrdenar() { return btnOrdenar; }

    public JButton getBtnBuscar() { return btnBuscar; }
}
