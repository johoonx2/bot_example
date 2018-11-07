package com.discord.bot.util;

import java.sql.*;

public class util {
    public float bmiCalc(float height, float weight){
        return (weight / (height*height));
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
