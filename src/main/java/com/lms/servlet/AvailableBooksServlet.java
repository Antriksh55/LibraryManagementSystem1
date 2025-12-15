package com.lms.servlet;

import com.lms.service.LibraryService;
import com.lms.model.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/availableBooks")
public class AvailableBooksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LibraryService svc = LibraryService.getInstance(getServletContext().getRealPath("/WEB-INF"));
        List<Book> books = svc.getAvailableBooks();
        req.setAttribute("books", books);
        try {
            req.getRequestDispatcher("availableBooks.jsp").forward(req, resp);
        } catch (Exception e) { resp.sendError(500, e.getMessage()); }
    }
}
