package com.api.helpoflegends.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.helpoflegends.Services.ChampionService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/champion")
public class ChampionController{

    ChampionService championService = new ChampionService();
    
    @GetMapping
    public ResponseEntity<Object> getAll(){

        String list = championService.getAll();

        if(list == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Champions not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> getByName(@PathVariable(value = "name") String name) {

        String list = championService.getByName(name);

        if(list == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Champion not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
