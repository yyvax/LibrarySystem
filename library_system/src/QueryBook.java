import java.sql.*;
import java.util.Scanner;

/**
 * Created by CZ on 2017/4/8.
 */
public class QueryBook {
    public static void main(String[] args){
        QueryBook Q1 = new QueryBook();
        while (true){
            System.out.println("1.类别 2.书名 3.出版社 4.年份 5.作者 6.价格 0.返回");
            Scanner reader;
            reader = new Scanner(System.in);
            int choice = reader.nextInt();
            switch (choice){
                case 1:
                    try {
                        System.out.println("请输入要查询的图书类别");
                        String category;
                        category = reader.next();
                        Q1.queryInCategoryTitlePressAuthor(category, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:{
                    try{
                        System.out.println("请输入要查询的图书名");
                        String title;
                        title = reader.next();
                        Q1.queryInCategoryTitlePressAuthor(title, 2);

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
                case 3:{
                    try{
                        System.out.println("请输入要查询的出版社");
                        String press;
                        press = reader.next();
                        Q1.queryInCategoryTitlePressAuthor(press, 3);

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    break;

                }
                case 4:{
                    try {
                        System.out.println("请输入要查询的年份区间");
                        System.out.print("起始年份：");
                        String year1 = reader.next();
                        System.out.print("终止年份：");
                        String year2 = reader.next();
                        Q1.queryByPrice(year1,year2);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
                case 5:{
                    try{
                        System.out.println("请输入要查询的作者");
                        String author;
                        author = reader.next();
                        Q1.queryInCategoryTitlePressAuthor(author, 5);

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    break;

                }

                case 6:{
                    try {
                        System.out.println("请输入要查询的价格区间");
                        System.out.print("最低价格：");
                        String price1 = reader.next();
                        System.out.print("最高价格：");
                        String price2 = reader.next();
                        Q1.queryByPrice(price1,price2);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 0:{
                    return;
                }
            }
        }
        //类别, 书名, 出版社, 年份(年份区间), 作者, 价格(区间)
        //书号, 类别, 书名, 出版社, 年份, 作者, 价格, 总藏书量, 库存
    }

    private void queryInCategoryTitlePressAuthor(String str, int num){
        Connection conn = null;
        String url =  "jdbc:mysql://localhost:3306/LibrarySystem?"
                + "user=root&password=19960415&useUnicode=true&characterEncoding=UTF8&useSSL=true";
        String sql=  "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            switch (num){
                case 1:{
                    sql = "SELECT * FROM book WHERE category = '" +
                    str +
                    "';";
                    break;
                }
                case 2:{
                    sql = "SELECT * FROM book WHERE title = '" +
                    str +
                    "';";
                    break;
                }
                case 3:{
                    sql = "SELECT * FROM book WHERE press = '" +
                    str +
                    "';";
                    break;
                }
                case 5:{
                    sql = "SELECT * FROM book WHERE author = '" +
                    str +
                    "';";
                    break;
                }
                default:break;
            }

            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("book_id\tcategory\ttitle\tpress\t\tyear\tauthor  \tprice\ttotal\tstock");
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
        } catch (SQLException e) {
            System.out.println("MySQL错误");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void queryByPrice(String price1, String price2){

        Connection conn = null;
        String url =  "jdbc:mysql://localhost:3306/LibrarySystem?"
                + "user=root&password=19960415&useUnicode=true&characterEncoding=UTF8&useSSL=true";
        String sql=  "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
                    sql = "SELECT * FROM book WHERE price > '" + price1 +
                    "' AND price < '" +
                            price2 +  "';";

            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("book_id\tcategory\ttitle\tpress\t\tyear\tauthor  \tprice\ttotal\tstock");
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
        } catch (SQLException e) {
            System.out.println("MySQL错误");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
