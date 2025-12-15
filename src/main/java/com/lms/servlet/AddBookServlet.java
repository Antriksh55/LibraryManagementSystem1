package com.lms.servlet;

import com.lms.service.LibraryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/add-book")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        LibraryService svc = LibraryService.getInstance(getServletContext().getRealPath("/WEB-INF"));
        if (title != null && !title.trim().isEmpty()) {
            svc.addBook(title.trim(), author == null ? "" : author.trim());
            resp.sendRedirect("listBooks");
        } else {
            resp.sendRedirect("addBook.jsp?error=1");
        }
    }
}
