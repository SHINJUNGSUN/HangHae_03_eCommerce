package io.hhplus.ecommerce.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SlackMessageUtil {

    private final String SLACK_API_URL = "";
    private final String SLACK_TOKEN = "";
    private final String SLACK_CHANNEL_ID = "";

    public void sendMessage(String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + SLACK_TOKEN);

        String payload = String.format("{\"channel\":\"%s\", \"text\":\"%s\"}", SLACK_CHANNEL_ID, message);

        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(SLACK_API_URL, HttpMethod.POST, requestEntity, String.class);

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
