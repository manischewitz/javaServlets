package accounts;

public class AccountServerImp implements AccountServer {

	private int usersCount;
    private int usersLimit; 
	
	public AccountServerImp(int usersLimit){
		this.usersLimit = usersLimit;
		this.usersCount = 0;
	}
    
	
	public void addNewUser() {
		this.usersCount++;

	}

	public void removeUser() {
		this.usersCount--;

	}

	
	public int getUsersLimit() {
		
		return this.usersLimit;
	}

	public void setUsersLimit(int usersLimit) {
		this.usersLimit = usersLimit;

	}

	public int getUsersCount() {
		
		return this.usersCount;
	}

}
