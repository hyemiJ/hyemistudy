package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTemplate {
    public void executeUpdate(User user , String sql , PreparedStatementSetter pss) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
             con = ConnectionManager.getConnection();
            //String sql = "INSERT INTO users VALUES (?,?,?,?)";
            ps = con.prepareStatement(sql);
            //ps.setString(1,user.getUserId());
            //ps.setString(2,user.getPassword());
            //ps.setString(3,user.getName());
            //ps.setString(4,user.getEmail());
            pss.setter(ps);
            ps.executeUpdate();//insert 쿼리 실행
        }finally {
            //자원 해제
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
    }
}
