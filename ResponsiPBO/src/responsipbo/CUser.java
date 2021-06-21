package responsipbo;

import java.sql.*;

public interface CUser {
    public void save(VUser data) throws SQLException;
    public void setting(VUser data) throws SQLException;
    public void delete(VUser data) throws SQLException;
    public void show(VUser data) throws SQLException;
    public void klik(VUser data) throws SQLException;
    public void update(VUser data) throws SQLException;
    public void showaccount(VUser data) throws SQLException;
}
