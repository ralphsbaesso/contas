package web.viewhelper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Entidade;

public abstract class AbstractVH implements IViewHelper {

	// atributos
	protected String operacao;
	protected Entidade entidade;

}
