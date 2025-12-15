<%@ include file="header.jsp" %>

<div class="container">
    <div class="hero">
        <h1>Library <span>Management System</span></h1>
        <p>Track books, borrowing, and history in one place</p>
    </div>

    <div class="grid">
        <div class="card">
            <h3>View All Books</h3>
            <p>Browse entire library</p>
            <a href="listBooks"><button class="btn">Open</button></a>
        </div>

        <div class="card">
            <h3>Available Books</h3>
            <p>Ready to borrow</p>
            <a href="availableBooks"><button class="btn">Check</button></a>
        </div>

        <div class="card">
            <h3>Borrow Book</h3>
            <p>Borrow instantly</p>
            <a href="borrow.jsp"><button class="btn">Borrow</button></a>
        </div>

        <div class="card">
            <h3>Return Book</h3>
            <p>Return borrowed books</p>
            <a href="return.jsp"><button class="btn">Return</button></a>
        </div>
    </div>
</div>
