package com.pacheco.game.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ResourceUtil {

    public static String getContentFromResource(String resourcePath) {
        String content = "";
        try {
            List<String> lines = Files.readAllLines(Path.of(resourcePath), Charset.forName("UTF-8"));
            content = lines.stream().reduce("", (accum, line) -> (accum + '\n' + line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}