package microservice.book.multiplication.client.gamification;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("endpoint.gamification-service")
public class GamificationServiceClientProperties {
    private String url;
}
