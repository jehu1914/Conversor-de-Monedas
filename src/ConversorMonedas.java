import com.google.gson.JsonObject;
import java.util.Scanner;

public class ConversorMonedas {
    private Scanner scanner;
    private JsonObject rates;

    public ConversorMonedas() {
        scanner = new Scanner(System.in);
        // Obtener tasas de cambio con USD como base
        rates = APIConnector.getExchangeRates("USD").getAsJsonObject("conversion_rates");
    }

    public void mostrarMenu() {
        System.out.println("\n=== CONVERSOR DE MONEDAS Jehu1914 ===");
        System.out.println("Seleccione la conversión que desea realizar:");

        System.out.println("1. Dólar (USD) → Peso Argentino (ARS)");
        System.out.println("2. Dólar (USD) → Peso Cubano (CUP)");
        System.out.println("3. Dólar (USD) → Real Brasileño (BRL)");
        System.out.println("4. Dólar (USD) → Guaraní Paraguayo (PYG)");
        System.out.println("5. Peso Argentino (ARS) → Dólar (USD)");
        System.out.println("6. Real Brasileño (BRL) → Peso Argentino (ARS)");
        System.out.println("7. Peso Argentino (ARS) → Real Brasileño (BRL)");
        System.out.println("8. Euro (EUR) → Dólar (USD)");
        System.out.println("9. Euro (EUR) → Real Brasileño (BRL)");
        System.out.println("10. Euro (EUR) → Peso Argentino (ARS)");
        System.out.println("0. Salir");
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            System.out.print("\nIngrese su opción: ");

            try {
                opcion = scanner.nextInt();

                if (opcion >= 1 && opcion <= 10) {
                    procesarOpcion(opcion);
                } else if (opcion != 0) {
                    System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error: Solo puede ingresar números. Intente nuevamente.");
                scanner.next(); // Limpiar el buffer del scanner
                opcion = -1; // Forzar nueva iteración
            }

        } while (opcion != 0);

        System.out.println("Gracias por usar el Conversor de Monedas Jehu1914. ¡Hasta pronto!");
        scanner.close();
    }

    private void procesarOpcion(int opcion) {
        double cantidad = 0;

        try {
            System.out.print("Ingrese el valor a convertir: ");
            cantidad = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Error: Solo puede ingresar valores numéricos.");
            scanner.next(); // Limpiar el buffer del scanner
            return;
        }

        double resultado = 0;
        String descripcion = "";

        switch (opcion) {
            case 1:
                resultado = cantidad * rates.get("ARS").getAsDouble();
                descripcion = cantidad + " USD = " + resultado + " ARS";
                break;
            case 2:
                resultado = cantidad * rates.get("CUP").getAsDouble();
                descripcion = cantidad + " USD = " + resultado + " CUP";
                break;
            case 3:
                resultado = cantidad * rates.get("BRL").getAsDouble();
                descripcion = cantidad + " USD = " + resultado + " BRL";
                break;
            case 4:
                resultado = cantidad * rates.get("PYG").getAsDouble();
                descripcion = cantidad + " USD = " + resultado + " PYG";
                break;
            case 5:
                resultado = cantidad / rates.get("ARS").getAsDouble();
                descripcion = cantidad + " ARS = " + resultado + " USD";
                break;
            case 6:
                double usd = cantidad / rates.get("BRL").getAsDouble();
                resultado = usd * rates.get("ARS").getAsDouble();
                descripcion = cantidad + " BRL = " + resultado + " ARS";
                break;
            case 7:
                usd = cantidad / rates.get("ARS").getAsDouble();
                resultado = usd * rates.get("BRL").getAsDouble();
                descripcion = cantidad + " ARS = " + resultado + " BRL";
                break;
            case 8:
                resultado = cantidad / rates.get("EUR").getAsDouble();
                descripcion = cantidad + " EUR = " + resultado + " USD";
                break;
            case 9:
                usd = cantidad / rates.get("EUR").getAsDouble();
                resultado = usd * rates.get("BRL").getAsDouble();
                descripcion = cantidad + " EUR = " + resultado + " BRL";
                break;
            case 10:
                usd = cantidad / rates.get("EUR").getAsDouble();
                resultado = usd * rates.get("ARS").getAsDouble();
                descripcion = cantidad + " EUR = " + resultado + " ARS";
                break;
        }

        System.out.println("\nResultado de la conversión:");
        System.out.println(descripcion);
    }
}