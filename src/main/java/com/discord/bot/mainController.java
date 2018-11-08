package com.discord.bot;

// for Discord
import com.discord.bot.util.typeController;
import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

// for MySQL
import java.awt.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;


// for Bot
import com.discord.bot.util.*;


// for Timmer
import javax.xml.ws.Response;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// for Time
import java.text.SimpleDateFormat;

import static com.discord.bot.util.cmdController.*;

public class mainController {

    static public void main(String args[]){
        initValues();
        lineageDispatcher();
//        localTest();
    }



    public static void localTest(){
//        Calendar cal = Calendar.getInstance();
//        System.out.println("h : " + cal.get(Calendar.HOUR));
//        System.out.println("m : " + cal.get(Calendar.MINUTE));
//
//        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 50);
//
//        System.out.println("시 : " + cal.get(Calendar.HOUR) + " 분 :: " + cal.get(Calendar.MINUTE));


        List<String> bossList = activatedBoss();
        for (String boss : bossList) {
            System.out.println("1st : " + boss);
        }

        bossTimer(typeController.LINEAGE_BOSS_NORTH_DRAKE_ID, null,0);

        List<String> bossList2 = activatedBoss();
        for (String boss : bossList2) {
            System.out.println("2nd : " + boss);
        }

        bossTimer(typeController.LINEAGE_BOSS_MIDDLE_DRAKE_ID, null,0);

        List<String> bossList3 = activatedBoss();
        for (String boss : bossList3) {
            System.out.println("3rd : " + boss);
        }
    }



