package com.api.helpoflegends.Dtos;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChampionDto implements Comparable<ChampionDto>{
    
    private int key;
    private String name;
    private String splash = "";
    @SerializedName("icon") // nome que vai aparecer no json
    private String icon = "";
    private String lore;
    private String blurb;
    private ArrayList<SpellDto> spells;
    private ArrayList<SkinDto> skins;
    private ImageDto image;

    public ChampionDto() {
        super();
    }

    @Override public int compareTo(ChampionDto anotherChampion) { // Ordena os campeões por nome

        if(this.name.compareTo(anotherChampion.getName()) < 0) {
            return -1;
        } else if(this.name.compareTo(anotherChampion.getName()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    
}
