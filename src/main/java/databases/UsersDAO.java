package databases;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDAO {

	private Session session;
	
	public UsersDAO (Session session){
		this.session = session;
	}
	
	public UsersDataSet get(long id) throws HibernateException{
		return (UsersDataSet) session.get(UsersDataSet.class, id);
	}
	
	public long getUserId(String name) throws HibernateException{
		Criteria criteria = session.createCriteria(UsersDataSet.class);
		return ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
	}
	
	public long insertUser(String name, String password) throws HibernateException{
		return (long) session.save(new UsersDataSet(name, password));
	}
	
	public UsersDataSet get(String name) throws HibernateException{
		Criteria criteria = session.createCriteria(UsersDataSet.class);
		return ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult());
	}
	
	/*
	OUTDATED
	private Executor executor;
	
	public UsersDAO(Connection connection){
		this.executor = new Executor(connection);
	}
	
	public UsersDataSet getUserById(long id) throws SQLException{
		return executor.query("SELECT * FROM users WHERE id="+id+"", result -> {
			
			result.next();
			return new UsersDataSet(result.getString(2), result.getLong(1));
		});
	}
	
	public long getUserId(String name) throws SQLException{
		
		return executor.query("SELECT * FROM users WHERE user_name='"+name+"'", result -> {
			
			result.next();
			return result.getLong(1);
		});
	}
	
	public void insertUser(String name) throws SQLException{
		executor.update("INSERT INTO users (user_name) VALUES ('"+name+"');");
	}
	
	public void createTable() throws SQLException{
		executor.update("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT,"
				+ "user_name VARCHAR(32), PRIMARY KEY (id))");
	}
	
	public void dropTable() throws SQLException{
		executor.update("DROP TABLE users");
	}*/
}
