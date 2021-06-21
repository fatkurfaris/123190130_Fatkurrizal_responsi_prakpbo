package responsipbo;

import java.sql.*;
import javax.swing.*;

public class MAdmin implements CAdmin{

    @Override
    public void setting(VAdmin admin) throws SQLException { 
        try {
            Connection con = MConnect.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "update akun set "
                        + "id           = '" + admin.fid.getText()            +"', "
                        + "nama         = '" + admin.fnama.getText()          +"', "
                        + "email        = '" + admin.femail.getText()         +"', "
                        + "password     = '" + admin.fpass.getText()          +"', "
                        + "role         = '" + admin.crole.getSelectedItem()  +"' "
                        + "where id     = '" + admin.fid.getText()            + "'";
            stt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil di Ubah");
            stt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan!", "Hasil", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        finally{
            admin.setLebarKolom();
        }
    }

    @Override
    public void show(VAdmin admin) throws SQLException { //menampilkan data yang diambil dari database
        try {
            
            Connection con = MConnect.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "select * from akun";
            ResultSet res = stt.executeQuery(sql);
            while(res.next())
            {
                Object[] ob= new Object[8];
                ob[0] = res.getString(1);
                ob[1] = res.getString(2);
                ob[2] = res.getString(3);
                ob[3] = res.getString(4);
                ob[4] = res.getString(5);
                admin.tblmodel.addRow(ob);
            } 
            stt.close();
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "gagal", "Hasil", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(VAdmin admin) throws SQLException { 
        try{
            Connection con = MConnect.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "delete from akun where id=" +admin.fid.getText();
            stt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
            stt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void klik(VAdmin admin) throws SQLException {
        String role = null;
        try {
             int select = admin.tabel.getSelectedRow();
             admin.fid.setText(admin.tblmodel.getValueAt(select, 0).toString());
             admin.fnama.setText(admin.tblmodel.getValueAt(select, 1).toString());
             admin.femail.setText(admin.tblmodel.getValueAt(select, 2).toString());
             admin.fpass.setText(admin.tblmodel.getValueAt(select, 3).toString());
             role = String.valueOf(admin.tblmodel.getValueAt(select, 4).toString());
             admin.crole.setSelectedItem(admin.tblmodel.getValueAt(select, 4).toString());
             
        } catch (Exception e) {
        }
    }   
}
