package servlets;

import dao.DAOFactory;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tanya on 3/9/14.
 */
public class LoginServlet extends HttpServlet {
    DAOFactory daof = null;

    @Override
    public void init() throws ServletException {
        daof = DAOFactory.getDAOFactory(1);//2 - XML; 1 - Derby
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = null;

        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");

        UserDAO userDAO = daof.getUserDAO();
        int k = userDAO.insertUser(login, password, "");

        if (k >= 0){
            session = httpServletRequest.getSession(true);
            session.setAttribute("curUser", userDAO.getCurUser());
            session.setAttribute("daof", daof);
            httpServletResponse.sendRedirect("/order.jsp");
        }
        else{
            PrintWriter out = httpServletResponse.getWriter();

            out.print("<html><body>");
            out.print("<h1>Please, try to log in once more</h1>");
            out.print("<h2><a href=\"/index.jsp\" \">Back</a></h2>");
            out.print("</body></html>");
        }
    }

    @Override
    public void destroy() {
        daof.close();
    }
}
