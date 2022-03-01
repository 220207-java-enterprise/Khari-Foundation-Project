package com.revature.foundations.daos;

import com.revature.foundations.models.Users;
import com.revature.foundations.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO attempt to centralize exception handling in service layer
public class UsersDAO implements CrudDAO<Users> {


    private final String rootSelect = "SELECT " +
            "au.user_id, au.given_name, au.surname, au.email, au.username, au.password, au.is_active, ur.role_id " +
            "FROM ers_users au " +
            "JOIN ers_user_roles ur " +
            "ON au.role_id = ur.id ";
    //------------------------------
    public Users findUsersByusername(String username) {

        Users users = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                users = new Users();
                users.setuser_id(rs.getString("user_id"));
                users.setgiven_name(rs.getString("given_name"));
                users.setsurname(rs.getString("surname"));
                users.setEmail(rs.getString("email"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setIsActive(rs.getString("is_active"));       //commented out, come back if needed
                users.setRole_Id(new UsersRole(rs.getString("role_id")/*, rs.getString("role_name")*/));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    //-------------------------------
    @Override
    public Users[] getAll() {

        return new Users[0];
    }
    //----------------------------------------------------------------
    /*
    * WHERE user_id table
     */
    @Override
    public Users getById(String id) {

        Users users = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE user_id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                users = new Users();
                users.setuser_id(rs.getString("user_id"));
                users.setgiven_name(rs.getString("given_name"));
                users.setsurname(rs.getString("surname"));
                users.setEmail(rs.getString("email"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setIsActive(rs.getString("is_active"));       //commented out, come back if needed
                users.setRole_Id(new UsersRole(rs.getString("role_id")/*, rs.getString("role_name")*/));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return users;

        return null;
    }

    /*
    *INSERT INTO ers_users tables VALUES
     */
    @Override
    public void save(Users newObj) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ers_users VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newUser.getuser_id());
            pstmt.setString(2, newUser.getgiven_name());
            pstmt.setString(3, newUser.getsurname());
            pstmt.setString(4, newUser.getemail());
            pstmt.setString(5, newUser.getusername());
            pstmt.setString(6, newUser.getpassword());
            pstmt.setString(6, newUser.getis_active());
            pstmt.setString(7, newUser.getrole_id());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist user to data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

    }

    @Override
    public void update(Users updatedObj) {

    }

    @Override
    public void deleteById(String id) {

    }
}
