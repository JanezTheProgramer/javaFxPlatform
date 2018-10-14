package sample;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryClass {
    public static Boolean loginQuery(String gmail, String pass){
        String sql = new StringBuffer()
                .append("SELECT id FROM users")
                .append("WHERE gmail = '")
                .append(gmail)
                .append("' and pass = '")
                .append(pass)
                .append("' ")
                .toString();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement stmt  = conn.createStatement();
             ResultSet result    = stmt.executeQuery(sql)){
                if(result.next()) {
                    conn.close();
                    return true;
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
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
            for(String sql: tables) stmt.execute(sql);
        } catch (SQLException e) {
            //error on base connection
        }
    }

    public static Boolean createAcc(String gmail, String pass) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        String sql;
        sql = new StringBuffer()
                .append("SELECT id FROM users")
                .append("WHERE gmail = '")
                .append(gmail)
                .append("' ")
                .toString();
        try (Statement stmt  = conn.createStatement();
            ResultSet result    = stmt.executeQuery(sql)){
                if(result.next()) {
                    //custom error acc already exsits!!!!!
                    return false;
                }
                //exit function above if condition true!!!!
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("acc does not exsist yet!!!!");
        DateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateToday = new Date();
        sql = "INSERT INTO users (gmail, pass) VALUES ('"+gmail+"', '"+Main.hashPassword(pass)+"', '"+ formatDate.format(dateToday)+"')";
        try(Statement stmt = conn.createStatement()) {
            try{
                stmt.execute(sql);
                return true;
            }
            catch(Exception e){
                return false;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            conn.close();
        }
    }
}
