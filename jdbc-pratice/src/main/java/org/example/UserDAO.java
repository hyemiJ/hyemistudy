package org.example;

import java.sql.*;

public class UserDAO {

    private Connection getConnection() {
        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
        String id = "sa";
        String password = "";
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(url,id,password);
        } catch (Exception e) {
            return null;
        }
    }

    public void create(User user) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            String sql = "INSERT INTO users VALUES (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getUserId());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getName());
            ps.setString(4,user.getEmail());
            ps.executeUpdate();//insert 쿼리 실행
        }finally {
            //자원 해제
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM users WHERE userId = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,userId);

            rs = ps.executeQuery();

            if(rs.next()) {
                 user = new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
                return user;
            }
        }finally {
            //마지막에 사용된 객체부터 자원 해제
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return user;
    }
}
