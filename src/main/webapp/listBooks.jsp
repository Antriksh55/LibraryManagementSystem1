<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.lms.model.Book" %>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    if (books == null) {
        books = new ArrayList<>();
    }
%>

<jsp:include page="header.jsp" />

<div class="page-container">

    <div class="page-header">
        <h1>📚 All Books</h1>
        <p>Complete library collection • <%= books.size() %> books</p>
    </div>

    <div class="glass-table">
        <table>
            <thead>
                <tr>
                    <th>Book ID</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Status</th>
                    <th>Borrower</th>
                </tr>
            </thead>
            <tbody>
            <% for (Book b : books) { %>
                <tr>
                    <td>#<%= b.getId() %></td>
                    <td class="title"><%= b.getTitle() %></td>
                    <td><%= b.getAuthor() %></td>
                    <td>
                        <% if (b.isBorrowed()) { %>
                            <span class="badge borrowed">Borrowed</span>
                        <% } else { %>
                            <span class="badge available">Available</span>
                        <% } %>
                    </td>
                    <td>
                        <%= b.isBorrowed() ? "Issued" : "—" %>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>

</div>

