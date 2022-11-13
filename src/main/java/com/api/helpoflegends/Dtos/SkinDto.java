package com.api.helpoflegends.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkinDto {

    private String name;
    private String splash;
    private int num;

    public SkinDto() {
        super();
    }
}
