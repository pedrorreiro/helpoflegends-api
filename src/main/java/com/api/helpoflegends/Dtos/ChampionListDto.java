package com.api.helpoflegends.Dtos;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ChampionListDto {

    private Object data;
    private ArrayList<ChampionDto> champions;
    
}
