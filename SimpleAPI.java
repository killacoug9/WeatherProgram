import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SimpleAPI {
    private HttpResponse<String> response;

    public SimpleAPI(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.ofMinutes(1)).GET().build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getResponse() {
        return response;
    }
}
