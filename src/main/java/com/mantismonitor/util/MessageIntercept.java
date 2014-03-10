package com.mantismonitor.util;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class MessageIntercept implements Interceptor {
	
	private Result result;
	
	public MessageIntercept(Result result) {
		this.result = result;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {
		stack.next(method, resourceInstance);
		result.include("error", "User not logged");
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return method != null;
	}

}
