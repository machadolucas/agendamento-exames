package br.com.agendamento.login;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agendamento.util.LoginHelper;

/**
 * Classe filtro para login de usuário no sistema
 * 
 * @author lucas.machado
 * 
 */
public class LoginFilter implements Serializable, Filter {

	private static final long serialVersionUID = -4949771056732781272L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		LoginHelper loginHelper = (LoginHelper) req.getSession().getAttribute(
				"loginHelper");

		if (loginHelper == null || !loginHelper.isLoggedIn()) {
			// Não há usuário logado na sessão... redirecionar parar Login
			res.sendRedirect(req.getContextPath() + "/login.jsf");
		} else {
			// Continuar para redirecionamento...
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
