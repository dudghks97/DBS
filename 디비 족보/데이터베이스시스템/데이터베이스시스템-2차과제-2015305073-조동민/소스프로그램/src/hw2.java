import java.sql.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class hw2 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:54718;database=largeDB;integratedSecurity=true";
            Connection conn = DriverManager.getConnection(connectionUrl);
            Statement stmt = conn.createStatement();
            System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("�˻��� �а��� �Է� : ");
        	String dept_name = scanner.nextLine();
        	
        	ResultSet rs = stmt.executeQuery("SELECT * FROM dept_section_cnt WHERE dept_name = '" + dept_name + "' ");
        	
        	System.out.println(""+ dept_name + " �а��� ���ݱ��� ������ ���� ��");
        	
        	boolean a = true;
        	
        	while( rs.next() ) {
        		
        		int s1 = rs.getInt("year");
        		int s2 = rs.getInt("cnt");
        		
        		System.out.print(s1 + "\t");
        		System.out.println(s2);
        		a = false;
        	}
        	if(a) {
        		System.out.println("������ ���¼��� �����ϴ�.");
        	}
        	scanner.close();
        	rs.close();
            stmt.close();   
            conn.close();
        }catch (ClassNotFoundException sqle) {
        	System.out.println("SQLException : " + sqle);
        }
    }
}
