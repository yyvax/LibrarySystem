import java.util.Scanner;

/**
 * Created by CZ on 2017/4/8.
 */
public class Menu {
    public static void main(String[] args) throws Exception {
        System.out.println("欢迎使用图书管理系统");
        BorrowOrReturnBook borrowAndReturn = new BorrowOrReturnBook();
        while (true){
            System.out.println("--------------------------------");
            System.out.println("1.管理员身份登陆 2.学生/教师身份登陆 0.退出");
            System.out.println("--------------------------------");
            Scanner reader = new Scanner(System.in);
            int choice = reader.nextInt();
            int choice2;
            switch (choice){
                //admin
                case 1:{
                    System.out.println("--------------------------------------");
                    System.out.println("1.图书查询 2.图书入库 3.借书证管理 0.返回");
                    System.out.println("--------------------------------------");
                    choice2 = reader.nextInt();
                    switch (choice2){
                        case 1:{
                            QueryBook.main(args);
                            break;
                        }
                        case 2:{
                            StoreBook.main(args);
                            break;
                        }

                        case 3:{
                            CardManage.main(args);
                            break;
                        }
                        case 0:{
                            return;
                        }
                    }
                    break;
                }
                //user
                case 2:{
                    System.out.println("--------------------------------------");
                    System.out.println("1.图书查询 2.借书 3.还书 0.返回");
                    System.out.println("--------------------------------------");
                    choice2 = reader.nextInt();
                    switch (choice2){
                        case 1:{
                            //query
                            QueryBook.main(args);
                            break;
                        }
                            //borrow
                        case 2:{
                            try {
                                borrowAndReturn.borrowBook();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case 3:{
                            //return
                            try {
                                borrowAndReturn.returnBook();
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                            break;
                        }
                        case 0:{
                            //back
                            return;
                        }
                    }
                    break;
                }
                case 0:{
                    return;
                }
            }
        }

    }
}
