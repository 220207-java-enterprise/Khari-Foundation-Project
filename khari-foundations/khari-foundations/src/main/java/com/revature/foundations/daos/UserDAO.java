package com.revature.foundations.daos;

import com.revature.foundations.models.UserRoles;
import com.revature.foundations.models.Users;
import com.revature.foundations.util.ConnectionFactory;
import com.revature.foundations.util.exceptions.DataSourceException;
import com.revature.foundations.util.exceptions.ResourcePersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO implements CrudDAO<Users> {
   /* @Override
    public Users[] getAll() {
        return new Users[0];
    }

    @Override
    public Users getById(String id) {
        return null;
    }

    @Override
    public void save(Users newObj) {

    }

    @Override
    public void update(Users updatedObj) {

    }

    @Override
    public void deleteById(String id) {

    }*/

    private final String rootSelect = "SELECT " +
            "au.User_id, au.Given_name, au.surname, au.email, au.username, au.password, au.Role_id, ur.is_active " +
            "FROM ers_users au " +
            "JOIN ers_user_roles ur " +
            "ON au.Role_id = ur.User_id ";

    public Users findUserByUsername(String username) {

        Users users = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                users = new Users();
                users.setUser_id(rs.getString("User_id"));
                users.setGiven_name(rs.getString("Given_name"));
                users.setSurname(rs.getString("surname"));
                users.setEmail(rs.getString("email"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setRole_id(new UserRoles(rs.getString("Role_id"), rs.getString("Role_id")));
                users.setIs_active(rs.getString("is_active"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public Users findUserByEmail(String email) {

        Users users = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE email = ?");
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                users = new Users();
                users.setUser_id(rs.getString("User_id"));
                users.setGiven_name(rs.getString("Given_name"));
                users.setSurname(rs.getString("surname"));
                users.setEmail(rs.getString("email"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setRole_id(new UserRoles(rs.getString("Role_id"), rs.getString("Role_id")));
                users.setIs_active(rs.getString("is_active"));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return users;

    }

    public Users findUsersByUsernameAndPassword(String username, String password) {

        Users authUsers = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                authUsers = new Users();
                authUsers.setUser_id(rs.getString("user_id"));
                authUsers.setGiven_name(rs.getString("given_name"));
                authUsers.setLastName(rs.getString("last_name"));
                authUsers.setEmail(rs.getString("email"));
                authUsers.setUsername(rs.getString("username"));
                authUsers.setPassword(rs.getString("password"));
                authUsers.setRole_id(new UserRoles(rs.getString("Role_id"), rs.getString("Role_id")));
                authUsers.setIs_active(rs.getString("is_active"));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return authUsers;
    }

    @Override
    public void save(Users newUsers) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ers_users VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newUsers.getUser_id());
            pstmt.setString(2, newUsers.getGiven_name());
            pstmt.setString(3, newUsers.getSurname());
            pstmt.setString(4, newUsers.getEmail());
            pstmt.setString(5, newUsers.getUsername());
            pstmt.setString(6, newUsers.getPassword());
            pstmt.setString(7, newUsers.getRole_id());

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
    public Users getById(String id) {

        Users users = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE User_id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                users = new Users();
                users.setUser_id(rs.getString("setUser_id"));
                users.setGiven_name(rs.getString("Given_name"));
                users.setSurname(rs.getString("surname"));
                users.setEmail(rs.getString("email"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setRole_id(new UserRoles(rs.getString("Role_id"), rs.getString("Role_id")));
                users.setIs_active(rs.getString("is_active"));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return users;

    }

    @Override
    public List<Users> getAll() {

        List<Users> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.createStatement().executeQuery(rootSelect);
            while (rs.next()) {
                Users user = new Users();
                user.setUser_id(rs.getString("user_id"));
                user.setGiven_name(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole_id(new UserRoles(rs.getString("role_id"), rs.getString("role_id")));
                user.setIs_active(rs.getString("is_active"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return users;
    }

    @Override
    public void update(Users updatedUsers) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ers_users " +
                    "SET Given_name = ?, " +
                    "Surname = ?, " +
                    "email = ?, " +
                    "username = ?, " +
                    "password = ? " +
                    "WHERE User_id = ?");
            pstmt.setString(1, updatedUsers.getGiven_name());
            pstmt.setString(2, updatedUsers.getSurname());
            pstmt.setString(3, updatedUsers.getEmail());
            pstmt.setString(4, updatedUsers.getUsername());
            pstmt.setString(5, updatedUsers.getPassword());
            pstmt.setString(6, updatedUsers.getUser_id());

            // TODO allow role to be updated as well

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                throw new ResourcePersistenceException("Failed to update user data within datasource.");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ers_users WHERE id = ?");
            pstmt.setString(1, id);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to delete user from data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

}

