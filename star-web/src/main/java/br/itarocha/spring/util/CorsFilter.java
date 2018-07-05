package br.itarocha.spring.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		final HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			System.out.println("**************************** Sending Header....");
			//LOG.trace("Sending Header....");
			// CORS "pre-flight" request
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//			response.addHeader("Access-Control-Allow-Headers", "Authorization");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			response.addHeader("Access-Control-Max-Age", "1");
		}
		
		filterChain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
	}
}
