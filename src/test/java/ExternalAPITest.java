import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.input.UsuarioDTOInput;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import spark.Spark;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExternalAPITest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        Spark.awaitInitialization();
    }

    @Test
    public void testUserInserction() throws IOException {
        UsuarioDTOInput usuarioDTOInput = generateUserData();

        String jsonInput = objectMapper.writeValueAsString(usuarioDTOInput);

        HttpURLConnection apiConnection = (HttpURLConnection) new URL("https://randomuser.me/api/").openConnection();
        apiConnection.setRequestMethod("GET");
        int apiResponseCode = apiConnection.getResponseCode();

        String baseUrl = "http://127.0.0.1:4567/usuarios";
        HttpURLConnection appConnection = (HttpURLConnection) new URL(baseUrl).openConnection();
        appConnection.setRequestMethod("POST");
        appConnection.setDoOutput(true);
        appConnection.getOutputStream().write(jsonInput.getBytes());
        int appResponseCode = appConnection.getResponseCode();

        Assert.assertEquals(200, apiResponseCode); // Código de resposta da API externa
        Assert.assertEquals(201, appResponseCode); // Código de resposta da minha API
    }

    private UsuarioDTOInput generateUserData() {
        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();
        usuarioDTOInput.setNome("Desmond Hume");
        usuarioDTOInput.setSenha("l0stS3rie-2004");
        return usuarioDTOInput;
    }
}
