import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIConnector {
    private static final String API_KEY = "555c43fabd0ec890b1a2ef50";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static JsonObject getExchangeRates(String baseCurrency) {
        try {
            URL url = new URL(BASE_URL + API_KEY + "/latest/" + baseCurrency);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error en la conexión: HTTP " + conn.getResponseCode());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            conn.disconnect();

            JsonParser parser = new JsonParser();
            return parser.parse(response.toString()).getAsJsonObject();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}