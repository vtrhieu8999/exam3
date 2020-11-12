package Controller;

import Database.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDeleteProduct", urlPatterns="/delete")
public class ServletDeleteProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        if(action==null) action="";
        switch(action){
            case "delete":
                int productId= Integer.parseInt(request.getParameter(ATTRIBUTE.PRODUCT_ID));
                ProductDAO.instance.deleteProduct(productId);
                break;
            default:
                break;
        }
        RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
