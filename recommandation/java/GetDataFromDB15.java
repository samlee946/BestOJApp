import java.io.BufferedReader;
import java.io.File;  
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;




public class GetDataFromDB15 {
	public static void main(String args[]) throws SQLException, ClassNotFoundException {
		try {
			//read
			String in_filename = "stu_num.txt";
			InputStreamReader reader = new InputStreamReader(new FileInputStream(in_filename));
            BufferedReader br = new BufferedReader(reader);
            //write
			String out_filename = "stu_problem.txt";
			File file = new File(out_filename);
			BufferedWriter out = new BufferedWriter(new FileWriter(out_filename));
			//deal
            String line = "";  
            while ((line = br.readLine()) != null) { 
            	//System.out.println(line);
            	Class.forName("com.mysql.jdbc.Driver");
            	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/recommandation", "root", "123456");
                Statement stmt = conn.createStatement();  
                // table:transform  
                String sql = "select * from grade15 where user_name = " + line;  
                ResultSet rs = stmt.executeQuery(sql);  
            	out.write("≤‚ ‘\r\n");
                while (rs.next()) {  
                	out.write(rs.getString(2) + "\r\n");
                }
            }
            out.flush();
            out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
