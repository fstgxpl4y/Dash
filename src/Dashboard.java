import java.util.ArrayList;
import java.util.Collections;

public class Dashboard {

    private ArrayList<Renderizable> widgets;

    public Dashboard() {
        widgets = new ArrayList<>();
    }

    public void init() {
        widgets.add(new WeatherWidget(1, "Clima capital", 120, "Ciudad de Guatemala", 24.0));

        TaskWidget tareasTrabajo = new TaskWidget(2, "Pendientes", 85);
        tareasTrabajo.agregarTarea("Entregar archivos DAA");
        tareasTrabajo.agregarTarea("Registrar usuarios nuevos");
        tareasTrabajo.agregarTarea("Reunion de equipo");
        widgets.add(tareasTrabajo);

        widgets.add(new AlertWidget(3, "Alerta servidor", 40, "Servidor principal con CPU al 95%", "ALTA"));
        widgets.add(new ClockWidget(4, "Reloj Guatemala", 30, "America/Guatemala"));
        widgets.add(new QuoteWidget(5, "Frase del dia", 15, "El exito es la suma de pequenos esfuerzos repetidos dia tras dia", "Robert Collier"));
        widgets.add(new WeatherWidget(6, "Clima Xela", 118, "Quetzaltenango", 15.0));

        TaskWidget tareasU = new TaskWidget(7, "Pendientes de la U", 64);
        tareasU.agregarTarea("Terminar el ejercicio de POO");
        tareasU.agregarTarea("Estudiar para el parcial");
        widgets.add(tareasU);

        widgets.add(new AlertWidget(8, "Alerta respaldo", 38, "El respaldo de anoche no se completo", "MEDIA"));
        widgets.add(new ClockWidget(9, "Reloj Madrid", 28, "Europe/Madrid"));
        widgets.add(new QuoteWidget(10, "Frase motivacional", 12, "No cuentes los dias, haz que los dias cuenten", "Muhammad Ali"));
    }

    public void agregarWidget(Renderizable widget) {
        widgets.add(widget);
    }

    public ArrayList<Renderizable> getWidgets() {
        return widgets;
    }

    public Renderizable buscarPorId(int id) {
        for (Renderizable w : widgets) {
            if (w.getId() == id) {
                return w;
            }
        }
        return null;
    }

    public Renderizable buscarPorNombre(String nombre) {
        for (Renderizable w : widgets) {
            if (w.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                return w;
            }
        }
        return null;
    }

    public boolean esInteractivo(Renderizable widget) {
        return widget instanceof Interactivo;
    }

    public String interactuarCon(int id) {
        Renderizable w = buscarPorId(id);
        if (w == null) {
            return "No encontre ningun widget con ese ID.";
        }
        if (esInteractivo(w)) {
            Interactivo interactivo = (Interactivo) w;
            return interactivo.interactuar();
        }
        return "Ese widget solo se muestra en pantalla, no tiene nada que hacerle.";
    }

    public void ordenarPorMemoria() {
        Collections.sort(widgets);
    }

    public int getMemoriaTotal() {
        int total = 0;
        for (Renderizable w : widgets) {
            total += w.getMemoriaKB();
        }
        return total;
    }
}
