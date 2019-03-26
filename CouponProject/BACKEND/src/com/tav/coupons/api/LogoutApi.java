package com.tav.coupons.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/logout")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogoutApi {

	@GET
	public void logout (@Context HttpServletRequest request, 
			@Context HttpServletResponse response)  throws Throwable {

		String username = "No username detected";
		
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("companyName") || cookie.getName().equals(("customerEmail"))) {
				username = cookie.getValue();
			}
		}
				
		request.getSession().invalidate();
		
		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setValue(null);
		cookie.setPath(request.getContextPath()); 
		cookie.setMaxAge(0); 
		response.addCookie(cookie);
		
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("LogoutStatus", "User : " +  username + " Logged out successfully");
	}

}
