package com.api.helpoflegends.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.api.helpoflegends.Dtos.ChampionDto;
import com.api.helpoflegends.Dtos.ChampionListDto;
import com.api.helpoflegends.External.RiotApi;
import com.google.gson.Gson;

@Service
public class ChampionService extends RiotApi {

    public ChampionService() {

        super();
    }

    public String getAll() {

        Gson gson = new Gson();

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://ddragon.leagueoflegends.com/cdn/" + this.getVersion() + "/data/pt_BR/champion.json";

        ResponseEntity<ChampionListDto> response = restTemplate.getForEntity(url, ChampionListDto.class);

        ChampionListDto championList = response.getBody();

        String jsonInString = "";

        if (championList != null) {
            jsonInString = gson.toJson(championList.getData());

            JSONObject jsonObject = new JSONObject(jsonInString); // Converte string para json

            Map<String, Object> map = new HashMap<String, Object>(); // Cria um map para receber o json

            for (String key : jsonObject.keySet()) {
                map.put(key, jsonObject.get(key)); // Adiciona o json no map
            }

            Object[] champions = map.keySet().toArray();

            ArrayList<ChampionDto> championDtos = new ArrayList<ChampionDto>();

            for (int i = 0; i < champions.length; i++) {

                var c = map.get(champions[i]).toString();

                ChampionDto champion = gson.fromJson(c, ChampionDto.class);

                String nameWithoutSpace = champion.getName().replace(" ", "");

                champion.setSplash(
                        "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + nameWithoutSpace + "_0.jpg");
                champion.setIcon("https://ddragon.leagueoflegends.com/cdn/" + this.getVersion() + "/img/champion/"
                        + nameWithoutSpace + ".png");

                championDtos.add(champion);

            }

            Collections.sort(championDtos);

            championList.setChampions(championDtos);

            jsonInString = gson.toJson(championList.getChampions());

            return jsonInString;

        }

        return null;

    }

    public String getByName(String name) {

        Gson gson = new Gson();

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://ddragon.leagueoflegends.com/cdn/" + this.getVersion() + "/data/pt_BR/champion/" + name
                + ".json";

        try {
            ResponseEntity<ChampionListDto> response = restTemplate.getForEntity(url, ChampionListDto.class);

            ChampionListDto championList = response.getBody();

            String jsonInString = "";

            if (championList != null) {
                jsonInString = gson.toJson(championList.getData());

                JSONObject jsonObject = new JSONObject(jsonInString); // Converte string para json

                Map<String, Object> map = new HashMap<String, Object>(); // Cria um map para receber o json

                for (String key : jsonObject.keySet()) {
                    map.put(key, jsonObject.get(key)); // Adiciona o json no map
                }

                Object[] champions = map.keySet().toArray();

                ArrayList<ChampionDto> championDtos = new ArrayList<ChampionDto>();

                for (int i = 0; i < champions.length; i++) {

                    var c = map.get(champions[i]).toString();

                    ChampionDto champion = gson.fromJson(c, ChampionDto.class);

                    String nameWithoutSpace = champion.getName().replace(" ", "");

                    champion.setSplash(
                            "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + nameWithoutSpace
                                    + "_0.jpg");
                    champion.setIcon("https://ddragon.leagueoflegends.com/cdn/" + this.getVersion() + "/img/champion/"
                            + champion.getImage().getFull());

                    champion.getSpells().forEach(spell -> {
                        spell.setDisplayImg("https://ddragon.leagueoflegends.com/cdn/" + this.getVersion() + "/img/spell/"
                                + spell.getImage().getFull());
                    });

                    champion.getSkins().forEach(skin -> {
                        skin.setSplash("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + nameWithoutSpace
                                + "_" + skin.getNum() + ".jpg");
                    });

                    championDtos.add(champion);

                }

                Collections.sort(championDtos);

                championList.setChampions(championDtos);

                jsonInString = gson.toJson(championList.getChampions());

                return jsonInString;

            }

        } catch (HttpStatusCodeException  e) {

            System.out.println(e.getMessage());

            return null;
        }

        return null;
    }
}
