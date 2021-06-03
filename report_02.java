package report;

import java.sql.*;
import java.util.Scanner;

public class report_02 {

	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String connectionUrl = "jdbc:sqlserver://localhost:6006;database=largeDB;integratedSecurity=true";
	            Connection conn = DriverManager.getConnection(connectionUrl);
	            Statement stmt = conn.createStatement();
	            System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
	            Scanner sc = new Scanner(System.in);
	            
	            System.out.println("학과명을 입력하면 해당 학과가 학년도 별로 지금까지 개설한 강좌의 수를 출력하는 프로그램입니다.");
	            System.out.print("학과 이름을 입력하세요 : ");
	            String dept_name = sc.nextLine();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM dept_section_cnt WHERE dept_name = '" + dept_name + "'");

	            System.out.println(dept_name + " 학과가 학년도 별로 지금까지 개설한 강좌의 수를 계산 중 입니다...");
	            
	            if (rs.next()) {
	            	System.out.println(dept_name + " 학과가 학년도 별로 지금까지 개설한 강좌의 수는 다음과 같습니다.");
	            	do {
	            		String field1 = rs.getString("dept_name");
	            		String field2 = rs.getString("year");
	            		String field3 = rs.getString("cnt_courses");
	            		System.out.print(field1 + "\t");
	            		System.out.print(field2 + "\t");
	            		System.out.println(field3);
	            	} while(rs.next());
	            }
	            else {
	            	System.out.println("해당 학과는 지금까지 개설한 강좌가 없습니다!");
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
