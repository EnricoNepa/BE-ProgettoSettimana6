package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import it.business.IntestatarioEJB;

import it.data.ContoCorrenteDAO;

/**
 * Servlet implementation class ServletVersare
 */
public class ServletVersare extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;
	
	@EJB
    IntestatarioEJB contoService;
    public ServletVersare() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Long id = Long.valueOf(request.getParameter("numero"));
		int PIN = Integer.valueOf(request.getParameter("pin"));
		Double versa = Double.valueOf(request.getParameter("versa"));
		
		if(contoService.controllaOperazione("versamento", id, PIN, versa)) {
		contoService.versa(id, PIN, versa);
		message = "<!DOCTYPE html>" +
	  			  "<html><head><link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">" +
	  			  "<title>Saldo</title>" +
	  			  "</head><body>";
			message += "<div class=\"container col-md-4 mt-5 col-md-offset-4 justify-content-center text-center\"><div class=\"card\" style=\"width: 18rem;\">\r\n"
					+ "  "
					+ "  <div class=\"card-body\">\r\n"
					+ "    <h5 class=\"card-title h1\">OPERAZIONE AVVENUTA!</h5>\r\n"
					+ "    <p class=\"card-text h2 text-info\">Versamento di "+ versa +" euro effettuato!</p>\r\n"    
					+ "  </div>\r\n"
					+ "</div></div>";
		} else {
			message = "<!DOCTYPE html>" +
	  			  "<html><head><link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">" +
	  			  "<title>Saldo</title>" +
	  			  "</head><body>";
			message += "<div class=\"container col-md-4 mt-5 col-md-offset-4 justify-content-center text-center\"><div class=\"card\" style=\"width: 18rem;\">\r\n"
					+ "  "
					+ "  <div class=\"card-body\">\r\n"
					+ "    <h5 class=\"card-title h1\">Errore!</h5>\r\n"
					+ "    <p class=\"card-text h2 text-info\">Errore nel versamento!</p>\r\n"    
					+ "  </div>\r\n"
					+ "</div></div>";
			}
		
		
		
		out.println(message);
	}
		
}


