<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.lms.model.BorrowRecord" %>

<jsp:include page="header.jsp"/>

<div class="container">
    <h2 class="page-title">📜 Borrow History</h2>
    <p class="subtitle">Track all borrowing records</p>

    <div class="timeline">

        <%
            List<BorrowRecord> history =
                (List<BorrowRecord>) request.getAttribute("history");

            if (history == null || history.isEmpty()) {
        %>
            <p class="empty">No borrow history available.</p>
        <%
            } else {
                for (BorrowRecord r : history) {
                    boolean returned = !"null".equals(r.getReturnDate());
        %>

        <div class="card">
            <div class="card-left">
                <div class="dot <%= returned ? "returned" : "borrowed" %>"></div>
            </div>

            <div class="card-content">
                <h3>📘 Book ID: <%= r.getBookId() %></h3>
                <p>👤 Borrower: <strong><%= r.getBorrower() %></strong></p>
                <p>📅 Borrowed: <%= r.getBorrowDate() %></p>

                <% if (returned) { %>
                    <p>✅ Returned: <%= r.getReturnDate() %></p>
                    <span class="status available">Returned</span>
                <% } else { %>
                    <span class="status borrowed">Active</span>
                <% } %>
            </div>
        </div>

        <%
                }
            }
        %>

    </div>
</div>

