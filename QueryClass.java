package sample;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryClass {
    public static Boolean loginQuery(String gmail, String pass) throws NoSuchAlgorithmException {
        String sql = new StringBuffer()
                .append("SELECT id FROM users")
                .append(" WHERE gmail = '")
                .append(gmail)
                .append("' and pass = '")
                .append(Main.hashPassword(pass))
                .append("' ")
                .toString();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement stmt  = conn.createStatement();
             ResultSet result    = stmt.executeQuery(sql)){
                if(result.next()) {
                    return true;
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void createDB() throws ClassNotFoundException, IOException {
        //get db file
        try{
            File database = new File("database.db");
            database.createNewFile();
        }catch (Exception err){
            Alert.CreateAlert("Failed to create database!");
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
            for(String sql: tables) stmt.execute(sql);
        } catch (SQLException e) {
            //error on base connection
        }
    }

    public static int createAcc(String gmail, String pass) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        String sql;
        sql = new StringBuffer()
                .append("SELECT id FROM users")
                .append(" WHERE gmail = '")
                .append(gmail)
                .append("' ")
                .toString();
        try (Statement stmt  = conn.createStatement();
            ResultSet result    = stmt.executeQuery(sql)){
                if(result.next()) {
                    //custom error acc already exsits!!!!!
                    return 1;
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(!gmail.matches("^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$")) {
            return 2;
        }
        DateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date dateToday = new Date();
        sql = "INSERT INTO users (gmail, pass, date) VALUES ('"+gmail+"', '"+Main.hashPassword(pass)+"', '"+ formatDate.format(dateToday)+"')";
        try(Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            return 0;
        } catch (SQLException e) {
            return -1;
        } finally {
            conn.close();
        }
    }
}
