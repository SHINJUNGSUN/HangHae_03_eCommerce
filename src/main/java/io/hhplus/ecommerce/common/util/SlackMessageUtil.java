package io.hhplus.ecommerce.common.util;

import io.hhplus.ecommerce.common.Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class SlackMessageUtil {

    private final Properties properties;

    public void sendMessage(String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + properties.getSlackToken());

        String payload = String.format("{\"channel\":\"%s\", \"text\":\"%s\"}", properties.getSlackChannelId(), message);

        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(properties.getSlackApiUrl(), HttpMethod.POST, requestEntity, String.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            if (responseBody != null && responseBody.contains("\"ok\":true")) {
                log.info("[SUCCESS] SLACK MESSAGE SENT SUCCESSFULLY: {}", responseBody);
            } else {
                log.error("[ERROR] SLACK MESSAGE FAILED: {}", responseBody);
                throw new IllegalStateException("[ERROR] SLACK MESSAGE FAILED");
            }
        } else {
            log.error("[ERROR] FAILED TO SEND SLACK MESSAGE");
            throw new IllegalStateException("[ERROR] FAILED TO SEND SLACK MESSAGE");
        }
    }
}
