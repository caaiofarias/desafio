package com.desafio.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.desafio.rest.config.H2Connection;

public class AuthDao {
	
	
	public static Boolean tokenIsValid(String token) throws SQLException {
		boolean result = false;
		Connection conn = H2Connection.getConnection();
		String sql = "SELECT id FROM USER u WHERE u.token = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, token);
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			result = true;
		}
		return result;
		
	}
	
	public static void updateToken(String token, String email) throws SQLException {
		Connection conn = H2Connection.getConnection();
		String sql = "UPDATE USER u SET token = ? WHERE u.email = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, token);
		statement.setString(2, email);
		statement.execute();
		
	}

}
