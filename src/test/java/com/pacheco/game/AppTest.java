package com.pacheco.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.pacheco.game.map.MapModel;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        MapModel mapModel = mapper.readValue(new File("src/main/resources/maps/default.yaml"), MapModel.class);
        System.out.println(mapModel);
    }
}
