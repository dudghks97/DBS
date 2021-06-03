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
	            System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
	            Scanner sc = new Scanner(System.in);
	            
	            System.out.println("�а����� �Է��ϸ� �ش� �а��� �г⵵ ���� ���ݱ��� ������ ������ ���� ����ϴ� ���α׷��Դϴ�.");
	            System.out.print("�а� �̸��� �Է��ϼ��� : ");
	            String dept_name = sc.nextLine();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM dept_section_cnt WHERE dept_name = '" + dept_name + "'");

	            System.out.println(dept_name + " �а��� �г⵵ ���� ���ݱ��� ������ ������ ���� ��� �� �Դϴ�...");
	            
	            if (rs.next()) {
	            	System.out.println(dept_name + " �а��� �г⵵ ���� ���ݱ��� ������ ������ ���� ������ �����ϴ�.");
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
	            	System.out.println("�ش� �а��� ���ݱ��� ������ ���°� �����ϴ�!");
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
