import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlertWidget implements Renderizable, Interactivo {

    private int id;
    private String nombre;
    private int memoriaKB;
    private String mensaje;
    private String prioridad;
    private boolean leida;

    public AlertWidget(int id, String nombre, int memoriaKB, String mensaje, String prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.memoriaKB = memoriaKB;
        this.mensaje = mensaje;
        this.prioridad = prioridad;
        this.leida = false;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public int getMemoriaKB() { return memoriaKB; }

    public String getMensaje() { return mensaje; }

    public String getPrioridad() { return prioridad; }

    public boolean isLeida() { return leida; }

    @Override
    public String renderizar() {
        String estado = leida ? "LEIDA" : "NUEVA";
        return "[Alerta] Prioridad " + prioridad + " (" + estado + "): " + mensaje;
    }

    @Override
    public String interactuar() {
        if (leida) {
            return "Esa alerta ya la habias leido.";
        }
        leida = true;
        return "Listo, la alerta quedo marcada como leida.";
    }

    private String textoAlerta() {
        String estado = leida ? "LEIDA" : "NUEVA";
        String color = leida ? "gray" : "red";
        return "<html><div style='width:230px'><b><font color='" + color + "'>PRIORIDAD " + prioridad + " - " + estado
                + "</font></b><br>" + mensaje + "</div></html>";
    }

    @Override
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("ID " + id + " - Alerta"));

        JLabel lblAlerta = new JLabel(textoAlerta());
        JButton btnLeida = new JButton("Marcar como leida");
        btnLeida.setEnabled(!leida);
        btnLeida.addActionListener(e -> {
            interactuar();
            lblAlerta.setText(textoAlerta());
            btnLeida.setEnabled(false);
        });

        panel.add(lblAlerta, BorderLayout.CENTER);
        panel.add(btnLeida, BorderLayout.SOUTH);
        return panel;
    }

    @Override
    public String toString() {
        return String.format("ID %d | %s | %d KB | %s", id, nombre, memoriaKB, renderizar());
    }
}
