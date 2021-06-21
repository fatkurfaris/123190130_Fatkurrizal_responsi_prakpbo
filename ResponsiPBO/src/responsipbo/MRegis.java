package responsipbo;

import java.sql.*;
import javax.swing.*;

public class MRegis implements CRegis{

    @Override
    public void save(VRegis data) throws SQLException { 
        try{
            Connection con = MConnect.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "insert into akun values(NULL, '"+ data.fnama.getText() + "','"+data.femail.getText()+"','"+data.fpass.getText()+"','"+data.crole.getSelectedItem()+"')";
            stt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Akun Berhasil Disimpan!", "Hasil",JOptionPane.INFORMATION_MESSAGE);            
            stt.close();
        }catch(Exception ex){
            
        }
    }
    
}
