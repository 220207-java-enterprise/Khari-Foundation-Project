package com.revature.foundations.daos;

import com.revature.foundations.models.UserRole;
import com.revature.foundations.util.ConnectionFactory;
import com.revature.foundations.util.exceptions.DataSourceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRolesDAO implements CrudDAO<UserRole>{

    private final String rootCall = "SELECT * FROM ERS_USER_ROLES";

    @Override
    public UserRole getById(String role_ID) {
        UserRole myRole = null;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(rootCall + " WHERE ROLE = ?");
            pstmt.setString(1, role_ID);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                myRole = new UserRole();
                myRole.setRole_id(rs.getString("ROLE_ID"));
                myRole.setRole(rs.getString("ROLE"));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return myRole;
    }


    public ArrayList<UserRole> getAll() {
        ArrayList<UserRole> myRoles = new ArrayList<>();
        UserRole myRole = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.createStatement().executeQuery(rootCall);
            while(rs.next()) {
                myRole = new UserRole();
                myRole.setRole_id(rs.getString("ROLE_ID"));
                myRole.setRole(rs.getString("ROLE"));

                myRoles.add(myRole);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return myRoles;
    }

    @Override
    public void save(UserRole newObject) {

    }

    @Override
    public void update(UserRole updatedObject) {

    }

    @Override
    public void deleteById(String id) {

    }
}

