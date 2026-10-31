public class Main {

    public static void main(String[] args) {
        Dashboard modelo = new Dashboard();
        DashboardView vista = new DashboardView();
        new DashboardController(modelo, vista);
        vista.setVisible(true);
    }
}
