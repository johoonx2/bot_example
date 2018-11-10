package com.discord.bot;

// for Discord
import com.discord.bot.util.typeController;
import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.VoiceChannel;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageReceiver;
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
import javax.security.auth.login.LoginException;
import javax.xml.ws.Response;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// for Time
import java.text.SimpleDateFormat;

import static com.discord.bot.util.cmdController.*;
import static com.discord.bot.util.typeController.*;

public class mainController {

    static public void main(String args[]) throws ParseException{
        initValues();
        lineageDispatcher(args[0]);
//        localTest();
//        timeTest();
    }

    public static void timeTest() throws ParseException{
//        SimpleDateFormat f = new SimpleDateFormat("HH:mm", Locale.KOREA);
//        Date setDate = f.parse("19:00");
//        Date curDate = new Date();
//        curDate = f.parse(f.format(curDate));
//
//
//        System.out.println(setDate.getTime() / 1000);
//        System.out.println(curDate.getTime() / 1000);
//        System.out.println(((setDate.getTime() / 1000) - (curDate.getTime() / 1000)));
    }

    public static void localTest() throws ParseException{

        List<String> bossList = activatedBoss();
        for (String boss : bossList) {
            System.out.println("1st : " + boss);
        }

        bossTimer(typeController.LINEAGE_BOSS_NORTH_DRAKE_ID, null,"17:00", true);

        List<String> bossList2 = activatedBoss();
        for (String boss : bossList2) {
            System.out.println("2nd : " + boss);
        }

        bossTimer(typeController.LINEAGE_BOSS_MIDDLE_DRAKE_ID, null,"", false);

        List<String> bossList3 = activatedBoss();
        for (String boss : bossList3) {
            System.out.println("3rd : " + boss);
        }
    }



