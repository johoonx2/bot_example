package com.discord.bot.util;

import de.btobastian.javacord.entities.message.Message;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class cmdController {


    final static boolean DEV_MODE = false;


    // 사다리타기
    static public List<String> sadari(int mode, String numWinner, String targets){
        List<String> target = Arrays.asList(targets.split("\\s*,\\s*"));
        Collections.shuffle(target);
        return target.subList(0, Integer.valueOf(numWinner));
    }



    // 보스타이머
    public static int[] bossTimeCounter = new int[20];
    public static Timer[] timerManager = new Timer[20];
    public static boolean[] timerManagerStatus = new boolean[20];
    public static String[] timerManagerBoss = new String[20];
    public static int[] timerManagerRestTime = new int[20];


//    public static void initValues(){
//        Arrays.fill(bossTimeCounter, 0);
//        Arrays.fill(timerManagerStatus, false);
//        Arrays.fill(timerManagerBoss, "");
//        Arrays.fill(timerManagerRestTime, 0);
//    }

    public static List<String> activatedBoss(){

        List<String> bossList = new ArrayList<String>();

        int index = 0;
        boolean isExist = false;
        for (boolean isActivated : timerManagerStatus) {
            if(isActivated){
                bossList.add(timerManagerBoss[index] + " : " + (timerManagerRestTime[index] / 60) + " Min");
                isExist = true;
            }
            index ++;
        }

        if(!isExist){
            bossList.add("등록된 정보가 없습니다.");
        }
        return bossList;
    }

    public static Timer bossTimer(String who, Message message, int whoID, int whoInterval, int delayMin){
        final Message response = message;
        final Timer bossTime = new Timer();

//        if(DEV_MODE){
//            whoInterval = 250;
//            delayMin = -1;
//        }

        final String bossName = who;
        final int bossID = whoID;
        final int bossInterval = whoInterval + (delayMin * 60);
        timerManager[bossID] = bossTime;
        timerManagerStatus[bossID] = true;
        timerManagerBoss[bossID] = bossName.replace("/", "");

        if(DEV_MODE){
            System.out.println("Debug > " + bossName + " / ID " + bossID + " / TimeInterval " + bossInterval);
        }

        TimerTask bossTask = new TimerTask() {
            @Override
            public void run() {
                timerManagerRestTime[bossID] = bossInterval - bossTimeCounter[bossID];
                bossTimeCounter[bossID]++;


                if(DEV_MODE){
                        System.out.println("Debug > " + bossName + " : " + timerManagerRestTime[bossID] + "초 전");

                    if (timerManagerRestTime[bossID] == typeController.TIME_3_MIN) {
                        System.out.println(bossName + " 3분 전");
                    } else if (timerManagerRestTime[bossID] == typeController.TIME_1_MIN) {
                        System.out.println(bossName + " 1분 전");
                    } else if (timerManagerRestTime[bossID] <= 1) {
                        bossTime.cancel();
                        timerManagerStatus[bossID] = false;
                    }

                }else {
                    System.out.println("Debug > " + bossName + " : " + timerManagerRestTime[bossID] + "초 전");

                    if (timerManagerRestTime[bossID] == typeController.TIME_3_MIN) {
                        response.reply(bossName + " 3분 전");
                    } else if (timerManagerRestTime[bossID] == typeController.TIME_1_MIN) {
                        response.reply(bossName + " 1분 전");
                    } else if (timerManagerRestTime[bossID] <= 1) {
                        bossTime.cancel();
                        timerManagerStatus[bossID] = false;
                    }
                }
            }
        };
        bossTime.schedule(bossTask, 0, 1000);

        return bossTime;
    }

//    public static int getWinner(int area){
//        Random rand = new Random();
//        return rand.nextInt(area);
//    }
}
