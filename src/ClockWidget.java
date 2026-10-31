import java.awt.BorderLayout;
import java.awt.Font;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ClockWidget implements Renderizable {

    private int id;
    private String nombre;
    private int memoriaKB;
    private String zonaHoraria;
    private JPanel panel;

    public ClockWidget(int id, String nombre, int memoriaKB, String zonaHoraria) {
        this.id = id;
        this.nombre = nombre;
        this.memoriaKB = memoriaKB;
        this.zonaHoraria = zonaHoraria;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public int getMemoriaKB() { return memoriaKB; }

    public String getZonaHoraria() { return zonaHoraria; }

    public String getHoraActual() {
        LocalTime hora = LocalTime.now(ZoneId.of(zonaHoraria));
        return hora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Override
    public String renderizar() {
        return "[Reloj] " + zonaHoraria + ": " + getHoraActual();
    }

    @Override
    public JComponent obtenerVista() {
        if (panel == null) {
            panel = new JPanel(new BorderLayout(5, 5));
            panel.setBorder(BorderFactory.createTitledBorder("ID " + id + " - Reloj"));

            JLabel lblZona = new JLabel(zonaHoraria);
            JLabel lblHora = new JLabel(getHoraActual());
            lblHora.setFont(new Font("SansSerif", Font.BOLD, 30));

            Timer timer = new Timer(1000, e -> lblHora.setText(getHoraActual()));
            timer.start();

            panel.add(lblZona, BorderLayout.NORTH);
            panel.add(lblHora, BorderLayout.CENTER);
        }
        return panel;
    }

    @Override
    public String toString() {
        return String.format("ID %d | %s | %d KB | %s", id, nombre, memoriaKB, renderizar());
    }
}
