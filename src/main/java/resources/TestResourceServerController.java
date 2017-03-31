package resources;

public class TestResourceServerController implements TestResourceServerControllerMBean{

	private final ResourceServer resServer;
	
	public TestResourceServerController(ResourceServer resServer){
		this.resServer = resServer;
	}
	
	@Override
	public int getAge() {
		return resServer.getAge();
	}

	@Override
	public String getName() {
		return resServer.getName();
	}

}
