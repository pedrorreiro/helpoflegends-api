package com.api.helpoflegends.External;

import org.springframework.web.client.RestTemplate;

import lombok.Getter;

@Getter
public class RiotApi {

    private String apiKey = "RGAPI-31aa32ac-6c1d-4bd2-96f2-f19a8c90d947";
    private String version = "";

    public RiotApi() {
        RestTemplate restTemplate = new RestTemplate();

        String[] versionList = restTemplate.getForObject("https://ddragon.leagueoflegends.com/api/versions.json",
                String[].class);

        if (versionList != null) {
            this.version = versionList[0];
        }

    }

}
