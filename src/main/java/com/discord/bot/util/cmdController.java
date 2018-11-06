package com.discord.bot.util;

import de.btobastian.javacord.entities.message.Message;

import java.util.*;

public class cmdController {


    final static boolean DEV_MODE = true;


    // 사다리타기
    static public List<String> sadari(int mode, String numWinner, String targets){
        List<String> target = Arrays.asList(targets.split("\\s*,\\s*"));
        Collections.shuffle(target);
        return target.subList(0, Integer.valueOf(numWinner));
    }



    // 보스타이머
    public static int[] bossTimeCounter = new int[20];
    public static Timer[] timerManager = new Timer[20];


    public static Timer bossTimer(String who, Message message, int Delay){
        final Message response = message;
        final String bossName = who;
        final  Timer bossTimer = new Timer();
        TimerTask bossTask = new TimerTask() {
            @Override
            public void run() {
                int bossID = 0;
                if(bossName.equals("드레이크")){
                    bossID = typeController.LINEAGE_BOSS_ID_DRAKE;
                }else if(bossName.equals("카스파")){
                    bossID = typeController.LINEAGE_BOSS_ID_CASPA;
                }else if(bossName.equals("이프리트")){
                    bossID = typeController.LINEAGE_BOSS_ID_IFRIT;
                }

                int rTime = 3600 - bossTimeCounter[bossID];
                if(bossTimeCounter[bossID] < 3600){

                    if(rTime  % 60 == 0){

                        // 10분 단위
                        if(rTime  % 600 == 0){
                            System.out.println(bossName + " " + rTime / 60 + "분 전");
                            if(!DEV_MODE)
                                response.reply(bossName + " " + rTime / 60 + "분 전");

                            // 10분 이내
                        }else if(rTime  < 600){
                            System.out.println(bossName + " " + rTime / 60 + "분 전");
                            if(!DEV_MODE)
                                response.reply(bossName + " " + rTime / 60 + "분 전");
                        }

                    }

                    // 30초 이내
                    if(rTime < 30){

                        // 20, 10초
                        if(rTime  % 10 == 0){
                            System.out.println(bossName + " " + rTime  + "초 전");
                            if(!DEV_MODE)
                                response.reply(bossName + " " + rTime  + "초 전");

                            // 5초 이내
                        }else if(rTime  < 6){
                            System.out.println(bossName + " " + rTime  + "초 전");
                            if(!DEV_MODE)
                                response.reply(bossName + " " + rTime  + "초 전");
                        }

                    }
                    bossTimeCounter[bossID]++;
                }else{
                    bossTimer.cancel();
                }
            }
        };
        bossTimer.schedule(bossTask, Delay, 100);

        return bossTimer;
    }

//    public static int getWinner(int area){
//        Random rand = new Random();
//        return rand.nextInt(area);
//    }
}
