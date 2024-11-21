package io.hhplus.ecommerce.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "common")
public class Properties {

    private String slackApiUrl;

    private String slackToken;

    private String slackChannelId;
}
