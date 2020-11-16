package com.desafio.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafio.rest.config.H2Connection;
import com.desafio.rest.model.Token;

public class TokenDao {
	
	public void addToken(Token token ) throws SQLException {
        Connection conn = H2Connection.getConnection();
        String sql = "INSERT INTO TOKEN(value,fk) VALUES(?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, token.getValue());
        statement.setInt(2, token.getFk());
        statement.execute();
    }
	
	public List<Token> findAll() throws SQLException {
		List<Token> tokens = new ArrayList<>();
		Connection conn = H2Connection.getConnection();
		String sql = "SELECT * FROM TOKEN";
		PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
        	Token token = new Token(rs.getString("value"), rs.getInt("fk"));
        	tokens.add(token);
        }
        
        return tokens;
	}
	
	public int findOneByIdUser(int idUser) throws SQLException {
		int id = -1;
		Connection conn = H2Connection.getConnection();
		String sql = "SELECT id FROM TOKEN WHERE FK = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, idUser);
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			id = rs.getInt("id");
		}
		return id;
		
	}
	
	public void editToken(int idToken, String value) throws SQLException {
		Connection conn = H2Connection.getConnection();
		String sql = "UPDATE TOKEN SET value = ? where id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, value);
		statement.setInt(2, idToken);
		statement.executeUpdate();
	}
	
	public static Boolean tokenIsValid(String token) throws SQLException {
	        boolean result = false;
	        Connection conn = H2Connection.getConnection();
	        String sql = "SELECT * FROM USER u WHERE u.id = (SELECT t.id FROM TOKEN t where t.value = ?)";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, token);
	        ResultSet rs = statement.executeQuery();
	        if(rs.next()) {
	        	result = true;
	        }
	        return result;
	 }

}
