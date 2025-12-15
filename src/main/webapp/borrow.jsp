<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.lms.model.Book" %>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    if (books == null) {
        books = new ArrayList<>();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Borrow a Book</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="dark-bg">

<jsp:include page="header.jsp" />

<div class="container center">
        <div>
            <h2 class="title">📚 Borrow a Book</h2>
        </div>
    <div class="card glass">
        <form action="borrow" method="post">

            <!-- BOOK DROPDOWN -->
            <label>Select Book</label>
            <select name="id" id="bookSelect" required>
                <option value="">Select a book...</option>

                <% for (Book b : books) { %>
                    <option value="<%= b.getId() %>"
                            data-title="<%= b.getTitle() %>"
                            data-author="<%= b.getAuthor() %>">
                        <%= b.getTitle() %> by <%= b.getAuthor() %>
                    </option>
                <% } %>
            </select>

            <!-- SELECTED BOOK PREVIEW -->
            <div id="bookPreview" class="preview hidden">
                <h3 id="previewTitle"></h3>
                <p id="previewAuthor"></p>
            </div>

            <!-- BORROWER -->
            <label>Borrower Name</label>
            <input type="text" name="borrower" placeholder="Enter your name" required>

            <button type="submit" class="btn gradient">
                ✔ Confirm Borrow
            </button>

        </form>
    </div>
</div>

<script>
    const select = document.getElementById("bookSelect");
    const preview = document.getElementById("bookPreview");
    const title = document.getElementById("previewTitle");
    const author = document.getElementById("previewAuthor");

    select.addEventListener("change", () => {
        const option = select.options[select.selectedIndex];
        if (!option.value) {
            preview.classList.add("hidden");
            return;
        }
        title.innerText = option.dataset.title;
        author.innerText = option.dataset.author;
        preview.classList.remove("hidden");
    });
</script>

</body>
</html>
