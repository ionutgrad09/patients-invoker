package patients.invoker.invoker;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class UrlInvokerRunner {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String TARGET_URL = "https://patients-api-zyvs.onrender.com/api/currentUser";

    @Bean
    public ApplicationRunner runTask() {
        return args -> {
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                try {
                    String response = restTemplate.getForObject(TARGET_URL, String.class);
                    System.out.println("Response: " + response);
                } catch (Exception e) {
                    System.err.println("Error invoking URL: " + e.getMessage());
                }
            }, 0, 10, TimeUnit.MINUTES); // Runs every 60 seconds
        };
    }
}
