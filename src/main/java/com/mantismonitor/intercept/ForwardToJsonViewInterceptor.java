package com.mantismonitor.intercept;

import static br.com.caelum.vraptor.view.Results.*;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.ExecuteMethodInterceptor;
import br.com.caelum.vraptor.interceptor.ForwardToDefaultViewInterceptor;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts(after=ExecuteMethodInterceptor.class, before={ForwardToDefaultViewInterceptor.class})
public class ForwardToJsonViewInterceptor implements Interceptor {
	
	private Result result;
	
	public ForwardToJsonViewInterceptor(Result result) {
		this.result = result;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {
		result.use(json()).withoutRoot().from(result.included()).serialize();
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return method.containsAnnotation(Json.class) || method.getResource().getType().isAnnotationPresent(Json.class);
	}

}
