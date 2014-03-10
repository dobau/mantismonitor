package com.mantismonitor.intercept;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.mantismonitor.controller.HomeController;
import com.mantismonitor.util.UserSession;

@Intercepts
@RequestScoped
public class AuthenticationIntercept implements Interceptor {

	private UserSession userSesison;
	private Result result;
	
	public AuthenticationIntercept(UserSession userSession, Result result) {
		this.userSesison = userSession;
		this.result = result;
	}
	
	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {
		if (method.getResource().getType() == HomeController.class) {
			stack.next(method, resourceInstance);
		} else {
			if (userSesison.islogged()) {
				stack.next(method, resourceInstance);
			} else {
				result.include("error", "User not logged");
				result.redirectTo(HomeController.class).index();
			}			
		}
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return true;
	}
	
}
