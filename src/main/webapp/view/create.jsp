<%@ page import="Controller.ATTRIBUTE" %>
<%@ page import="Models.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="Database.CategoryDAO" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/12/2020
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<%--private String name;--%>
<%--private int amount;--%>
<%--private int price;--%>
<%--private String color;--%>
<%--private String description;--%>
<%--private int categoryId;--%>
<body>
<% List<Category> list= CategoryDAO.instance.getAllCategory(); %>
<form action="/create" method="post">
    <input type="text" name="action" value="create" hidden>
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
            <td><input type="text" name="<%=ATTRIBUTE.NAME%>"></td>
            <td><input type="text" name="<%=ATTRIBUTE.AMOUNT%>"></td>
            <td><input type="text" name="<%=ATTRIBUTE.PRICE%>"></td>
            <td><input type="text" name="<%=ATTRIBUTE.COLOR%>"></td>
            <td><input type="text" name="<%=ATTRIBUTE.DESCRIPTION%>"></td>
            <td><select name="<%=ATTRIBUTE.CATEGORY_ID%>" id="">
                <% for(Category category: list){ %>
                <option value="<%=category.getCategoryId()%>"><%=category.getCategory()%></option>
                <%}%>
            </select></td>
        </tr>

        <tr><td><button>Save</button></td></tr>
    </table>
</form>

</body>
</html>
