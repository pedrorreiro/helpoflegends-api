package com.api.helpoflegends.Dtos;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpellDto {
    @SerializedName("name")
    public String full;
    public String description;
    public String displayImg = "";
    public String cooldownBurn = "";
    public ImageDto image;

    public SpellDto() {
        super();
    }
}
