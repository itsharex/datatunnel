package com.superior.datatunnel.kafka;

import com.superior.datatunnel.api.ParamKey;
import com.superior.datatunnel.api.model.SourceOption;

import javax.validation.constraints.NotBlank;

public class KafkaSourceOption extends SourceOption {

    @NotBlank(message = "subscribe can not blank")
    private String subscribe;

    @ParamKey("kafka.bootstrap.servers")
    @NotBlank(message = "kafka.bootstrap.servers can not blank")
    private String servers;

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }
}