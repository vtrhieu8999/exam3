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

@WebServlet(name = "ServletUpdateProduct", urlPatterns = "/edit")
public class ServletUpdateProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //    name, price, amount, color, description, categoryId
        String action= request.getParameter("action");
        int productId;
        Product product;
        RequestDispatcher dispatcher;
        if(action==null) action="";
        switch (action){
            case "edit":
                productId= Integer.parseInt(request.getParameter(ATTRIBUTE.PRODUCT_ID));
                product= ProductDAO.instance.selectProductById(productId);
                request.setAttribute("product", product);
                dispatcher= request.getRequestDispatcher("view/edit.jsp");
                dispatcher.forward(request, response);

                break;
            case "confirm":
                productId= Integer.parseInt(request.getParameter(ATTRIBUTE.PRODUCT_ID));
                String name= request.getParameter(ATTRIBUTE.NAME);
                int price= Integer.parseInt(request.getParameter(ATTRIBUTE.PRICE));
                int amount= Integer.parseInt(request.getParameter(ATTRIBUTE.AMOUNT));
                String color= request.getParameter(ATTRIBUTE.COLOR);
                String description= request.getParameter(ATTRIBUTE.DESCRIPTION);
                int categoryId= Integer.parseInt(request.getParameter(ATTRIBUTE.CATEGORY_ID));
                product= new Product(productId, name, amount, price, color, description, categoryId);
                ProductDAO.instance.updateProduct(product);
                dispatcher= request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
