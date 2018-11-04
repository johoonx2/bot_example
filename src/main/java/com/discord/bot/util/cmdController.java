package com.discord.bot.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class cmdController {
    static public List<String> sadari(int mode, String numWinner, String targets){
        List<String> target = Arrays.asList(targets.split("\\s*,\\s*"));
        Collections.shuffle(target);
        return target.subList(0, Integer.valueOf(numWinner));
    }

    public static int getWinner(int area){
        Random rand = new Random();
        return rand.nextInt(area);
    }
}
