import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.sql.*;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by CZ on 2017/4/8.
 */
public class BorrowOrReturnBook {

    public static void main(String args[]) {
        BorrowOrReturnBook B1 =  new BorrowOrReturnBook();
        try {
            B1.returnBook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void borrowBook() throws Exception{

        Connection conn = null;
        String url =  "jdbc:mysql://localhost:3306/LibrarySystem?"
                + "user=root&password=19960415&useUnicode=true&characterEncoding=UTF8&useSSL=true";
        String sql;

        try {

            System.out.println("请输入你的借书证编号");
            Scanner reader = new Scanner(System.in);
            String cno = reader.nextLine();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            sql = "SELECT count(*) FROM card WHERE cno = '" +
                    cno +
                    "';";
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            if (rs.next()){
                count = rs.getInt(1);
            }
            if (count == 0) {
                System.out.println("借书证号不存在，请确认后重新输入");
                return;
            }
            System.out.println("请输入你要借阅的图书编号");
            String bno = reader.nextLine();

            sql = "SELECT count(*) FROM book WHERE bno = '" +
                    bno +
                    "';";
            rs = stmt.executeQuery(sql);
            if (rs.next()){
                count = rs.getInt(1);
            }
            if(count == 0){
                System.out.println("图书不存在，请确认后再进行查询");
                return;
            }

            sql = "SELECT stock FROM book WHERE bno = '" +
                    bno +
                    "';";
            rs = stmt.executeQuery(sql);
            if (rs.next()){
                count = rs.getInt(1);
            }
            if(count == 0){
                System.out.println("图书缺货");
                return;
            }
            //借书证图书均存在，则进行借书步骤
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            sql = "INSERT INTO borrow(cno,bno,borrow_date) VALUES ('" +
                    cno +
                    "','" +
                    bno +
                    "','" +
                    date +
                    "');";
            int result = stmt.executeUpdate(sql);
            if (result != -1){
                System.out.println("借书成功！");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
    }


    public void returnBook() throws Exception{
        Connection conn = null;
        String url =  "jdbc:mysql://localhost:3306/LibrarySystem?"
                + "user=root&password=19960415&useUnicode=true&characterEncoding=UTF8&useSSL=true";
        String sql;

        try {

            System.out.println("请输入你的借书证编号");
            Scanner reader = new Scanner(System.in);
            String cno = reader.nextLine();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            sql = "SELECT count(*) FROM card WHERE cno = '" +
                    cno +
                    "';";
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            if (rs.next()){
                count = rs.getInt(1);
            }
            if (count == 0) {
                System.out.println("借书证号不存在，请确认后重新输入");
                return;
            }
            System.out.println("请输入你要退还的图书编号");
            String bno = reader.nextLine();

            sql = "SELECT count(*) FROM book WHERE bno = '" +
                    bno +
                    "';";
            rs = stmt.executeQuery(sql);
            if (rs.next()){
                count = rs.getInt(1);
            }
            if(count == 0){
                System.out.println("图书不存在，请确认后再进行查询");
                return;
            }


            //借书证图书均存在，则进行还书步骤
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(new Date());
            sql = "UPDATE borrow SET return_date = '" +
                    date +
                    "' WHERE bno = '" +
                    bno +
                    "' AND cno = '" +
                    cno +
                    "';";
            int result = stmt.executeUpdate(sql);
            if (result != -1){
                System.out.println("还书成功！");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
