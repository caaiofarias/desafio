package com.desafio.rest;

import java.sql.Connection;
import java.sql.SQLException;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.desafio.rest.config.H2Connection;
import com.desafio.rest.provider.AuthenticationFilter;

public class CustomApplication extends ResourceConfig 
{
	public CustomApplication()
	{
		packages("com.desafio.rest");
		register(LoggingFilter.class);
		register(AuthenticationFilter.class);
		Connection conn = H2Connection.getConnection();
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
