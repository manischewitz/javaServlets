
import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.logging.log4j.*;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import accounts.AccountServer;
import accounts.AccountServerController;
import accounts.AccountServerControllerMBean;
import accounts.AccountServerImp;
import chat.ChatServlet;
import chat.ChatWebSocket;
import databases.DBManager;
import resources.ResourceServer;
import resources.TestResourceServerControllerMBean;
import resources.ResourceServerImp;
import resources.ResourcesHelper;
import resources.TestResource;
import resources.TestResourceServerController;
import sax.ReadSAXXMLFile;
import servlets.AdminPageServlet;
import servlets.HomePageServlet;
import servlets.MainPageServlet;
import servlets.MirrorPageServlet;
import servlets.ResourcesServlet;
import servlets.SessionsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;




public class Main {

	static final Logger logger = LogManager.getLogger(Main.class.getName());
	
	
	public static void main(String[] args) throws Exception{
		
		if(args.length != 1){
			logger.error("Port as first argument");
			System.exit(1);
		}
		
		String stringPort = args[0];
		int port = Integer.valueOf(stringPort);
		
		logger.info("Starting at http://127.0.0.1:"+stringPort);
		
		AccountServer accountServer = new AccountServerImp(10);
		AccountServerControllerMBean serverStats = new AccountServerController(accountServer);
		ResourcesHelper.newMBean("Admin:type=AccountServerImp.usersLimit", serverStats);
		
		DBManager dbmanager = new DBManager();
		dbmanager.printConnectionInfo();
		
		ServletContextHandler sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
		
		sch.addServlet(new ServletHolder(new MainPageServlet()), "/main");
		sch.addServlet(new ServletHolder(new MirrorPageServlet()), "/mirror");
		sch.addServlet(new ServletHolder(new ResourcesServlet()), "/resources");
		sch.addServlet(new ServletHolder(new SignUpServlet()), "/signup");
		sch.addServlet(new ServletHolder(new SignInServlet()), "/signin");
		sch.addServlet(new ServletHolder(new ChatServlet()), "/chat");
		sch.addServlet(new ServletHolder(new HomePageServlet(accountServer)), HomePageServlet.PAGE_URL);
		sch.addServlet(new ServletHolder(new AdminPageServlet(accountServer)), AdminPageServlet.PAGE_URL);

		Server server = new Server(port);
		
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("/templates/homePage");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, sch});
        server.setHandler(handlers);
		
		server.start();
		logger.info("Server started");
		server.join();
	}
	
	

}













