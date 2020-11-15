package com.desafio.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafio.rest.config.H2Connection;
import com.desafio.rest.model.Phone;
import com.desafio.rest.model.User;



public class PhoneDao {
	
	public List<Phone> findByUser(int idUser) throws SQLException {
		List<Phone> phones = new ArrayList<Phone>();
		String sql = "SELECT * FROM PHONE p WHERE p.fk = ?";
		Connection conn = H2Connection.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, idUser);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			Phone phone = new Phone();
			phone.setDdd(rs.getInt("ddd"));
			phone.setNumber(rs.getString("number"));
			phone.setType(rs.getString("type"));
			phone.setFk(rs.getInt("fk"));
			phone.setId(rs.getInt("id"));
			phones.add(phone);
		}
		return phones;
		
	}
	
	public User findUserByPhone(int idPhone) {
		return null;
		
	}
	
	public void addPhone(Phone phone, int idUser) throws SQLException {
		Connection conn = H2Connection.getConnection();
		String sql = "INSERT INTO PHONE(ddd,number,type,fk) VALUES(?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, phone.getDdd());
		statement.setString(2, phone.getNumber());
		statement.setString(3, phone.getType());
		statement.setInt(4, idUser);
		statement.execute();
	}
	
	public void editPhoneByUser(int idUser ) throws SQLException {
		Connection conn = H2Connection.getConnection();
		String sql = "UPDATE PHONE p SET = (?,?,?) WHERE p.fk = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, idUser);
		statement.execute();
	}
	
	public void removePhone(int idPhone, int idUser) throws SQLException {
		Connection conn = H2Connection.getConnection();
		String sql = "DELETE PHONE p WHERE p.fk = ? AND id = ? ";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, idUser);
		statement.setInt(2, idPhone);
		statement.execute();
	}
	
	public void removeAllPhones(int idUser) throws SQLException {
		Connection conn = H2Connection.getConnection();
		String sql = "DELETE PHONE p WHERE p.fk = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, idUser);
		statement.execute();
	}

}
