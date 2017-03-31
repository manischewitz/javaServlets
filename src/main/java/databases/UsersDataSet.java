package databases;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="users")
public class UsersDataSet implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "name", unique = true, updatable = true)
	private String name;
	
	@Column(name = "password", unique = false, updatable = true)
	private String password;
	
	@SuppressWarnings("UnusedDeclaration")
	public UsersDataSet(){}
	
	@SuppressWarnings("UnusedDeclaration")
	public UsersDataSet(long id, String name, String password){
		this.setId(id);
		this.setName(name);
		this.password = password;
	}
	
	public UsersDataSet(String name, String password){
		this.setId(-1);
		this.setName(name);
		this.setPassword(password);
	}
	
	@SuppressWarnings("UnusedDeclaration")
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getPassword(){
    	return password;
    }
	
    public void setPassword(String password){
    	this.password = password;
    }
    
    @Override
	public String toString(){
		return "!!!user info!!! Name: "+this.name+" Id: "+this.id+" password: "+this.password;
	}
	
	
}
