import javax.swing.JComponent;

public interface Renderizable extends Comparable<Renderizable> {

    int getId();

    String getNombre();

    int getMemoriaKB();

    String renderizar();

    JComponent obtenerVista();

    @Override
    default int compareTo(Renderizable otro) {
        return Integer.compare(this.getMemoriaKB(), otro.getMemoriaKB());
    }
}
