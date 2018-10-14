package sample;

import java.io.File;
import java.sql.*;

public class QueryClass {
    public static Boolean tryLogin(String gmail, String pass){
        String sql = new StringBuffer()
                .append("SELECT id FROM users").append("WHERE gmail = '").append(gmail).append("' and pass = '").append(pass).append("' ")
                .toString();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement stmt  = conn.createStatement();
             ResultSet result    = stmt.executeQuery(sql)){
                if(!result.next()) return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static void createDB() throws ClassNotFoundException {
        //get db file
        try{
            File database = new File("database.db");
            database.createNewFile();
        }catch (Exception err){
            System.out.println("file creation error!");
            //custom error!!!
        }
        Class.forName("org.sqlite.JDBC");
        String[] tables = {""};
        tables[0] = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS `users`")
                .append("( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,")
                .append("`gmail` TEXT NOT NULL,")
                .append("`pass` TEXT NOT NULL,")
                .append("`date` BLOB NOT NULL )")
                .toString();
        //generate tables if they dont exsist
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement stmt = conn.createStatement()) {
            try{
                for(String sql: tables) stmt.execute(sql);
            }
            catch(Exception e){/*not valid create table*/}
        } catch (SQLException e) {
            //error on base connection
        }
    }

    public static void createAcc(String gmail, String pass) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        String sql;
        sql = new StringBuffer()
                .append("SELECT id FROM users")
                .append("WHERE gmail = '")
                .append(gmail)
                .append("' and pass = '")
                .append(pass)
                .append("' ")
                .toString();
        try (Statement stmt  = conn.createStatement();
            ResultSet result    = stmt.executeQuery(sql)){
                if(result.next()) {
                    //custom error acc already exsits!!!!!
                    return;
                }
                //exit function above if condition true!!!!
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sql = new StringBuilder()
                .append("fd")
                .append("fds")
                .append("dfs")
                .append("fds")
                .toString();

        try(Statement stmt = conn.createStatement()) {
            try{
                stmt.execute(sql);
            }
            catch(Exception e){/*not valid create table*/}
        } catch (SQLException e) {
            //error on base connection
        }

    }
}
