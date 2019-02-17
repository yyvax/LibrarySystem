import java.util.Scanner;
import java.sql.*;
/**
 * Created by CZ on 2017/4/8.
 */


public class CardManage {

    Connection conn = null;
    String url =  "jdbc:mysql://localhost:3306/LibrarySystem?"
            + "user=root&password=19960415&useUnicode=true&characterEncoding=UTF8&useSSL=true";


    public static void main(String args[]){
        CardManage card = new CardManage();
        while (true){
            System.out.println("1/添加借书证 2/删除借书证 0/返回");
            Scanner reader;
            reader = new Scanner(System.in);
            int choice = reader.nextInt();
            switch (choice){
                case 0:
                    return;
                case 1:
                    try {
                        card.addCard();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        card.deleteCard();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private void addCard() throws Exception{
        String sql;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            //输入借书证信息
            scanCardInf();
            sql = "INSERT INTO card (cno, name, department, type) VALUES ('" +
                    Card.cno +
                    "','" + Card.name +  "','" + Card.department + "','" + Card.type + "');";
            int result = stmt.executeUpdate(sql);
            if (result != -1){
                System.out.println("---借书证添加成功");
            }
        }
        catch (SQLException e){
            if (e.toString().contains("Duplicate entry") && e.toString().contains("PRIMARY")){
                System.out.println("---借书证已存在");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void deleteCard(){
        String sql;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            System.out.println("请输入要删除的借书证号");
            //输入借书证信息
            Scanner reader = new Scanner(System.in);
            Card.cno = reader.nextLine();
            sql = "DELETE FROM card WHERE cno = '" +
                    Card.cno + "';";
            int result = stmt.executeUpdate(sql);
            if (result != -1){
                System.out.println("---借书证删除成功");
            }
        }
        catch (SQLException e){
            System.out.println("MySQL处理错误");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void scanCardInf(){
        Scanner reader = new Scanner(System.in);
        System.out.println("---请输入借书证号码");
        Card.cno = reader.nextLine();
        System.out.println("---请输入姓名");
        Card.name = reader.nextLine();
        System.out.println("---请输入所属院系");
        Card.department = reader.nextLine();
        System.out.println("---请选择类型 教师或学生(请输入T/S)");
        Card.type = reader.nextLine();
    }

    static class Card{
        static String cno;
        static String name;
        static String department;
        static String type;
    }

}


