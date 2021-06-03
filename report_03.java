package report;

import java.sql.*;
import java.util.Scanner;

public class report_03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:6006;database=largeDB;integratedSecurity=true";
            Connection conn = DriverManager.getConnection(connectionUrl);
            System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
            Scanner sc = new Scanner(System.in);
            
            System.out.println("������ �̸��� �Է��ϸ� �ش� ������ �����ϴ� �л��� ���� ����ϴ� ���α׷��Դϴ�.");
            System.out.print("������ �̸��� �Է��ϼ��� : ");
            String inst_name = sc.nextLine();
           
            CallableStatement cstmt = conn.prepareCall("{? = call dbo.inst_advise_student (?) }");
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setString(2, inst_name);
            cstmt.execute();
            
            String output = cstmt.getString(1);
            System.out.println(inst_name + " ������ �����ϴ� �� �л� ���� " + output + " �� �Դϴ�.");
            
            sc.close();
            cstmt.close();   
            conn.close();
        } catch (ClassNotFoundException sqle) {
        	System.out.println("SQLException : " + sqle);
        }
   }
}
