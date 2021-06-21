package responsipbo;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
import javax.swing.*;

public class MUser implements CUser{
    String email, password, id;
    String upid, upnama, upemail, uppass, uprole;

    @Override
    public void save(VUser data) throws SQLException {
        try{
            Connection con = MConnect.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "insert into cerita values(NULL, '"+ data.fjudul.getText() + "','"+data.fisi.getText()+"','"+data.fid.getText()+"')";
            stt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Cerita Berhasil Disimpan!", "Hasil",JOptionPane.INFORMATION_MESSAGE);            
            stt.close();
        }catch(Exception ex){
            
        }
    }

    @Override
    public void setting(VUser data) throws SQLException { //untuk update data yang dipilih
        try {
            Connection con = MConnect.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "update cerita set "
                        + "cerid            = '" + data.fcerid.getText() +"', "
                        + "judul            = '" + data.fjudul.getText()+"', "
                        + "isi              = '" + data.fisi.getText()+"', "
                        + "id               = '" + data.fid.getText() +"' "
                        + "where cerid      = '" + data.fcerid.getText() + "'";
            stt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Cerita Berhasil di Ubah");
            stt.close();
            //con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cerita Gagal Disimpan!", "Hasil", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void delete(VUser data) throws SQLException { //untuk menghapus data yang telah dipilih
        try{
            Connection con = MConnect.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "delete from cerita where cerid=" +data.fcerid.getText();
            stt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Cerita Berhasil di Hapus");
            stt.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Cerita Gagal Dihapus!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void show(VUser data) throws SQLException { //untuk menampilkan data dari database ke view
        try {
            try {
                File myObj = new File("data.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String form = myReader.nextLine();
                    if(email == null) email = form;
                    else if(password == null) password = form;
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Class.forName("com.mysql.jdbc.Driver");
            Connection con = MConnect.getKoneksi();
            Statement stta = con.createStatement();
            String sqla = "select * from akun where email='"+email+"' and password='"+password+"'";
            ResultSet resa = stta.executeQuery(sqla);
            while(resa.next())
            {
                id = resa.getString(1); //mengambil id dari tabel akun
            } 
            data.fid.setText(""+id);
            stta.close();
            
            Statement stt = con.createStatement();
            String sql = "select * from cerita where id="+id; //mengambil seluruh data dari cerita yang memiliki id tertentu
            ResultSet res = stt.executeQuery(sql);
            while(res.next())
            {
                Object[] ob= new Object[8];
                ob[0] = res.getString(1);
                ob[1] = res.getString(2);
                ob[2] = res.getString(3);
                ob[3] = res.getString(4);
                data.tblmodel.addRow(ob);
            } 
            stt.close();
            //con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "gagal", "Hasil", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void klik(VUser data) throws SQLException { //untuk mengambil data dari tabel ke field
        try {
             int select = data.tabel.getSelectedRow();
             data.fcerid.setText(data.tblmodel.getValueAt(select, 0).toString());
             data.fjudul.setText(data.tblmodel.getValueAt(select, 1).toString());
             data.fisi.setText(data.tblmodel.getValueAt(select, 2).toString());
             data.fid.setText(data.tblmodel.getValueAt(select, 3).toString());           
        } catch (Exception e) {
        }
    }

    @Override
    public void update(VUser input) throws SQLException { 
        try{
            Connection con = MConnect.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "update akun set "
                        + "role         = '" + uprole  +"' "
                        + "where id     = '" + input.fid.getText()            + "'";
            stt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Akun Berhasil di Ubah");
            stt.close();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Akun Gagal Diupdate", "Hasil", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void showaccount(VUser data) throws SQLException { //untuk mengambil dan menampilkan data dari akun ke field user
        try{
            Connection con = MConnect.getKoneksi();
            Statement stt = con.createStatement();
            String sql = "select * from akun where id="+id;
            ResultSet res = stt.executeQuery(sql);
            while(res.next())
            {
                upid = res.getString(1);
                upnama = res.getString(2);
                upemail = res.getString(3);
                uppass = res.getString(4);
                uprole = res.getString(5);
            }
            stt.close();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Akun Gagal Ditampilkan", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
