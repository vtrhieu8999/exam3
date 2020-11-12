<%@ page import="Models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="Database.ProductDAO" %>
<%@ page import="Database.CategoryDAO" %>
<%@ page import="Controller.ATTRIBUTE" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/12/2020
  Time: 8:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%List<Product> productList= ProductDAO.instance.getAllProduct(); %>
  <head>
    <title>Select Option</title>
  </head>
  <body>
  <a href="view/create.jsp"><h1>Create</h1></a>
  
  <% for(Product product: productList){ %>
  <table border="1">
    <tr>
      <td>Name</td>
      <td>Amount</td>
      <td>Color</td>
      <td>Description</td>
      <td>Price</td>
      <td>Category</td>
    </tr>
    <tr>
      <td><%=product.getName()%></td>
      <td><%=product.getAmount()%></td>
      <td><%=product.getColor()%></td>
      <td><%=product.getDescription()%></td>
      <td><%=product.getPrice()%></td>
      <td><%=CategoryDAO.instance.getCategoryById(product.getCategoryId()).getCategory()%></td>
      <td>
        <form action="/delete" method="post">
          <input type="text" name="action" value="delete" hidden>
          <input type="text" name="<%=ATTRIBUTE.PRODUCT_ID%>" value="<%=product.getProductId()%>" hidden>
          <button>Delete</button>
        </form>
      </td>
      <td>
        <form action="/edit" method="post">
          <input type="text" name="action" value="edit" hidden>
          <input type="text" name="<%=ATTRIBUTE.PRODUCT_ID%>" value="<%=product.getProductId()%>" hidden>
          <button>Edit</button>
        </form>
      </td>
    </tr>
  </table>
  
  
  <%}%>

  </body>
</html>
