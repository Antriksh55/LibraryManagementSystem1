<%@ include file="header.jsp" %>

<div class="container">
    <h2>Add Book</h2>

    <form action="addBook" method="post">
        <label>Title:</label>
        <input type="text" name="title" required>

        <label>Author:</label>
        <input type="text" name="author" required>

        <button class="btn btn-primary" type="submit">Add</button>
    </form>
</div>
