package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuari;
import model.UsuariDAO;
import model.registremovimentsDAO;

@WebServlet("/login")
public class login extends HttpServlet {

	// metodo encargado de la gestión del método POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session;
		System.out.println("servlet in");
		UsuariDAO uDAO = new UsuariDAO();
		Usuari usu = new Usuari();
		String nif;
		String pass;
		nif = request.getParameter("dni");
		pass = request.getParameter("password");

		List<String> valors = new ArrayList<String>();

		usu.setNIF(nif);
		usu.setPasswd(pass);

		session = request.getSession();

		// deberíamos buscar el usuario en la base de datos, pero dado que se
		// escapa de este tema, ponemos un ejemplo en el mismo código

		registremovimentsDAO rmDAO = new registremovimentsDAO();
		String hafet = "Login";

		try {
			rmDAO.desaMoviment(nif, hafet);
			List<String> llista = uDAO.validarLogin(usu);
			if (nif.equals(llista.get(0)) && pass.equals(llista.get(1))) {

				valors = uDAO.valorsUsuari(nif, pass);
				usu.setNom(valors.get(0));
				usu.setPermis(Integer.parseInt(valors.get(1)));

				// Expirar en 30 min
				session.setMaxInactiveInterval(30 * 60);

				session.setAttribute("nif", usu.getNIF());
				session.setAttribute("permis", usu.getPermis());

				uDAO.tancarConn();
				// redirijo a página con información de login exitoso
				response.sendRedirect("index.jsp");

			}
		} catch (Exception e) {
			System.out.println("Error session add: " + e);
			session.setAttribute("correcte", 1);
			response.sendRedirect("login.jsp");
		}

	}

}
