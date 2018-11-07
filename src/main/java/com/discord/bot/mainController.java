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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// for Time
import java.text.SimpleDateFormat;

import static com.discord.bot.util.cmdController.*;

public class mainController {

    final static boolean DEV_MODE = false;

    static public void main(String args[]){
        initValues();
        lineageDispatcher();
    }



    public static void localTest(){
//        Calendar cal = Calendar.getInstance();
//        System.out.println("h : " + cal.get(Calendar.HOUR));
//        System.out.println("m : " + cal.get(Calendar.MINUTE));
//
//        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 50);
//
//        System.out.println("시 : " + cal.get(Calendar.HOUR) + " 분 :: " + cal.get(Calendar.MINUTE));
    }



    // Lineage Manager
    public static void lineageDispatcher(){
        DiscordAPI api = Javacord.getApi("NTA0MjI4ODM4NjU0NjA3Mzcx.DrG2Aw.7FUV_2YF4JJo8imPTLAkO9azhUw", true);
        api.connect(new FutureCallback<DiscordAPI>() {
            public void onSuccess(final DiscordAPI api) {
                api.registerListener(new MessageCreateListener() {
                    public void onMessageCreate(DiscordAPI api, Message message) {
                        try {
                            if(message.getContent().equals("ping")){
                                message.reply("pong!");
                            }

                            String resp = "";
                            String cmd = message.getContent();
                            System.out.println("> Command : " + cmd);


                            cmd = cmd.replace(" ", "");
                            String[] command = cmd.split("#");

                            if (command[0].equals(typeController.LINEAGE_HELPER)) {
                                System.out.println("> LINEAGE HELPER");
                                if (command.length == 1) {
                                    resp = "명령어 목록 : 도움말, 사다리, 서드 \n"
                                            + "명령어 도움말 ex) /도움말#사다리";
                                } else if (command[1].equals("도움말")) {
                                    resp = "명령어 구조 : /도움말#대상명령어 \n" +
                                            "ex) /도움말#사다리";
                                } else if (command[1].equals("사다리")) {
                                    resp = "명령어 구조 : /사다리#당첨자수#대상1,대상2,대상3,...\n" +
                                            "ex) 3명 대상으로 사다리 : /사다리#3#대상1,대상2,대상3,...";
                                } else if (command[1].equals("보스")) {
                                    resp = "명령어 구조 : /보스#명령어\n" +
                                            "명령어 예제\n" +
                                            "등록 : /보스#컷 - 예) /서드#컷\n" +
                                            "등록(조정) : /보스#컷#조정시간 - 예) /서드#컷#-5 (등록 기준 5분전 보스컷)\n" +
                                            "등록(지정시간) : /보스#셋#시간 - 예) /서드#컷#20:20 (20:20분 기준 보스컷 - 미지원)\n" +
                                            "삭제 : /보스#삭 - 예) /서드#삭\n" +
                                            "지원보스 : 동드, 서드, 북드, 중드, 자크, 카스파, 아르";
//                                            "시간 설정 : /서드#셋#12:40";
                                }

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
                                // 북드
                                Calendar cal = Calendar.getInstance();
                                cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + typeController.LINEAGE_BOSS_NORTH_DRAKE_TIME);

                                if (command[1].equals("컷")) {
                                    message.reply("> 드레이크(북) 타이머 설정 완료 (" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ")");
                                    timerManager[typeController.LINEAGE_BOSS_NORTH_DRAKE_ID]
                                            = bossTimer(typeController.LINEAGE_BOSS_NORTH_DRAKE, message, typeController.LINEAGE_BOSS_NORTH_DRAKE_ID, typeController.LINEAGE_BOSS_NORTH_DRAKE_TIME, Integer.valueOf(command[2]));

                                }else if(command[1].equals("삭")){
                                    timerManager[typeController.LINEAGE_BOSS_NORTH_DRAKE_ID].cancel();
                                    message.reply("> 드레이크(북) 타이머 제거 완료");
                                }
                            } else if (command[0].equals(typeController.LINEAGE_BOSS_CASPA)) {
                                // 북드
                                Calendar cal = Calendar.getInstance();
                                cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + typeController.LINEAGE_BOSS_CASPA_TIME);

                                if (command[1].equals("컷")) {
                                    message.reply("> 카스파 타이머 설정 완료 (" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ")");
                                    timerManager[typeController.LINEAGE_BOSS_CASPA_ID]
                                            = bossTimer(typeController.LINEAGE_BOSS_CASPA, message, typeController.LINEAGE_BOSS_CASPA_ID, typeController.LINEAGE_BOSS_CASPA_TIME, Integer.valueOf(command[2]));

                                }else if(command[1].equals("삭")){
                                    timerManager[typeController.LINEAGE_BOSS_CASPA_ID].cancel();
                                    message.reply("> 카스파 타이머 제거 완료");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("> EXCEPTION : " +  e);
                            message.reply("> 에러 발생! " +  e);
                        }
                    }

                });
            }


            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }





    // Weight Manager
    public void test(){
        String input = "/wa hjo 85.5";   // Weight Add [NAME] [WEIGHT], Response [BMI]
//        input = "/ma swkim 177";    // Member Add [NAME] [HEIGHT], Response [Result]
//        input = "/ws hjo";        // Weight Search [NAME]
//        input = "/ms hjo";          // Member Search [NAME]
//        input = "/wd 1";            // Weight Delete [CODE]
//        input = "/md hjo";          // Member Delete [NAME]
//        input = "/wl";              // Weight List - All
//        input = "/ml";              // Member List - All



//        String sql;
//        sql = "SELECT * FROM weight WHERE name = '" + "hjo" + "';";
//        sql = "INSERT INTO weight (`date`, `name`, `weight`, `bmi`) VALUES (now(), 'hjo', '84.6', '0');";
//        sql = "INSERT INTO member (`name`, `height`) VALUES ('" + "hjo" + ", '" + "170" + "');";
//        System.out.println(dbConn(sql));
    }

    public String cmdDispatcher(String input){

        String query;
        String cmd[] = input.split(" ");
//        System.out.println(cmd[0]);

        if(cmd[0].equals(typeController.WEIGHT_ADD)){

            query = "SELECT * FROM member WHERE name = " + cmd[1];
            String memberInfos[] = selectDB(query);
            String memberInfo[] = memberInfos[0].split(" | ");

            query = "INSERT INTO weight (`date`, `name`, `weight`, `bmi`) VALUES (now(), '" + cmd[1] + "', '"+ cmd[2] + "', '0');";

        }

        return "";
    }



    public String[] selectDB(String sql){
        String sqlRecipeProcess = "";
        Connection connection = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://210.89.181.173:3306/health_manage?characterEncoding=UTF-8&serverTimezone=UTC" , "root", "Tmdals1318!@#");
            st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            rs.getFetchSize();
            while (rs.next()) {
//                sqlRecipeProcess += rs.getString("name");
//                System.out.println(rs.getString("name"));
            }

            rs.close();
            st.close();
            connection.close();
        } catch (SQLException se1) {
            se1.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return null;
    }


    static public String insertDB(String sql){
        String sqlRecipeProcess = "";
        Connection connection = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://210.89.181.173:3306/health_manage?characterEncoding=UTF-8&serverTimezone=UTC" , "root", "Tmdals1318!@#");
            st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            rs.getFetchSize();
            while (rs.next()) {
//                sqlRecipeProcess += rs.getString("name");
//                System.out.println(rs.getString("name"));
            }

            rs.close();
            st.close();
            connection.close();
        } catch (SQLException se1) {
            se1.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return sqlRecipeProcess;
    }

    static public String helper(){
        return "//i [name] [kg]" +
                "//r [item_no]" +
                "//s [name]";
    }


//
//
//    static public void dbConn(){
//
//        Connection connection = null;
//        Statement st = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://210.89.181.173:3306/health_manage?characterEncoding=UTF-8&serverTimezone=UTC" , "root", "Tmdals1318!@#");
//            st = connection.createStatement();
//
//            String sql;
//            sql = "select * FROM weight;";
//
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
////                String sqlRecipeProcess = rs.getString("name");
//                System.out.println(rs.getString("name"));
//            }
//
//            rs.close();
//            st.close();
//            connection.close();
//        } catch (SQLException se1) {
//            se1.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (st != null)
//                    st.close();
//            } catch (SQLException se2) {
//            }
//            try {
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//    }



//
//    static public void dbConn(){
//
//        Connection connection = null;
//        Statement st = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://210.89.181.173:3306/health_manage?characterEncoding=UTF-8&serverTimezone=UTC" , "root", "Tmdals1318!@#");
//            st = connection.createStatement();
//
//            String sql;
//            sql = "select * FROM weight;";
//
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
////                String sqlRecipeProcess = rs.getString("name");
//                System.out.println(rs.getString("name"));
//            }
//
//            rs.close();
//            st.close();
//            connection.close();
//        } catch (SQLException se1) {
//            se1.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (st != null)
//                    st.close();
//            } catch (SQLException se2) {
//            }
//            try {
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//    }

//
//    static public void discordConn(){
//        DiscordAPI api = Javacord.getApi("NTA0MjI4ODM4NjU0NjA3Mzcx.DrG2Aw.7FUV_2YF4JJo8imPTLAkO9azhUw", true);
//        api.connect(new FutureCallback<DiscordAPI>() {
//            public void onSuccess(final DiscordAPI api) {
//                api.registerListener(new MessageCreateListener() {
//                    public void onMessageCreate(DiscordAPI api, Message message) {
//
////                        if(message.getContent().equals("ping")){
////                            message.reply("pong!");
////
////                        }
//                    }
//                });
//            }
//
//            public void onFailure(Throwable t) {
//                t.printStackTrace();
//            }
//        });
//
//    }
}
