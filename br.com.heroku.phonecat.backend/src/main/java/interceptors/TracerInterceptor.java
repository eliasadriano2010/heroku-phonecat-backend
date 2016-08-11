package interceptors;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
@RequestScoped
public class TracerInterceptor implements Interceptor {

	private final HttpServletRequest request;

	public TracerInterceptor(HttpServletRequest request) {
		this.request = request;
	}

	/*
	 * Este interceptor deve interceptar o method dado? Neste caso vai
	 * interceptar todos os métodos. method.getMethod() retorna o método java
	 * que está sendo executado method.getResourceClass().getType() retorna a
	 * classe que está sendo executada
	 */
	public boolean accepts(ResourceMethod method) {
		return true;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		
		System.out.println("Running >>>>>> Controller Method: " + request.getRequestURI() + ">>>> Request Method:" + request.getMethod() + "\n");
		// código a ser executado antes da lógica

		stack.next(method, resourceInstance); // continua a execução

		System.out.println("Leaving >>>>>> Controller Method: " + request.getRequestURI() + ">>>> Request Method:" + request.getMethod());
		
		// código a ser executádo depois da lógica
	}

}