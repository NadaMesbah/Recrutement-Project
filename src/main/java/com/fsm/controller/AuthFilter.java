package com.fsm.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter({"/ServletOffreModifier", "/ServletOffreAjouter", 
        "/ServletOffreSupprimer", "/postuler", "/postulations","/MesPostulations","/editPostulation", "/deletePostulation", "/AdminPostulation/*"})
public class AuthFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public AuthFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =(HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		boolean Authenticated =false;
		if ( req.getSession().getAttribute("Authenticated")!=null) {
			
			Authenticated = (boolean) req.getSession().getAttribute("Authenticated");
		}
		System.out.println(Authenticated);
		if(!Authenticated) {
		res.sendRedirect(((HttpServletRequest) request).getContextPath() + "/login");
		return;}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
