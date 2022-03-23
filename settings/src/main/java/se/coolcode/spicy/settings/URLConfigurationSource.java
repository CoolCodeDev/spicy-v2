package se.coolcode.spicy.settings;

import se.coolcode.spicy.json.Json;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

public class URLConfigurationSource implements ConfigurationSource {

    private final HttpClient httpClient;
    private final HttpRequest requestTemplate;

    public URLConfigurationSource(String url) {
        this.httpClient = HttpClient.newHttpClient();
        this.requestTemplate = createRequestTemplate(url);
    }

    private HttpRequest createRequestTemplate(String url) {
        return HttpRequest.newBuilder(URI.create(url))
                .header("Content-Type", "application/json")
                .build();
    }

    @Override
    public String getValue(String key) {
        Set<String> keys = Set.of(key);
        Map<String, String> values = getValues(keys);
        return values.get(key);
    }

    @Override
    public Map<String, String> getValues(Set<String> keys) {
        String requestBody = createRequestBody(keys);
        HttpRequest request = createRequest(requestBody);
        HttpResponse<String> response = send(request);
        return response == null ? Map.of() : Json.toMap(response.body());
    }

    private HttpRequest createRequest(String requestBody) {
        return HttpRequest.newBuilder(this.requestTemplate, (n, v) -> true)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
    }

    private HttpResponse<String> send(HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String createRequestBody(Set<String> keys) {
        return Json.toJsonArray(Map.of("keys", keys));
    }

}
