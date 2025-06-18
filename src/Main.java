public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al Conversor de Monedas Jehu1914");
        System.out.println("Cargando tasas de cambio...");

        ConversorMonedas conversor = new ConversorMonedas();
        conversor.iniciar();
    }
}