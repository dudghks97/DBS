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
            System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
           
            conn.setAutoCommit(false);
            Scanner sc = new Scanner(System.in);
            
            System.out.print("���� ID �Է� : ");
            String course_id = sc.nextLine();
            System.out.print("������� �Է� : ");
            String title = sc.nextLine();
            System.out.print("�а����� �Է� : ");
            String dept_name = sc.nextLine();
            System.out.print("������ �Է� : ");
            int credits = sc.nextInt();
            
            PreparedStatement pStmt = conn.prepareStatement("insert into course values(?,?,?,?)");
            
            pStmt.setString(1, course_id);
            pStmt.setString(2, title);
            pStmt.setString(3, dept_name);
            pStmt.setInt(4, credits);
            pStmt.executeUpdate();
            
            System.out.print("'���' or '���' �Է� : ");
            String act = sc.next();
            
            if(act.equals("���")) {
            	conn.commit();
            	System.out.println("��ϵǾ����ϴ�.");
            }
            else if(act.equals("���")) {
            	conn.rollback();
            	System.out.println("����� ��ҵǾ����ϴ�.");
            }
            else {
            	System.out.println("��� or ��� �� �ϳ��� ������.");
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