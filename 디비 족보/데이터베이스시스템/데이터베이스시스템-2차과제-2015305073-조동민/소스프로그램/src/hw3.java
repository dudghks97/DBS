import java.sql.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class hw3 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:54718;database=largeDB;integratedSecurity=true";
            Connection conn = DriverManager.getConnection(connectionUrl);
            Statement stmt = conn.createStatement();
            System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
           
            conn.setAutoCommit(false);
            Scanner sc = new Scanner(System.in);
            
            System.out.print("과목 ID 입력 : ");
            String course_id = sc.nextLine();
            System.out.print("과목명을 입력 : ");
            String title = sc.nextLine();
            System.out.print("학과명을 입력 : ");
            String dept_name = sc.nextLine();
            System.out.print("학점을 입력 : ");
            int credits = sc.nextInt();
            
            PreparedStatement pStmt = conn.prepareStatement("insert into course values(?,?,?,?)");
            
            pStmt.setString(1, course_id);
            pStmt.setString(2, title);
            pStmt.setString(3, dept_name);
            pStmt.setInt(4, credits);
            pStmt.executeUpdate();
            
            System.out.print("'등록' or '취소' 입력 : ");
            String act = sc.next();
            
            if(act.equals("등록")) {
            	conn.commit();
            	System.out.println("등록되었습니다.");
            }
            else if(act.equals("취소")) {
            	conn.rollback();
            	System.out.println("등록이 취소되었습니다.");
            }
            else {
            	System.out.println("등록 or 취소 중 하나를 고르세요.");
            	System.exit(1);
            }
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM course WHERE course_id = '" + course_id + "' ");
            
            while( rs.next() ) {
            	String s1 = rs.getString("course_id");
            	String s2 = rs.getString("title");
            	String s3 = rs.getString("dept_name");
            	int s4 = rs.getInt("credits");
            	
            	System.out.print(s1 + "\t");
            	System.out.print(s2 + "\t");
            	System.out.print(s3 + "\t");
            	System.out.println(s4);
            }
          
            sc.close();
        	rs.close();
        	pStmt.close();
            stmt.close();   
            conn.close();
        }catch (ClassNotFoundException sqle) {
        	System.out.println("SQLException : " + sqle);
        }
    }
}