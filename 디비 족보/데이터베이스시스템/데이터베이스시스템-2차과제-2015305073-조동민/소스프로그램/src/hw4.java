import java.sql.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class hw4 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:54718;database=largeDB;integratedSecurity=true";
            Connection conn = DriverManager.getConnection(connectionUrl);
            Statement stmt = conn.createStatement();
            System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
            
            Scanner sc = new Scanner(System.in);
            
            System.out.print("�������� �Է��ϼ��� : ");
            String inst_name = sc.nextLine();
            
            CallableStatement cstmt = conn.prepareCall("{? = call dbo.inst_advise_student (?)}");
            cstmt.registerOutParameter(1,java.sql.Types.INTEGER);
            cstmt.setString(2, inst_name);
            cstmt.execute();
            
            String s1 = cstmt.getString(1);
            System.out.println( inst_name + " ������ �����ϴ� �л� �� : " + s1);
            
            
            
            sc.close();
            stmt.close();
            cstmt.close();
            conn.close();
        }catch (ClassNotFoundException sqle) {
        	System.out.println("SQLException : " + sqle);
        }
    }
}

           