/**
 * Created by CZ on 2017/4/6.
 */
import java.sql.*;

public class SystemInit {
    public static void main(String[] args) throws Exception{
        Connection conn = null;
        String sql, sql2,sql3;
        String url =  "jdbc:mysql://localhost:3306/LibrarySystem?"
                + "user=root&password=19960415&useUnicode=true&characterEncoding=UTF8&useSSL=true";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            sql = "CREATE TABLE book(bno CHAR(6)," +
                    "category VARCHAR(20)," +
                    "title VARCHAR(20)," +
                    "press VARCHAR(20)," +
                    "year CHAR(4)," +
                    "author VARCHAR(20)," +
                    "price DECIMAL(7,2)," +
                    "total INT," +
                    "stock INT," +
                    "PRIMARY KEY(bno));";

            sql2 =  "create table card" +
                    "  (cno char(7)," +
                    "  name varchar(10)," +
                    "  department varchar(40)," +
                    "  type char(1)," +
                    "  primary key(cno)," +
                    "  check(type in('T','S')));";
            sql3 =  "  create table borrow" +
                    "  (cno char(7)," +
                    "  bno  char(8)," +
                    "  borrow_date varvhar(40)," +
                    "  return_date varchar(40)," +
                    "  primary key(cno,bno)," +
                    "  foreign key (cno) references card(cno)," +
                    "  foreign key (bno) references book(bno));";
            int result = stmt.executeUpdate(sql);
            int result2 = stmt.executeUpdate(sql2);
            int result3 = stmt.executeUpdate(sql3);
            // executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            if (result != -1 && result2 != -1 && result3 != -1) {
                System.out.println("创建数据表成功");
                sql = "INSERT INTO book(bno,category,title,press,year,author,price,total,stock)" +
                        " VALUES('311440','math','calculus','PeopleRepublic','1999','newton',19.50, 500, 100)";
                result = stmt.executeUpdate(sql);

                sql = "SELECT * FROM book";
                ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
                System.out.println("book_id\tcategory\ttitle\tpublishing_house\tyear\tauthor\tprice\ttotal_amount\tamount");
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
        catch (SQLException e) {
                System.out.println("MySQL操作错误");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.close();
            }
    }
}
