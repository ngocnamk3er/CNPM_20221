package Project.DAO;

import Project.DBConnect.JDBCUtil;
import Project.Model.Phi;
import Project.Model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDAO implements DAO<Users>{

    @Override
    public int insert(Users o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO users(ID,username,password)"
                    +" VALUES(?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            st.setString(2,o.getUserName());
            st.setString(3,o.getPassword());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return ans;
    }

    @Override
    public int update(Users o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE users "+"SET username = ?,password = ?"
                    +"WHERE ID = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,o.getUserName());
            st.setString(2,o.getPassword());
            st.setInt(3,o.getID());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public int delete(Users o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from users "+"WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public ArrayList<Users> selectAll() {
        ArrayList<Users> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM users";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Users o=new Users();
                o.setID(rs.getInt("ID"));
                o.setUserName(rs.getString("username"));
                o.setPassword(rs.getString("password"));
                ans.add(o);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}