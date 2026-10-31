import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TaskWidget implements Renderizable, Interactivo {

    private int id;
    private String nombre;
    private int memoriaKB;
    private ArrayList<String> pendientes;
    private ArrayList<String> completadas;

    public TaskWidget(int id, String nombre, int memoriaKB) {
        this.id = id;
        this.nombre = nombre;
        this.memoriaKB = memoriaKB;
        this.pendientes = new ArrayList<>();
        this.completadas = new ArrayList<>();
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public int getMemoriaKB() { return memoriaKB; }

    public void agregarTarea(String tarea) {
        pendientes.add(tarea);
    }

    public boolean completarTarea(int indice) {
        if (indice < 0 || indice >= pendientes.size()) {
            return false;
        }
        completadas.add(pendientes.remove(indice));
        return true;
    }

    public int getCantidadPendientes() { return pendientes.size(); }

    @Override
    public String renderizar() {
        StringBuilder sb = new StringBuilder("[Tareas] " + nombre + "\n");
        for (String t : pendientes) {
            sb.append("  [ ] ").append(t).append("\n");
        }
        for (String t : completadas) {
            sb.append("  [x] ").append(t).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String interactuar() {
        if (pendientes.isEmpty()) {
            return "No hay tareas pendientes.";
        }
        String tarea = pendientes.get(0);
        completarTarea(0);
        return "Tarea completada: " + tarea;
    }

    private String textoLista() {
        String texto = "<html>";
        for (String t : pendientes) {
            texto += "&#9744; " + t + "<br>";
        }
        for (String t : completadas) {
            texto += "&#9745; <s>" + t + "</s><br>";
        }
        return texto + "</html>";
    }

    @Override
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("ID " + id + " - " + nombre));

        JLabel lblLista = new JLabel(textoLista());
        JButton btnCompletar = new JButton("Completar tarea");
        btnCompletar.addActionListener(e -> {
            interactuar();
            lblLista.setText(textoLista());
        });

        panel.add(lblLista, BorderLayout.CENTER);
        panel.add(btnCompletar, BorderLayout.SOUTH);
        return panel;
    }

    @Override
    public String toString() {
        return String.format("ID %d | %s | %d KB | %d pendientes, %d hechas",
                id, nombre, memoriaKB, pendientes.size(), completadas.size());
    }
}
