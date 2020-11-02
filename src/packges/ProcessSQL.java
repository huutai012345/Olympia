/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packges;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NHT
 */
public class ProcessSQL {
    public Connection ketNoi; 
     
    public Connection layKetNoi() { 
     
        String uRL = "jdbc:sqlserver://;databaseName=LTM";
        String userName = "sa";
        String password = "123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.ketNoi = DriverManager.getConnection(uRL, userName, password);
            System.out.println("Ket noi CSDL thanh cong");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Khong ket noi duoc voi CSDL");
        }
        return ketNoi;
    }
      
    public String checkUser(String user,String pass)
    {
        String sql = "SELECT * FROM SINHVIEN WHERE UserName=? AND PassWord=?";
        try {
            PreparedStatement ps = ketNoi   .prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //rs.getString("MaLenh"), rs.getString("MaTKNH"), rs.getString("MaCP")
                return rs.getString("MASV");
            }
            
            rs.close();
            ps.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(ProcessSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    public List<Question> layCauHoi()
    {
        List<Question> questionList=new ArrayList<>();
        String sql = "SELECT TOP 10 * FROM BODE ORDER BY NEWID()";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question question=new Question(rs.getString("NOIDUNG"), rs.getString("A"), 
                        rs.getString("B"),rs.getString("C"),rs.getString("D"),
                        rs.getString("DAP_AN"));
                
                questionList.add(question);
            }
            
            rs.close();
            ps.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(ProcessSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questionList;
    }
    
    public void insertBangDiem(String maSV,int point)
    {
        String sql = "INSERT INTO BANGDIEM VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maSV);
            ps.setInt(2, 1);
            Date untilDate=new Date();
            ps.setDate(3,new java.sql.Date(untilDate.getTime()));
            ps.setInt(4, point);
            ps.setString(5, "Bai1") ;
          
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProcessSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ProcessSQL() {
       this.layKetNoi();
    }
    
    
}
