package report;

import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

public class report_01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:6006;database=largeDB;integratedSecurity=true";
            Connection conn = DriverManager.getConnection(connectionUrl);
            Statement stmt = conn.createStatement();
            System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
            Scanner sc = new Scanner(System.in);
            
            System.out.println("교수의 ID를 입력하면 해당 교수가 맡아서 진행한 강좌의 교과목명을 출력하는 프로그램입니다.");
            System.out.print("교수의 ID를 입력하세요 : ");
            String i_ID = sc.nextLine();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teaches, course WHERE teaches.ID = " + i_ID 
            								+ "and teaches.course_id = course.course_id");

            System.out.println("ID가 " + i_ID + "인 교수가 맡아서 진행한 강좌의 교과목명을 검색합니다...");
            
            if (rs.next()) {
            	System.out.println("해당 교수가 맡아서 진행한 강좌의 교과목명은 다음과 같습니다.");
            	do {
            		String field1 = rs.getString("title");
            		System.out.println(field1);
            	} while(rs.next());
            }
            else {
            	System.out.println("해당 교수는 맡은 강좌가 없음.");
            }
            
            sc.close();
            rs.close();
            stmt.close();   
            conn.close();
        } catch (ClassNotFoundException sqle) {
        	System.out.println("SQLException : " + sqle);
        }
   }


}
