package servlets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import accounts.AccountServer;

public class HomePageServletTest {

	private AccountServer accountServer = mock(AccountServer.class);
	
	private HttpServletResponse getMockedResponse(StringWriter stringWriter) throws IOException{
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		final PrintWriter writer = new PrintWriter(stringWriter);
		
		when(response.getWriter()).thenReturn(writer);
		
		return response;
	}
	
	private HttpServletRequest getMockedRequest(String url){
		HttpSession httpSession = mock(HttpSession.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getSession()).thenReturn(httpSession);
		when(request.getPathInfo()).thenReturn(url);
		
		return request;
	}
	
	@Test
	public void testRemove() throws Exception{
		final StringWriter stringWriter = new StringWriter();
		HttpServletResponse response = getMockedResponse(stringWriter);
		HttpServletRequest request = getMockedRequest(HomePageServlet.PAGE_URL);
		when(request.getParameter("remove")).thenReturn("");
		
		HomePageServlet homePage = new HomePageServlet(accountServer);
		homePage.doGet(request, response);
		
		assertEquals("GoodBye!",stringWriter.toString().trim());
		verify(accountServer, times(1)).removeUser();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
