package Controller;

import Database.ProductDAO;
import Models.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletCreateProduct", urlPatterns = "/create")
public class ServletCreateProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        if(action==null) action="";
        switch (action){
            case "create":
                //    name, price, amount, color, description, categoryId
                String name= request.getParameter(ATTRIBUTE.NAME);
                int categoryId= Integer.parseInt(request.getParameter(ATTRIBUTE.CATEGORY_ID));
                int price= Integer.parseInt(request.getParameter(ATTRIBUTE.PRICE));
                int amount= Integer.parseInt(request.getParameter(ATTRIBUTE.AMOUNT));
                String color= request.getParameter(ATTRIBUTE.COLOR);
                String description=request.getParameter(ATTRIBUTE.DESCRIPTION);
                ProductDAO.instance.addProduct(new Product(-1, name, price, amount, color, description, categoryId));
                break;
            default:
                break;
        }
        RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher= request.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(request, response);
    }


}
