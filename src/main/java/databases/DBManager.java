package databases;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.service.ServiceRegistry;

public class DBManager {

	private final SessionFactory sessionFactory;
	
	public DBManager(){
		Configuration configuration = getMySQLConfiguration();
		this.sessionFactory = createSessionFactory(configuration);
	}
	
	private Configuration getMySQLConfiguration(){
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(UsersDataSet.class);
		
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/USERS");
        configuration.setProperty("hibernate.connection.username", "stepicUser");
        configuration.setProperty("hibernate.connection.password", "javaserver");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
	}
	
	public void printConnectionInfo(){
		Session session = sessionFactory.openSession();
			session.doWork(connection1 -> {
				say("DB name: "+connection1.getMetaData().getDatabaseProductName());
				say("DB version: "+connection1.getMetaData().getDatabaseProductVersion());
				say("driver: "+connection1.getMetaData().getDriverName());
				say("Autocommit: "+connection1.getAutoCommit());
			});
		session.close();
	}
	
	private static SessionFactory createSessionFactory(Configuration configuration){
		
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
		ssrb.applySettings(configuration.getProperties());
		ServiceRegistry serviseRegistry = ssrb.build();
		return configuration.buildSessionFactory(serviseRegistry);
	}
	
	public long addUser(String name,String password){
		
		try{
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			UsersDAO dao = new UsersDAO(session);
			long id = dao.insertUser(name,password);
			transaction.commit();
			session.close();
			return id;
		}catch(HibernateException e){e.printStackTrace();}
		return 0;
	}
	
	public UsersDataSet getUser(long id){
		
		try{
			Session session = sessionFactory.openSession();
			UsersDAO dao = new UsersDAO(session);
			UsersDataSet uds = dao.get(id);
			session.close();
			return uds;
		}catch(HibernateException e){e.printStackTrace();}
		return null;
	}
	
	public UsersDataSet getUser(String name){
		try{
			Session session = sessionFactory.openSession();
			UsersDAO dao = new UsersDAO(session);
			UsersDataSet uds = dao.get(name);
			session.close();
			return uds;
		}catch(HibernateException e){e.printStackTrace();}
		return null;
	}
	
	/*
	OUTDATED
	private final Connection connection;
	
	public DBManager(){
		this.connection = getSQLConnection();
	}
	
	public void cleanUP(){
		UsersDAO dao = new UsersDAO(this.connection);
		try{
			dao.dropTable();
		}catch(SQLException sqle){sqle.printStackTrace();}
	}
	
	public long addUser(String name){
		try{
			connection.setAutoCommit(false);
			UsersDAO dao = new UsersDAO(connection);
			dao.createTable();
			dao.insertUser(name);
			connection.commit();
			return dao.getUserId(name);
		}catch(SQLException sqle){
			try {
                connection.rollback();
                sqle.printStackTrace();
            } catch (SQLException e){
            	e.printStackTrace();
            }
		}finally{
			try{
				connection.setAutoCommit(true);
			}catch(SQLException e){e.printStackTrace();}
		}
		return 0;
	}
	
	public UsersDataSet getUser(long id){
		try{
			return new UsersDAO(connection).getUserById(id);
		}catch(SQLException e){e.printStackTrace();}
		
		return null;
	}
	
	public static Connection getSQLConnection(){
		
		try{
			DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
			
			StringBuilder url = new StringBuilder();
			
			url.
			append("jdbc:mysql://").        //db type
			append("localhost:").           //host name
			append("3306/").                //port  
			append("USERS?").               //db name
			append("user=stepicUser&").     //user
			append("password=javaserver");  //password
			
			Connection connection = DriverManager.getConnection(url.toString());
			return connection;
		
		}catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e){e.printStackTrace();}
		
		return null;
	}
	
	
	*/
	private <T> void say(T t){System.out.println(t);}
	
}
