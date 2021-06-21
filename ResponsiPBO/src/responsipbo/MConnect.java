package responsipbo;

import java.sql.*;

public class MConnect {
    private static Connection MConnect;
    public static Connection getKoneksi()
    {
        if(MConnect == null)
        {
            try {
                String url="jdbc:mysql://localhost/responsiprakpbo";
                String username= "root";     
                String password= "";         
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                MConnect = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
            }
        }
        return MConnect;
    }
}
