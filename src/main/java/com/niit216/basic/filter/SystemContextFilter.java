package com.niit216.basic.filter;

import com.niit216.basic.model.SystemRequest;
import com.niit216.basic.threadlocal.FilterQueryHolder;
import com.niit216.basic.threadlocal.SystemRequestHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SystemContextFilter implements Filter {
	private Integer pageSize;

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		Integer offset = 0;
		try {
			offset = Integer.parseInt(req.getParameter("pager.offset"));
		} catch (NumberFormatException e) {}
		try {
			SystemRequest systemRequest =  new SystemRequest();
			systemRequest.setOrder(req.getParameter("order"));
			systemRequest.setPageOffset(offset);
			systemRequest.setPageSize(pageSize);
			systemRequest.setRequest((HttpServletRequest)req);
			systemRequest.setSort(req.getParameter("sort"));
			systemRequest.setIp(req.getRemoteHost());
			SystemRequestHolder.initRequestHolder(systemRequest);
			FilterQueryHolder.setQuery(FilterEntityConditionGenerator.generateQuery((HttpServletRequest)req));
			chain.doFilter(req,resp);
		} finally {
			SystemRequestHolder.remove();
			FilterQueryHolder.remove();
		}
	}

	public void init(FilterConfig cfg) throws ServletException {
		try {
			pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			pageSize = 15;
		}
	}

}
