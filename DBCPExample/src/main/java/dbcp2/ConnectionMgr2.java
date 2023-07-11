package dbcp2;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionMgr2 {
    /*
    private static final String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521:orcl";
    private static final String className = "oracle.jdbc.driver.OracleDriver";
    private static final String userName = "dbpro";
    private static final String password = "dbpro";
    --> DB ���� ������ context.properties ���Ͽ� ���� �� �ε�
    */
	private static DataSource ds = null;
    	
    public ConnectionMgr2() {
		InputStream input = null;
    	Properties prop = new Properties();

		try {
			input = getClass().getResourceAsStream("/context.properties");
			prop.load(input);			// load the properties file
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		
		try {
    		// DataSource ���� �� ����
			BasicDataSource bds = new BasicDataSource();
	        bds.setDriverClassName(prop.getProperty("db.driver"));
	        bds.setUrl(prop.getProperty("db.url"));
	        bds.setUsername(prop.getProperty("db.username"));
	        bds.setPassword(prop.getProperty("db.password"));   
	        
	        bds.setMaxTotal(10);
			bds.setInitialSize(10);
			bds.setMinIdle(5);
			bds.setMaxIdle(10);
			bds.setMaxWaitMillis(5000);
			
			ds = bds;
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} 	   
    }

    public Connection getConnection() {
    	Connection conn = null;
    	try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
    }
    
    public void close() {
		BasicDataSource bds = (BasicDataSource) ds;
		try {
			bds.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// ���� Ȱ��ȭ ������ Connection �� ������ ��Ȱ��ȭ ������ Connection ���� ���
	public void printDataSourceStats() {
		try {
			BasicDataSource bds = (BasicDataSource) ds;
			System.out.println("NumActive: " + bds.getNumActive());
			System.out.println("NumIdle: " + bds.getNumIdle());
		} catch (Exception ex) {
			ex.printStackTrace();
		}   
	}
}
