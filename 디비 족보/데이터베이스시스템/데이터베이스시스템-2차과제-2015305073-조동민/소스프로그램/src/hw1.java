import java.sql.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class hw1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:54718;database=largeDB;integratedSecurity=true";
            Connection conn = DriverManager.getConnection(connectionUrl);
            Statement stmt = conn.createStatement();
            System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("�˻��� �л��� ID �Է� : ");
        	String ID = scanner.nextLine();
        	System.out.println("ID = " + ID + " �� �л��� �̼��� ��������");
        	ResultSet rs = stmt.executeQuery("SELECT * FROM takes, course "
        			+ "WHERE takes.course_id = course.course_id and (grade != 'F' or grade is not null) and takes.ID = '" + ID + "'");
        	
        	boolean a = true;
            while( rs.next() ) {
            	String s1 = rs.getString("title");
           
                System.out.println(s1);
                a = false;
            }
            if(a) {
        		System.out.println("�̼��� ������ �����ϴ�.");
        	}
            scanner.close();
            rs.close();
            stmt.close();   
            conn.close();
        } catch (ClassNotFoundException sqle) {
        	System.out.println("SQLException : " + sqle);
        	}
	}
}
