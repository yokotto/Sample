package filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class FilterSample implements Filter {
	public void init(FilterConfig fConfig) throws ServletException { }
	public void doFilter(ServletRequest request,
		ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
	request.setCharacterEncoding("UTF-8");
	chain.doFilter(request, response);
	}
	public void destroy() { }
}