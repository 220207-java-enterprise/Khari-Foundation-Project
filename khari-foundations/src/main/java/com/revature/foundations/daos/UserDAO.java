package com.revature.foundations.daos;

import com.revature.foundations.models.User;
import com.revature.foundations.models.UserRole;
import com.revature.foundations.util.ConnectionFactory;
import com.revature.foundations.util.exceptions.DataSourceException;
import com.revature.foundations.util.exceptions.ResourcePersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {

    private final String rootSelect = "SELECT " +
            "au.User_id, au.Given_name, au.surname, au.email, au.username, au.password, au.Role_id, ur.role, au.is_active " +
            "FROM ers_users au " +
            "JOIN ers_user_roles ur " +
            "ON au.role_id = ur.role_id ";

    public User findUserByUsername(String username) {

        User user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getString("User_id"));
                user.setGiven_name(rs.getString("Given_name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(new UserRole(rs.getString("Role_id"), rs.getString("Role_id")));
                user.setIs_active(rs.getBoolean("is_active"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findUserByEmail(String email) {

        User user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE email = ?");
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getString("User_id"));
                user.setGiven_name(rs.getString("Given_name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(new UserRole(rs.getString("Role_id"), rs.getString("Role_id")));
                user.setIs_active(rs.getBoolean("is_active"));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }

    public User findUsersByUsernameAndPassword(String username, String password) {

        User authUser = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                authUser = new User();
                authUser.setUser_id(rs.getString("user_id"));
                authUser.setGiven_name(rs.getString("given_name"));
                authUser.setSurname(rs.getString("surname"));
                authUser.setEmail(rs.getString("email"));
                authUser.setUsername(rs.getString("username"));
                authUser.setPassword(rs.getString("password"));
                authUser.setRole(new UserRole(rs.getString("role_id"), rs.getString("role")));
                authUser.setIs_active(rs.getBoolean("is_active"));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return authUser;
    }

    @Override
    public void save(User newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ers_users VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newUser.getUser_id());
            pstmt.setString(2, newUser.getGiven_name());
            pstmt.setString(3, newUser.getSurname());
            pstmt.setString(4, newUser.getEmail());
            pstmt.setString(5, newUser.getUsername());
            pstmt.setString(6, newUser.getPassword());
            pstmt.setString(7, newUser.getRole().getRole_id());

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
    public User getById(String id) {

        User user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE User_id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getString("setUser_id"));
                user.setGiven_name(rs.getString("Given_name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(new UserRole(rs.getString("Role_id"), rs.getString("Role_id")));
                user.setIs_active(rs.getBoolean("is_active"));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }


    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.createStatement().executeQuery(rootSelect);
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getString("user_id"));
                user.setGiven_name(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(new UserRole(rs.getString("role_id"), rs.getString("role_id")));
                user.setIs_active(rs.getBoolean("is_active"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return users;
    }

    @Override
    public void update(User updatedUser) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ers_users " +
                    "SET Given_name = ?, " +
                    "Surname = ?, " +
                    "email = ?, " +
                    "username = ?, " +
                    "password = ? " +
                    "WHERE User_id = ?");
            pstmt.setString(1, updatedUser.getGiven_name());
            pstmt.setString(2, updatedUser.getSurname());
            pstmt.setString(3, updatedUser.getEmail());
            pstmt.setString(4, updatedUser.getUsername());
            pstmt.setString(5, updatedUser.getPassword());
            pstmt.setString(6, updatedUser.getUser_id());

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

