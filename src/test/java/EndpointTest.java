import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class EndpointTest {
    @Test
    public void listingTest() throws IOException {
        URL url = new URL("http://127.0.0.1:4567/usuarios");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        ObjectMapper objectMapper = new ObjectMapper();
        int responseCode = connection.getResponseCode();

        Assert.assertEquals(200, responseCode);
    }
}
