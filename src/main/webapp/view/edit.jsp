<%@ page import="Models.Category" %>
<%@ page import="Controller.ATTRIBUTE" %>
<%@ page import="Database.CategoryDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Product" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/12/2020
  Time: 10:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% List<Category> list= CategoryDAO.instance.getAllCategory(); %>
<% Product product= (Product) request.getAttribute("product"); %>
<form action="/edit" method="post">
    <input type="text" name="action" value="confirm" hidden>
    <table border="1">
        <tr>
            <td>Name</td>
            <td>Amount</td>
            <td>Price</td>
            <td>Color</td>
            <td>Description</td>
            <td>Category</td>
        </tr>
        <tr>
            <input type="text" name="<%=ATTRIBUTE.PRODUCT_ID%>" value="<%=product.getProductId()%>" hidden>
            <td><input type="text" name="<%=ATTRIBUTE.NAME%>" value="<%=product.getName()%>"></td>
            <td><input type="text" name="<%=ATTRIBUTE.AMOUNT%>" value="<%=product.getAmount()%>"></td>
            <td><input type="text" name="<%=ATTRIBUTE.PRICE%>" value="<%=product.getPrice()%>"></td>
            <td><input type="text" name="<%=ATTRIBUTE.COLOR%>" value="<%=product.getColor()%>"></td>
            <td><input type="text" name="<%=ATTRIBUTE.DESCRIPTION%>" value="<%=product.getDescription()%>"></td>
            <td><select name="<%=ATTRIBUTE.CATEGORY_ID%>" id="">
                <% for(Category category: list){ %>
                <option value="<%=category.getCategoryId()%>"
                    <%if(product.getCategoryId()==category.getCategoryId()){%>
                        selected="selected"
                    <%}%>
                ><%=category.getCategory()%></option>
                <%}%>
            </select></td>
        </tr>

        <tr><td><button>Save</button></td></tr>
    </table>
</form>

</body>
</html>
