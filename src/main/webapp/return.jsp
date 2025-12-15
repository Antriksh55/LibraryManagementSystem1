<%@ include file="header.jsp" %>

<div class="container">
    <h2>Return a Book</h2>

    <% if ("1".equals(request.getParameter("error"))) { %>
        <div class="error">Return failed! Book was not borrowed.</div>
    <% } %>

    <form action="returnBook" method="post">
        <label>Book ID:</label>
        <input type="number" name="id" required>

        <button class="btn btn-primary" type="submit">Return</button>
    </form>
</div>
