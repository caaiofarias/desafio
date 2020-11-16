package com.desafio.rest.dao;

import com.desafio.rest.config.H2Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthDao {


    public static Boolean tokenIsValid(String token) throws SQLException {
        boolean result = false;
        Connection conn = H2Connection.getConnection();
        String sql = "SELECT * FROM USER u WHERE u.token = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, token);
        result = statement.execute();
        return result;

    }

    public static void updateToken(String token, String email) throws SQLException {
        Connection conn = H2Connection.getConnection();
        String sql = "UPDATE USER u SET token = ? WHERE u.email = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, token);
        statement.setString(2, email);
        int rs = statement.executeUpdate();
    }

}

