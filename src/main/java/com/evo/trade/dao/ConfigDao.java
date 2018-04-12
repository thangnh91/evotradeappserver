package com.evo.trade.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class ConfigDao {
	public static final int PROPERTY = 0;
	public static final int FILE = 1;
	public static final int MYSQL = 2;
	public static final int POSTGRESQL = 3;
	
	public static int database = PROPERTY;
	
	public ConfigDao() {
		// TODO Auto-generated constructor stub
		try {
			dataSource = dataSource();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(System.getenv("JDBC_DATABASE_URL"));
	}
	

    protected DataSource dataSource;

	public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();
        
        System.out.println("dbUrl=" + dbUrl);
        System.out.println("username=" + username);
        System.out.println("password=" + password);
        
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}
