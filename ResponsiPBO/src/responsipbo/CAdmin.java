package responsipbo;

import java.sql.*;

public interface CAdmin {
    public void setting(VAdmin admin) throws SQLException;
    public void show(VAdmin admin) throws SQLException;
    public void delete(VAdmin admin) throws SQLException;
    public void klik(VAdmin admin) throws SQLException;
}
