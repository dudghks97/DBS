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
            System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
            Scanner sc = new Scanner(System.in);
            
            System.out.println("������ ID�� �Է��ϸ� �ش� ������ �þƼ� ������ ������ ��������� ����ϴ� ���α׷��Դϴ�.");
            System.out.print("������ ID�� �Է��ϼ��� : ");
            String i_ID = sc.nextLine();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teaches, course WHERE teaches.ID = " + i_ID 
            								+ "and teaches.course_id = course.course_id");

            System.out.println("ID�� " + i_ID + "�� ������ �þƼ� ������ ������ ��������� �˻��մϴ�...");
            
            if (rs.next()) {
            	System.out.println("�ش� ������ �þƼ� ������ ������ ��������� ������ �����ϴ�.");
            	do {
            		String field1 = rs.getString("title");
            		System.out.println(field1);
            	} while(rs.next());
            }
            else {
            	System.out.println("�ش� ������ ���� ���°� ����.");
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
