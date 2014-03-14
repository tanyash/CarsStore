package servlets;

import dao.CarDAO;
import dao.DAOFactory;
import dao.OrderDAO;
import store.Car;
import store.Order;
import store.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by tanya on 3/9/14.
 */
public class OrderServlet extends HttpServlet {
    DAOFactory daof = null;

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null){
            daof = (DAOFactory)session.getAttribute("daof");
            User curUser = (User)session.getAttribute("curUser");

            OrderDAO orderDAO = (OrderDAO)session.getAttribute("orderDAO");
            CarDAO carDAO = daof.getCarDAO();

            Car car = carDAO.insertCar(httpServletRequest.getParameter("brand"),
                    httpServletRequest.getParameter("model"),
                    Integer.valueOf(httpServletRequest.getParameter("year")));

            int k = orderDAO.insertOrder(curUser, car);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/order.jsp");
            rd.forward(httpServletRequest, httpServletResponse);
           //httpServletResponse.sendRedirect("/order.jsp");

        }
        else{
            httpServletResponse.sendRedirect("/index.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null){
            daof = (DAOFactory)session.getAttribute("daof");
            User curUser = (User)session.getAttribute("curUser");

            OrderDAO orderDAO = (OrderDAO)session.getAttribute("orderDAO");
            List<Order> uOrders= orderDAO.findAllUserOrders(curUser);

            for (int i = 0; i < uOrders.size(); i++){
                if (httpServletRequest.getParameter(String.valueOf(i)) != null){
                    orderDAO.deleteOrder(uOrders.get(i));
                }
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/order.jsp");
            rd.forward(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void destroy() {
        daof.close();
    }
}