    // Lineage Manager
    public static void lineageDispatcher(String token){
        DiscordAPI api = Javacord.getApi(token, true);
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
//                                >[general (id: 504566390624092166), 사다리추첨방 (id: 508442656737853443), 보탐-인원확인방 (id: 508136002762833921), 하이혈맹-운영비 (id: 508136044290506753), 자유채팅방 (id: 453165102141669389), 보탐-제작재료 (id: 508136025684443136), 보탐시간체크 (id: 508139161610485764), 보탐-다야정산방 (id: 508136014645166092), 척살인원명단 (id: 508136034962505749), 하이-공지사항 (id: 508139364023271435)]
//                            }

//                            # getVoiceChannels
//                            if(message.getContent().equals("getVoiceChannels")){
//                                message.reply(">" + api.getVoiceChannels().toString());
//                                >[일반 (id: 504566390624092170), 하이혈맹디코방 (id: 453165102141669391), 하이간부회의방 (id: 508139474279071744), 잠수방 (id: 508338215674183681)]
//                            }


//                            # getVoiceChannelById
                            if(message.getContent().equals("getVoiceChannelById_getConnectedUsers")){
                                message.reply(">" + api.getVoiceChannelById("453165102141669391").getConnectedUsers().toString());
                            }
//
//                            if(message.getContent().equals("getVoiceChannelById_getConnectedUsers")){
//                                message.reply(">" + api.getVoiceChannelById("453165102141669391").getConnectedUsers().toString());
//                            }

                            VoiceChannel vChannel = api.getVoiceChannelById("453165102141669391");
//                            AudioManager audioManager =


                            String resp = "";
                            String cmd = message.getContent();
                            System.out.println("> Command : " + cmd);


//                            cmd = cmd.replace(" ", "");
//                            String[] command = cmd.split("#");
                            String[] command = cmd.split(" ");


                            if (command[0].equals(typeController.LINEAGE_HELPER)) {
                                System.out.println("> LINEAGE HELPER");
                                resp = LINEAGE_HELPER_BOSS + "\n" + LINEAGE_HELPER_BOSS_SUPPORT + "\n" + LINEAGE_HELPER_SADARI;
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

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_PHOENIX)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_PHOENIX, typeController.LINEAGE_BOSS_PHOENIX_ID, typeController.LINEAGE_BOSS_PHOENIX_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_IFRIT)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_IFRIT, typeController.LINEAGE_BOSS_IFRIT_ID, typeController.LINEAGE_BOSS_IFRIT_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_MAYO)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_MAYO, typeController.LINEAGE_BOSS_MAYO_ID, typeController.LINEAGE_BOSS_MAYO_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_GIANTDRAKE)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_GIANTDRAKE, typeController.LINEAGE_BOSS_GIANTDRAKE_ID, typeController.LINEAGE_BOSS_GIANTDRAKE_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_ANCIENTGIANT)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_ANCIENTGIANT, typeController.LINEAGE_BOSS_ANCIENTGIANT_ID, typeController.LINEAGE_BOSS_ANCIENTGIANT_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_GIANTWORM)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_GIANTWORM, typeController.LINEAGE_BOSS_GIANTWORM_ID, typeController.LINEAGE_BOSS_GIANTWORM_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_BANDIT)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_BANDIT, typeController.LINEAGE_BOSS_BANDIT_ID, typeController.LINEAGE_BOSS_BANDIT_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_GREENCHASKI)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_GREENCHASKI, typeController.LINEAGE_BOSS_GREENCHASKI_ID, typeController.LINEAGE_BOSS_GREENCHASKI_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_REDCHASKI)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_REDCHASKI, typeController.LINEAGE_BOSS_REDCHASKI_ID, typeController.LINEAGE_BOSS_REDCHASKI_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_RICHARD)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_RICHARD, typeController.LINEAGE_BOSS_RICHARD_ID, typeController.LINEAGE_BOSS_RICHARD_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_DOPPELGANGER)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_DOPPELGANGER, typeController.LINEAGE_BOSS_DOPPELGANGER_ID, typeController.LINEAGE_BOSS_DOPPELGANGER_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_SPIRID)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_SPIRID, typeController.LINEAGE_BOSS_SPIRID_ID, typeController.LINEAGE_BOSS_SPIRID_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_GASTROD)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_GASTROD, typeController.LINEAGE_BOSS_GASTROD_ID, typeController.LINEAGE_BOSS_GASTROD_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_FAUST)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_FAUST, typeController.LINEAGE_BOSS_FAUST_ID, typeController.LINEAGE_BOSS_FAUST_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_DEATHKNIGHT)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_DEATHKNIGHT, typeController.LINEAGE_BOSS_DEATHKNIGHT_ID, typeController.LINEAGE_BOSS_DEATHKNIGHT_TIME, message);

                            }else if (command[0].equals(typeController.LINEAGE_BOSS_KURTZ)) {
                                bossDispatcher(command, typeController.LINEAGE_BOSS_KURTZ, typeController.LINEAGE_BOSS_KURTZ_ID, typeController.LINEAGE_BOSS_KURTZ_TIME, message);

//                            }else if (command[0].equals(typeController.LINEAGE_BOSS_NECROMANCER)) {
//                                bossDispatcher(command, typeController.LINEAGE_BOSS_NECROMANCER, typeController.LINEAGE_BOSS_NECROMANCER_ID, typeController.LINEAGE_BOSS_NECROMANCER_TIME, message);
//
//                            }else if (command[0].equals(typeController.LINEAGE_BOSS_BAPHOMET)) {
//                                bossDispatcher(command, typeController., typeController., typeController., message);

                            }

                        } catch (Exception e) {
                            System.out.println("> EXCEPTION : " +  e);
                            message.reply("> 에러 발생! " +  e);
                        }

                    }

                });
            }

            public void bossDispatcher(String[] command, String bossName, int bossID, int bossTime, Message message) throws ParseException {

                String delay = "";
                if(command.length > 2 && command[2] != null){
                   delay = command[2];
                }


                if (command[1].equals("컷")) {
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.MINUTE, cal.get(Calendar.MINUTE) + bossTime);
                    message.reply("> " + bossName + " 타이머 설정 완료 (" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ")");
                    bossTimer(bossID, message, delay, false);

                }else if(command[1].equals("셋")){
                    message.reply("> " + bossName + " 타이머 설정 완료 (" +  command[2] + ")");
                    bossTimer(bossID, message, command[2], true);

                }else if(command[1].equals("삭")){
                    bossInfo.get(bossID).getBossTimer().cancel();
                    bossInfo.get(bossID).setActivated(false);
                    message.reply("> " + bossName + " 타이머 제거 완료");

                }else if(command[1].equals("멍")){
                    int restTime = bossInfo.get(bossID).getBossRestTime();
                    restTime += bossInfo.get(bossID).getBossInterval(); // 젠시간 합산
                    bossInfo.get(bossID).setBossRestTime(restTime);
                    message.reply("> " + bossName + " 멍타임 적용 완료");
                }
            }
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
