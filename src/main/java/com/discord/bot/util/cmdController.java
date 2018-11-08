package com.discord.bot.util;

import com.discord.bot.model.BossInfo;
import de.btobastian.javacord.entities.message.Message;

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


    // 보스타임 관리
    public static List<BossInfo> bossInfo = new ArrayList<BossInfo>();
    public static void initValues(){
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_NORTH_DRAKE, typeController.LINEAGE_BOSS_NORTH_DRAKE_ID,typeController.LINEAGE_BOSS_NORTH_DRAKE_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_MIDDLE_DRAKE, typeController.LINEAGE_BOSS_MIDDLE_DRAKE_ID,typeController.LINEAGE_BOSS_MIDDLE_DRAKE_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_EAST_DRAKE, typeController.LINEAGE_BOSS_EAST_DRAKE_ID,typeController.LINEAGE_BOSS_EAST_DRAKE_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_WEST_DRAKE, typeController.LINEAGE_BOSS_WEST_DRAKE_ID,typeController.LINEAGE_BOSS_WEST_DRAKE_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_CASPA, typeController.LINEAGE_BOSS_CASPA_ID,typeController.LINEAGE_BOSS_CASPA_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_GIANTCROCODILE, typeController.LINEAGE_BOSS_GIANTCROCODILE_ID,typeController.LINEAGE_BOSS_GIANTCROCODILE_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_ARPIER, typeController.LINEAGE_BOSS_ARPIER_ID,typeController.LINEAGE_BOSS_ARPIER_TIME,0,false));
    }


    // 활성화된 보탐 조회
    public static List<String> activatedBoss(){

        List<String> bossList = new ArrayList<String>();

        int index = 0;
        boolean isExist = false;
        for (BossInfo boss: bossInfo) {

            if(boss.isActivated()){
                bossList.add(boss.getBossName() + " : " + (boss.getBossRestTime() / 60) + " Min");
                isExist = true;
            }
            index ++;
        }

        if(!isExist){
            bossList.add("등록된 정보가 없습니다.");
        }
        return bossList;
    }

    // 보탐 활성화
    public static Timer bossTimer(int boss, Message message, int delayMin){
        final Message response = message;

//        if(DEV_MODE){
//            whoInterval = 250;
//            delayMin = -1;
//        }

        final int bossID = boss;

        final String bossName = bossInfo.get(bossID).getBossName();
        final int bossInterval = bossInfo.get(bossID).getBossInterval() + (delayMin * 60);
        bossInfo.get(bossID).setBossTimer(new Timer());
        bossInfo.get(bossID).setActivated(true);
        bossInfo.get(bossID).setBossRestTime(bossInterval);

        if(DEV_MODE){
            System.out.println("Debug > " + bossName + " / ID " + bossID + " / TimeInterval " + bossInterval);
        }

        TimerTask bossTask = new TimerTask() {
            @Override
            public void run() {
                bossInfo.get(bossID).increaseSec();
                int restTime = bossInfo.get(bossID).getBossRestTime();
                if(DEV_MODE){
                    System.out.println("Debug > " + bossName + " : " + restTime + "초 전");

                    if (restTime == typeController.TIME_3_MIN) {
                        System.out.println("> [알림] " + bossName + " 3분 전");
                    } else if (restTime == typeController.TIME_1_MIN) {
                        System.out.println("> [알림] " + bossName + " 1분 전");
                    } else if (restTime <= 1) {
                        bossInfo.get(bossID).getBossTimer().cancel();
                        bossInfo.get(bossID).setActivated(false);
                    }

                }else {
                    System.out.println("Debug > " + bossName + " : " + restTime + "초 전");

                    if (restTime == typeController.TIME_3_MIN) {
                        response.reply("> [알림] " + bossName + " 3분 전");
                    } else if (restTime == typeController.TIME_1_MIN) {
                        response.reply("> [알림] " + bossName + " 1분 전");
                    } else if (restTime <= 1) {
                        bossInfo.get(bossID).getBossTimer().cancel();
                        bossInfo.get(bossID).setActivated(false);
                    }
                }
            }
        };
        bossInfo.get(bossID).getBossTimer().schedule(bossTask, 0, 1000);

        return bossInfo.get(bossID).getBossTimer();
    }
}
