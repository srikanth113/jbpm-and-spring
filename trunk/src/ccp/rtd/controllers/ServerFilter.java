package ccp.rtd.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

public class ServerFilter extends GenericFilterBean {
	private static final Log log = LogFactory.getLog(ServerFilter.class);

	private String contextAttribute;
	private String targetBeanName;
	private boolean targetFilterLifecycle = false;
	private Filter delegate;
	private final Object delegateMonitor = new Object();

	public void setContextAttribute(String contextAttribute) {
		this.contextAttribute = contextAttribute;
	}

	public String getContextAttribute() {
		return this.contextAttribute;
	}

	public void setTargetBeanName(String targetBeanName) {
		this.targetBeanName = targetBeanName;
	}

	protected String getTargetBeanName() {
		return this.targetBeanName;
	}

	public void setTargetFilterLifecycle(boolean targetFilterLifeCycle) {
		this.targetFilterLifecycle = targetFilterLifeCycle;
	}

	protected boolean isTargetFilterLifecycle() {
		return this.targetFilterLifecycle;
	}

	protected void initFilterBean() throws ServletException {
		if (this.targetBeanName == null) {
			this.targetBeanName = getFilterName();
		}

		synchronized (this.delegateMonitor) {
			WebApplicationContext wac = findWebApplicationContext();
			if (wac != null) {
				this.delegate = initDelegate(wac);
			}
		}
	}

	protected WebApplicationContext findWebApplicationContext() {
		String attrName = getContextAttribute();
		if (attrName != null) {
			return WebApplicationContextUtils.getWebApplicationContext(
					getServletContext(), attrName);
		} else {
			return WebApplicationContextUtils
					.getWebApplicationContext(getServletContext());
		}
	}

	protected Filter initDelegate(WebApplicationContext wac)
			throws ServletException {
		Filter delegate = (Filter) wac.getBean(getTargetBeanName(),
				Filter.class);
		if (isTargetFilterLifecycle()) {
			delegate.init(getFilterConfig());
		}
		return delegate;
	}

	protected void invokeDelegate(Filter delegate, ServletRequest request,
			ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		delegate.doFilter(request, response, filterChain);
	}

	protected void destroyDelegate(Filter delegate) {
		if (isTargetFilterLifecycle()) {
			delegate.destroy();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		// Lazily initialize the delegate if necessary.
		Filter delegateToUse = null;
		synchronized (this.delegateMonitor) {
			if (this.delegate == null) {
				WebApplicationContext wac = findWebApplicationContext();
				if (wac == null) {
					throw new IllegalStateException(
							"No WebApplicationContext found: no ContextLoaderListener registered?");
				}
				this.delegate = initDelegate(wac);
			}
			delegateToUse = this.delegate;
		}

		// Let the delegate perform the actual doFilter operation.
		invokeDelegate(delegateToUse, request, response, filterChain);
	}

	public void destroy() {
		Filter delegateToUse = null;
		synchronized (this.delegateMonitor) {
			delegateToUse = this.delegate;
		}
		if (delegateToUse != null) {
			destroyDelegate(delegateToUse);
		}
	}

}
