<%@ page import="java.util.*,com.lms.model.Book" %>
<%@ include file="header.jsp" %>

<div class="container">
    <h2>Available Books</h2>

    <div class="grid">
        <%
            List<Book> books = (List<Book>) request.getAttribute("books");
            for (Book b : books) {
        %>
        <div class="card">
            <h3><%= b.getTitle() %></h3>
            <p><%= b.getAuthor() %></p>
            <span class="status available">Available</span>
        </div>
        <% } %>
    </div>
</div>
