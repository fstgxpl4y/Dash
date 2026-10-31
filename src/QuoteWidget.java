import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuoteWidget implements Renderizable {

    private int id;
    private String nombre;
    private int memoriaKB;
    private String frase;
    private String autor;

    public QuoteWidget(int id, String nombre, int memoriaKB, String frase, String autor) {
        this.id = id;
        this.nombre = nombre;
        this.memoriaKB = memoriaKB;
        this.frase = frase;
        this.autor = autor;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public int getMemoriaKB() { return memoriaKB; }

    public String getFrase() { return frase; }

    public String getAutor() { return autor; }

    @Override
    public String renderizar() {
        return "[Frase] \"" + frase + "\" - " + autor;
    }

    @Override
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("ID " + id + " - " + nombre));

        JLabel lblFrase = new JLabel("<html><div style='width:230px'><i>\"" + frase + "\"</i><br><br>- " + autor + "</div></html>");
        panel.add(lblFrase, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public String toString() {
        return String.format("ID %d | %s | %d KB | %s", id, nombre, memoriaKB, renderizar());
    }
}
