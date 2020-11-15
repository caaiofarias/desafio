package com.desafio.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.desafio.rest.config.H2Connection;
import com.desafio.rest.model.Phone;
import com.desafio.rest.model.Session;
import com.desafio.rest.model.User;


public class UserDao {
	
	
	public int logIn(String email, String password) throws SQLException {
		Connection conn = H2Connection.getConnection();
		Session.instance.setValue("name", "admin");
		int id = -1;
		String name = null;
		String sql = "SELECT id,name from USER u WHERE u.email = ? AND u.password = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, email);
		statement.setString(2, password);
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			id = rs.getInt("id");
			name = rs.getString("name"); 
		}
		return id;
		
	}
	
	public List<User> findAll() throws SQLException {
		List<User> list = new ArrayList<>();
		List<Phone> phones = new ArrayList<Phone>();
		PhoneDao phoneDao = new PhoneDao();
		String sql = "SELECT * FROM USER";
		Connection conn = H2Connection.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			phones = phoneDao.findByUser(user.getId());
			user.setPhones(phones);
			System.out.println(phones.size());
			list.add(user);
		}
		conn.close();
		return list;
	}
	
	public User findOne(int id) throws SQLException {
		User user = null;
		List<Phone> phones = new ArrayList<Phone>();
		PhoneDao phoneDao = new PhoneDao();
		String sql = "SELECT * FROM USER WHERE id = ?";
		Connection conn = H2Connection.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			phones = phoneDao.findByUser(user.getId());
			user.setPhones(phones);
		}
		return user;
	}
	
	public User findOne(String email) throws SQLException {
		User user = null;
		String sql = "SELECT * FROM USER WHERE email = ?";
		Connection conn = H2Connection.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, email);
		ResultSet rs = statement.executeQuery();
		
		if(rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("name"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		return user;
	}
	
	public int addUser(User user) throws SQLException {
		Connection conn = H2Connection.getConnection();
		int last_inserted_id = -1;
		String sql = "INSERT INTO USER(name,password,email) VALUES(?,?,?)";
		PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, user.getName());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getEmail());
		statement.execute();
		ResultSet rs = statement.getGeneratedKeys();
		if(rs.next())
        {
            last_inserted_id = rs.getInt(1);
        }
		statement.close();
		return last_inserted_id;
	}
	
	public void editUser(User user) throws SQLException {
		Connection conn = H2Connection.getConnection();
		String sql = "UPDATE USER SET name = ?, email = ?, password = ? WHERE USER.email = ? ";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getEmail());
		statement.setString(3, user.getPassword());
		statement.setString(4, user.getEmail());
		statement.execute();
	}
	
	public void removeUser(int id) throws SQLException {
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.removeAllPhones(id);
		Connection conn = H2Connection.getConnection();
		String sql = "DELETE FROM USER WHERE id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		statement.execute();
	}
}
