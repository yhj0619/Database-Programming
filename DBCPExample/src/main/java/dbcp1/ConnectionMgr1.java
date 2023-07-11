package dbcp1;

import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource; // DBCP ���� Ŭ���� import

public class ConnectionMgr1 {
	
	private BasicDataSource ds = null;		// DBCP ��ü ����

	// BasicDataSource �κ��� connection �� ���´�.
	public Connection getConnection() throws SQLException {
		setupDataSource();
		return ds.getConnection(); 				// DataSource�κ��� connection ȹ��
	}
	
	private void setupDataSource() {
		if (ds == null) {
			
			String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521:orcl";
			String className = "oracle.jdbc.driver.OracleDriver";
			String userName = "dbpro";
			String passWord = "dbpro";
			
			ds = new BasicDataSource();
			
			ds.setDriverClassName(className); 	// JDBC ����̹� ����
			ds.setUrl(url); 					// DBMS ���� URL ����
			ds.setUsername(userName); 			// DBMS ����� ����
			ds.setPassword(passWord);			// DBMS ����� ��ȣ ����
			
			ds.setMaxTotal(10);
			ds.setInitialSize(10);
			ds.setMinIdle(5);
			ds.setMaxIdle(10);
			ds.setMaxWaitMillis(5000);
			ds.setPoolPreparedStatements(true); // Statement pooling ���
		}
	}
}	