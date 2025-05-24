import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ApiConsumer {

    public static void consumirApi() {
        try {
            // URL e credenciais
            String apiUrl = "https://urldaapi";
            String username = "usuario";
            String password = "senha";

            // Criar a conexão HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar a requisição
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Configurar autenticação Basic
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

            // Habilitar envio de body
            connection.setDoOutput(true);

            // Escrever o body da requisição
            String requestBody = "{\"campo\":\"valor\"}";
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Obter o status HTTP
            int statusCode = connection.getResponseCode();
            System.out.println("Status HTTP: " + statusCode);

            // Ler a resposta
            StringBuilder response = new StringBuilder();
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // Imprimir a resposta como string JSON compacta
            System.out.println("Resposta como string: " + response.toString());

            // Imprimir a resposta como JSON formatado (se for JSON válido)
            try {
                // Tentar formatar como JSON bonito (apenas para visualização)
                String formattedJson = response.toString()
                        .replace("{", "{\n    ")
                        .replace(",", ",\n    ")
                        .replace("}", "\n}");
                System.out.println("Resposta como JSON formatado:\n" + formattedJson);
            } catch (Exception e) {
                // Se não for JSON válido, apenas imprime como string
                System.out.println("Resposta (não é JSON válido): " + response.toString());
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        consumirApi();
    }
}
