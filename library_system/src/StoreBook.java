/**
 * Created by CZ on 2017/4/7.
 */
import java.util.*;
import java.sql.*;

public class StoreBook {
    public static void main(String args[]){

        while (true){
            System.out.println("1/单本入库 2/批量入库 0/返回");
            Scanner reader;
            reader = new Scanner(System.in);
            int choice = reader.nextInt();
            switch (choice){
                case 0:
                    return;
                case 1:
                    try {
                        insertOneBook();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    break;
            }
        }
    }

    private static void insertOneBook() throws Exception{
        Connection conn = null;
        String url =  "jdbc:mysql://localhost:3306/LibrarySystem?"
                + "user=root&password=19960415&useUnicode=true&characterEncoding=UTF8&useSSL=true";
        String sql;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            //输入图书信息
            scanBookInf();
            sql = "INSERT INTO book(bno,category,title,press,year,author,price,total,stock)" +
                    " VALUES('" +
                    Book.bno +
                    "','" +
                    Book.category +
                    "','" +
                    Book.title +
                    "','" +
                    Book.press +
                    "','" +
                    Book.year +
                    "','" +
                    Book.author +
                    "'," +
                    Book.price +
                    "," +
                    Book.stock +
                    "," +
                    Book.stock +
                    ")";

            int result = stmt.executeUpdate(sql);
            if (result != -1) {
                System.out.println("---添加成功---");
                sql = "SELECT * FROM book WHERE bno = '" +
                        Book.bno + "';";
                ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
                System.out.println("book_id\tcategory\t\ttitle  \tpress\t\tyear\tauthor  \tprice\ttotal\tstock");
                while (rs.next()) {
                    System.out
                            .println(rs.getString(1) + "\t"
                                    + rs.getString(2) + "\t"
                                    + rs.getString(3) + "\t"
                                    + rs.getString(4) + "\t"
                                    + rs.getString(5) + "\t"
                                    + rs.getString(6) + "\t"
                                    + rs.getFloat(7) + "\t"
                                    + rs.getInt(8) + "\t"
                                    + rs.getInt(9));// 入如果返回的是int类型可以用getInt()
                }
            }
        }
        catch (SQLException e){
            if (e.toString().indexOf("Duplicate entry") >= 0 && e.toString().indexOf("PRIMARY") >= 0){
                System.out.println("---图书在图书库中");
                sql = "UPDATE book SET price = " +
                        Book.price +
                        " WHERE bno = '" +
                        Book.bno + "';";
                Statement stmt = conn.createStatement();
                int result = stmt.executeUpdate(sql);
                sql = "UPDATE book " +
                        "SET stock = stock + " +
                        Book.stock +
                        ", total = total + " +
                        Book.stock +
                        " WHERE bno = '" +
                        Book.bno + "';";
                int result1 = stmt.executeUpdate(sql);
                if ( result != -1 && result1 != -1) {
                    System.out.println("---已添加到现有图书库中");
                    sql = "SELECT * FROM book WHERE bno = '" +
                            Book.bno + "';";
                    ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
                    System.out.println("book_id\tcategory\ttitle\tpress\tyear\tauthor\tprice\ttotal\tstock");
                    while (rs.next()) {
                        System.out
                                .println(rs.getString(1) + "\t"
                                        + rs.getString(2) + "\t"
                                        + rs.getString(3) + "\t"
                                        + rs.getString(4) + "\t"
                                        + rs.getString(5) + "\t"
                                        + rs.getString(6) + "\t"
                                        + rs.getString(7) + "\t"
                                        + rs.getString(8) + "\t"
                                        + rs.getString(9));// 入如果返回的是int类型可以用getInt()
                    }
                }
            }

//            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
    }

    private static void scanBookInf(){

        Scanner reader;
        reader  = new Scanner(System.in);
        System.out.println("book number:");
        Book.bno = reader.nextLine();
        System.out.println("category:");
        Book.category = reader.nextLine();
        System.out.println("title:");
        Book.title = reader.nextLine();
        System.out.println("press:");
        Book.press = reader.nextLine();
        System.out.println("year:");
        Book.year = reader.nextLine();
        System.out.println("author:");
        Book.author = reader.nextLine();
        System.out.println("price:");
        Book.price = reader.nextFloat();
        System.out.println("amount:");
        Book.stock = reader.nextInt();
    }

    static class Book {
        static String bno;
        static String category;
        static String title;
        static String press;
        static String year;
        static String author;
        static float price;
        static int total;
        static int stock;
    }
}
