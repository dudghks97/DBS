package report;

import java.sql.*;
import java.util.Scanner;

public class report_03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:6006;database=largeDB;integratedSecurity=true";
            Connection conn = DriverManager.getConnection(connectionUrl);
            System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
            Scanner sc = new Scanner(System.in);
            
            System.out.println("교수의 이름을 입력하면 해당 교수가 지도하는 학생의 수를 출력하는 프로그램입니다.");
            System.out.print("교수의 이름을 입력하세요 : ");
            String inst_name = sc.nextLine();
           
            CallableStatement cstmt = conn.prepareCall("{? = call dbo.inst_advise_student (?) }");
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setString(2, inst_name);
            cstmt.execute();
            
            String output = cstmt.getString(1);
            System.out.println(inst_name + " 교수가 지도하는 총 학생 수는 " + output + " 명 입니다.");
            
            sc.close();
            cstmt.close();   
            conn.close();
        } catch (ClassNotFoundException sqle) {
        	System.out.println("SQLException : " + sqle);
        }
   }
}
