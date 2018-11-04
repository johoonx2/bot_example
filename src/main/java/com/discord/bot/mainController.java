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
import java.util.ArrayList;
import java.util.logging.Handler;


// for Bot
import com.discord.bot.util.*;


public class mainController {

    static public void main(String args[]) throws Exception{
        lineageDispatcher();
//        localTest();
    }


    public static void localTest() throws Exception{
//        String cmd = "/드레#컷";
//        cmd = cmd.replace(" ", "");
//        String[] command = cmd.split("#");
//
//        if(command[0].equals(typeController.LINEAGE_BOSS_DRAKE)){
//            if(command[1].equals("컷")){
//
//                System.out.println("TimerTest");
//                int delayTimeSec = 10;
//                delayTimeSec*=1000;
//                Robot robot = new Robot();
//                robot.delay(delayTimeSec);
//                System.out.println("TimerTest-End");
//
//            }
//        }

    }



    // Lineage Manager
    public static void lineageDispatcher() throws Exception{
        DiscordAPI api = Javacord.getApi("NTA0MjI4ODM4NjU0NjA3Mzcx.DrG2Aw.7FUV_2YF4JJo8imPTLAkO9azhUw", true);
        api.connect(new FutureCallback<DiscordAPI>() {
            public void onSuccess(final DiscordAPI api) {
                api.registerListener(new MessageCreateListener() {
                    public void onMessageCreate(DiscordAPI api, Message message) {
                        try {
//                        Default Type
//                        if(message.getContent().equals("ping")){
//                            message.reply("pong!");
//                        }


//                        String cmd = "/사다리#2#캐스팅,대선한잔,다마,해롱,노빠꾸";

                            String resp = "";
                            String cmd = message.getContent();
                            System.out.println("> Command : " + cmd);


                            cmd = cmd.replace(" ", "");
                            String[] command = cmd.split("#");

                            if (command[0].equals(typeController.LINEAGE_HELPER)) {
                                System.out.println("> LINEAGE HELPER");
                                if (command.length == 1) {
                                    resp = "명령어 목록 : 도움말, 사다리, 드레" + "\n" + "명령어 도움말 ex) /도움말#사다리";
                                } else if (command[1].equals("도움말")) {
                                    resp = "명령어 구조 : /도움말#대상명령어" + "\n" +
                                            "ex) /도움말#사다리";
                                } else if (command[1].equals("사다리")) {
                                    resp = "명령어 구조 : /사다리#당첨자수#대상1,대상2,대상3,..." + "\n" +
                                            "ex) 3명 대상으로 사다리 : /사다리#3#대상1,대상2,대상3,...";
                                } else if (command[1].equals("드레")) {
                                    resp = "명령어 구조 : /드레#명령어" + "\n" +
                                            "명령어 예제" + "\n" +
                                            "등록 : /드레#컷" + "\n" +
                                            "삭제 : /드레#삭" + "\n" +
                                            "시간 설정 : /드레#셋#12:40";
                                }
                            } else if (command[0].equals(typeController.LINEAGE_SADARI_DEFAULT)) {
                                System.out.println("> SADARI");
//                                int delayTimeSec = 1;
//                                delayTimeSec *= 1000;
//                                Robot robot = new Robot();
//
//                                robot.delay(delayTimeSec);
//                                message.reply("3");
//
//                                robot.delay(delayTimeSec);
//                                message.reply("2");
//
//                                robot.delay(delayTimeSec);
//                                message.reply("1");
//
//                                robot.delay(delayTimeSec);
//                                message.reply("가즈아~");
//
//                                robot.delay(delayTimeSec);
                                message.reply("당첨자 : " + cmdController.sadari(1, command[1], command[2]).toString());

                            } else if (command[0].equals(typeController.LINEAGE_BOSS_DRAKE)) {
                                System.out.println("> DRAKE CUT");
                                if (command[1].equals("컷")) {
//                                    int delayTimeSec = 10;
//                                    delayTimeSec *= 1000;
//                                    Robot robot = new Robot();
//                                    robot.delay(delayTimeSec);
//                                    message.reply("드레컷 후 10초");
//
//                                    delayTimeSec *= 6;
//                                    robot.delay(delayTimeSec);
//                                    message.reply("드레컷 후 1분");
//
//                                    delayTimeSec *= 60;
//                                    robot.delay(delayTimeSec);
//                                    message.reply("드레컷 후 60분");
                                }
                            }



                        } catch (Exception e) {
                            System.out.println("> EXCEPTION : " +  e);
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
