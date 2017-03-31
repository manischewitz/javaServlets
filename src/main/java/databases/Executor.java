package databases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {

	private final Connection connection;
	
	public Executor(Connection conn){
		this.connection = conn;
	}
	
	public void update(String update) throws SQLException{
		
		Statement satatement = connection.createStatement();
		satatement.execute(update);
		satatement.close();
		
	}
	
	public <T> T query(String query, ResultHandler<T> handle) throws SQLException{
		
		Statement statement = connection.createStatement();
		statement.execute(query);
		ResultSet result = statement.getResultSet();
		T val = handle.handle(result);
		result.close();
		statement.close();
		
		return val;
	}
}
