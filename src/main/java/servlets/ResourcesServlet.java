package servlets;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resources.ResourceServer;
import resources.TestResourceServerControllerMBean;
import resources.ResourceServerImp;
import resources.ResourcesHelper;
import resources.TestResource;
import resources.TestResourceServerController;
import sax.ReadSAXXMLFile;

@SuppressWarnings("serial")
public class ResourcesServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getParameter("path");
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		
		if(path != null && Files.exists(Paths.get(path))){
			
			ResourceServer resourceServer = new ResourceServerImp((TestResource) ReadSAXXMLFile.readXML(path));
			TestResourceServerControllerMBean rscMBean = new TestResourceServerController(resourceServer);
			try {
				ResourcesHelper.newMBean("Admin:type=ResourceServerController", rscMBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
