package com.tav.coupons.filters;

import java.io.IOException;  

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/rest/loggedin/*")
public class LoginFilter implements Filter {


    private static final String RESOURCE_PATH = "/unsecured";

	public void destroy() {
		// TODO Auto-generated method stub
	}

// ------------------------------ The method that actually filters-------------------------------------

	// This method makes sure that only a logged-in user can access certain URLs
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String path = ((HttpServletRequest) request).getPathInfo();
		
        if (path.contains(RESOURCE_PATH)) {
            chain.doFilter(request, response);
            return;
        }
        
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (req.getSession(false) != null) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletResponse res = (HttpServletResponse) response;
		res.setStatus(401);
		res.setHeader("ErrorCause", "Couldn't find a login session");
	}
	
// ---------------------------------------------------------------------------------------------------

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
