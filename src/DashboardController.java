public class DashboardController {

    private Dashboard modelo;
    private DashboardView vista;

    public DashboardController(Dashboard modelo, DashboardView vista) {
        this.modelo = modelo;
        this.vista = vista;

        modelo.init();
        actualizarVista();

        vista.getBtnListar().addActionListener(e -> listar());
        vista.getBtnOrdenar().addActionListener(e -> ordenar());
        vista.getBtnBuscar().addActionListener(e -> buscar());
    }

    private void actualizarVista() {
        vista.mostrarWidgets(modelo.getWidgets());
        vista.actualizarInfo(modelo.getWidgets().size(), modelo.getMemoriaTotal());
    }

    private void listar() {
        String texto = "";
        for (Renderizable w : modelo.getWidgets()) {
            String tipo = modelo.esInteractivo(w) ? "interactivo" : "solo lectura";
            texto += w.toString() + " | " + tipo + "\n";
        }
        vista.mostrarLista(texto);
    }

    private void ordenar() {
        modelo.ordenarPorMemoria();
        actualizarVista();
    }

    private void buscar() {
        String texto = vista.getTextoBuscar();
        if (texto.isEmpty()) {
            vista.mostrarMensaje("Escribi un ID o un nombre para buscar.");
            return;
        }

        Renderizable encontrado;
        if (texto.matches("\\d+")) {
            encontrado = modelo.buscarPorId(Integer.parseInt(texto));
        } else {
            encontrado = modelo.buscarPorNombre(texto);
        }

        if (encontrado == null) {
            vista.mostrarMensaje("No aparecio ningun widget con eso.");
        } else {
            vista.mostrarMensaje(encontrado.toString());
        }
    }
}
