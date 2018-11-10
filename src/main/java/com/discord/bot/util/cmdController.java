package com.discord.bot.util;

import com.discord.bot.model.BossInfo;
import de.btobastian.javacord.entities.message.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_PHOENIX, typeController.LINEAGE_BOSS_PHOENIX_ID,typeController.LINEAGE_BOSS_PHOENIX_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_IFRIT, typeController.LINEAGE_BOSS_IFRIT_ID,typeController.LINEAGE_BOSS_IFRIT_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_MAYO, typeController.LINEAGE_BOSS_MAYO_ID,typeController.LINEAGE_BOSS_MAYO_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_GIANTDRAKE, typeController.LINEAGE_BOSS_GIANTDRAKE_ID,typeController.LINEAGE_BOSS_GIANTDRAKE_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_ANCIENTGIANT, typeController.LINEAGE_BOSS_ANCIENTGIANT_ID,typeController.LINEAGE_BOSS_ANCIENTGIANT_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_GIANTWORM, typeController.LINEAGE_BOSS_GIANTWORM_ID,typeController.LINEAGE_BOSS_GIANTWORM_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_BANDIT, typeController.LINEAGE_BOSS_BANDIT_ID,typeController.LINEAGE_BOSS_BANDIT_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_GREENCHASKI, typeController.LINEAGE_BOSS_GREENCHASKI_ID,typeController.LINEAGE_BOSS_GREENCHASKI_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_REDCHASKI, typeController.LINEAGE_BOSS_REDCHASKI_ID,typeController.LINEAGE_BOSS_REDCHASKI_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_RICHARD, typeController.LINEAGE_BOSS_RICHARD_ID,typeController.LINEAGE_BOSS_RICHARD_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_DOPPELGANGER, typeController.LINEAGE_BOSS_DOPPELGANGER_ID,typeController.LINEAGE_BOSS_DOPPELGANGER_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_SPIRID, typeController.LINEAGE_BOSS_SPIRID_ID,typeController.LINEAGE_BOSS_SPIRID_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_GASTROD, typeController.LINEAGE_BOSS_GASTROD_ID,typeController.LINEAGE_BOSS_GASTROD_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_FAUST, typeController.LINEAGE_BOSS_FAUST_ID,typeController.LINEAGE_BOSS_FAUST_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_DEATHKNIGHT, typeController.LINEAGE_BOSS_DEATHKNIGHT_ID,typeController.LINEAGE_BOSS_DEATHKNIGHT_TIME,0,false));
        bossInfo.add(new BossInfo(typeController.LINEAGE_BOSS_KURTZ, typeController.LINEAGE_BOSS_KURTZ_ID,typeController.LINEAGE_BOSS_KURTZ_TIME,0,false));
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
    public static Timer bossTimer(int boss, Message message, String delayMin, boolean isSet) throws ParseException{
        final Message response = message;

//        if(DEV_MODE){
//            whoInterval = 250;
//            delayMin = -1;
//        }

        final int bossID = boss;
        final String bossName = bossInfo.get(bossID).getBossName();

        int interval = bossInfo.get(bossID).getBossInterval();

        if(delayMin.length() > 0){
            if(isSet){
                // 직접 시간을 지정한 경우
                SimpleDateFormat f = new SimpleDateFormat("HH:mm", Locale.KOREA);
                Date setDate = f.parse(delayMin);
                Date curDate = new Date();
                curDate = f.parse(f.format(curDate));
                interval += (int) (setDate.getTime() - curDate.getTime()) / 1000;

            }else{
                // 딜레이만 적용할 경우
                interval += (Integer.parseInt(delayMin) * 60);
            }
        }

        final int bossInterval = interval;

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

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, restTime);

                if(DEV_MODE){
                    if(restTime % 10 == 0) {
                        System.out.println("Debug > " + bossName + ":" + restTime + "초 전 # 기준시각 " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
                    }

                    if (restTime == typeController.TIME_3_MIN) {
                        System.out.println("> [알림] " + bossName + " 3분 전 # 기준시각 " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
                    } else if (restTime == typeController.TIME_1_MIN) {
                        System.out.println("> [알림] " + bossName + " 1분 전 # 기준시각 " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
                    } else if (restTime <= 1) {
                        bossInfo.get(bossID).getBossTimer().cancel();
                        bossInfo.get(bossID).setActivated(false);
                    }

                }else {
                    if(restTime % 10 == 0) {
                        System.out.println("Debug > " + bossName + ":" + restTime + "초 전 # 기준시각 " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
                    }
                    if (restTime == typeController.TIME_3_MIN) {
                        response.reply("> [알림] " + bossName + " 3분 전 # 기준시각 " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
                    } else if (restTime == typeController.TIME_1_MIN) {
                        response.reply("> [알림] " + bossName + " 1분 전 # 기준시각 " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
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
