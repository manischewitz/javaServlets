package resources;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;



public class ResourcesHelper {

	private final static MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	
	public static void newMBean(String objectName, Object object) throws Exception{
		mbs.registerMBean(object, new ObjectName(objectName));
	}
}

