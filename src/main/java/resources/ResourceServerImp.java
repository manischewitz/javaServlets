package resources;

public class ResourceServerImp implements ResourceServer {

	TestResource testResource;
	
	public ResourceServerImp(TestResource testResource){
		this.testResource = testResource;
	}
	
	@Override
	public int getAge() {
		return testResource.getAge();
	}

	@Override
	public String getName() {
		return testResource.getName();
	}
}