    // Lineage Manager
    public static void lineageDispatcher(){
        DiscordAPI api = Javacord.getApi("NTA0MjI4ODM4NjU0NjA3Mzcx.DrG2Aw.7FUV_2YF4JJo8imPTLAkO9azhUw", true);
        api.connect(new FutureCallback<DiscordAPI>() {
            public void onSuccess(final DiscordAPI api) {
                api.registerListener(new MessageCreateListener() {
                    public void onMessageCreate(DiscordAPI api, Message message) {
                        try {


                            /* 예제 모음 */
//                            # Default
//                            if(message.getContent().equals("ping")){
//                                message.reply("pong!");
//                            }

//                            # getChannel
//                            if(message.getContent().equals("getChannel")){
//                                message.reply(">" + api.getChannels().toString());
//                                Response :
//                                >[general (id: 504566390624092166), 사다리추첨방 (id: 508442656737853443), 보탐-인원확인방 (id: 508136002762833921), 하이혈맹-운영비 (id: 508136044290506753), 자유채팅방 (id: 453165102141669389), 보탐-제작재료 (id: 508136025684443136), 보탐시간체크 (id: 508139161610485764), 보탐-다야정산방 (id: 508136014645166092), 척살인원명단 (id: 508136034962505749), 하이-공지사항 (id: 508139364023271435)]
//                            }

//                            # getVoiceChannels
                            if(message.getContent().equals("getVoiceChannels")){
                                message.reply(">" + api.getVoiceChannels().toString());
                            }


//                            # getVoiceChannelById
//                            if(message.getContent().equals("getVoiceChannelById_getConnectedUsers")){
//                                message.reply(">" + api.getVoiceChannelById(api.getVoiceChannelById("하이혈맹디코방").getConnectedUsers().toString()));
//                            }
//
//                            if(message.getContent().equals("getVoiceChannelById_getConnectedUsers")){
//                                message.reply(">" + api.getVoiceChannelById(api.getVoiceChannelById("하이혈맹디코방").getConnectedUsers().toString()));
//                            }




                            String resp = "";
                            String cmd = message.getContent();
                            System.out.println("> Command : " + cmd);


//                            cmd = cmd.replace(" ", "");
//                            String[] command = cmd.split("#");
                            String[] command = cmd.split(" ");


                            if (command[0].equals(typeController.LINEAGE_HELPER)) {
                                System.out.println("> LINEAGE HELPER");
//                                if (command.length == 1) {
//                                    resp = "명령어 목록 : 도움말, 사다리, 서드 \n"
//                                            + "명령어 도움말 ex) /도움말#사다리";
//                                } else if (command[1].equals("도움말")) {
//                                    resp = "명령어 구조 : /도움말#대상명령어 \n" +
//                                            "ex) /도움말#사다리";
//                                } else if (command[1].equals("사다리")) {
//                                    resp = "명령어 구조 : /사다리#당첨자수#대상1,대상2,대상3,...\n" +
//                                            "ex) 3명 대상으로 사다리 : /사다리#3#대상1,대상2,대상3,...";
//                                } else if (command[1].equals("보스")) {
//                                    resp = "명령어 구조 : /보스#명령어\n" +
//                                            "명령어 예제\n" +
//                                            "등록 : /보스#컷 - 예) /서드#컷\n" +
//                                            "등록(조정) : /보스#컷#조정시간 - 예) /서드#컷#-5 (등록 기준 5분전 보스컷)\n" +
//                                            "등록(지정시간) : /보스#셋#시간 - 예) /서드#컷#20:20 (20:20분 기준 보스컷 - 미지원)\n" +
//                                            "삭제 : /보스#삭 - 예) /서드#삭\n" +
//                                            "지원보스 : 동드, 서드, 북드, 중드, 자크, 카스파, 아르";
////                                            "시간 설정 : /서드#셋#12:40";
//                                }

                                message.reply(resp);

                            /* 사다리타기 */
                            } else if (command[0].equals(typeController.LINEAGE_SADARI_DEFAULT)) {

                                System.out.println("> 사다리");
                                message.reply("당첨자 : " + cmdController.sadari(1, command[1], command[2]).toString());


                            /* 활성화된 보스 리스트 전체 조회 */
                            } else if (command[0].equals(typeController.LINEAGE_BOSS_LIST)) {
                                List<String> bossList = activatedBoss();
                                String response = "";

                                for (String boss : bossList) {
                                    response += "> " + boss + "\n";
                                }

                                message.reply(response);

                            } else if (command[0].equals(typeController.LINEAGE_BOSS_NORTH_DRAKE)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_NORTH_DRAKE, typeController.LINEAGE_BOSS_NORTH_DRAKE_ID, typeController.LINEAGE_BOSS_CASPA_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_MIDDLE_DRAKE)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_MIDDLE_DRAKE, typeController.LINEAGE_BOSS_MIDDLE_DRAKE_ID, typeController.LINEAGE_BOSS_MIDDLE_DRAKE_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_EAST_DRAKE)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_EAST_DRAKE, typeController.LINEAGE_BOSS_EAST_DRAKE_ID, typeController.LINEAGE_BOSS_EAST_DRAKE_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_WEST_DRAKE)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_WEST_DRAKE, typeController.LINEAGE_BOSS_WEST_DRAKE_ID, typeController.LINEAGE_BOSS_WEST_DRAKE_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_CASPA)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_CASPA, typeController.LINEAGE_BOSS_CASPA_ID, typeController.LINEAGE_BOSS_CASPA_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_GIANTCROCODILE)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_GIANTCROCODILE, typeController.LINEAGE_BOSS_GIANTCROCODILE_ID, typeController.LINEAGE_BOSS_GIANTCROCODILE_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_ARPIER)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_ARPIER, typeController.LINEAGE_BOSS_ARPIER_ID, typeController.LINEAGE_BOSS_ARPIER_TIME, message);

                            }

                        } catch (Exception e) {
                            System.out.println("> EXCEPTION : " +  e);
                            message.reply("> 에러 발생! " +  e);
                        }

                    }

                });
            }

            public void bossDispatcher(String[] command, String bossName, int bossID, int bossTime, Message message){

                int delay = 0;
                if(command.length > 2 && command[2] != null){
                   delay = Integer.valueOf(command[2]);
                }


                Calendar cal = Calendar.getInstance();
//                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + bossTime);

                if (command[1].equals("컷")) {
                    message.reply("> " + bossName + " 타이머 설정 완료 (" + cal.get(Calendar.AM_PM) + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ")");
                    bossTimer(bossID, message, delay);

                }else if(command[1].equals("삭")){
                    bossInfo.get(bossID).getBossTimer().cancel();
                    bossInfo.get(bossID).setActivated(false);
                    message.reply("> " + bossName + " 타이머 제거 완료");
                }
            }
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
