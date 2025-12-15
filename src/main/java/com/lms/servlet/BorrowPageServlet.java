package com.lms.servlet;

import com.lms.model.Book;
import com.lms.service.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/borrow-page")
public class BorrowPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LibraryService service =
                LibraryService.getInstance(getServletContext().getRealPath("/WEB-INF"));

        List<Book> books = service.getAvailableBooks();
        request.setAttribute("books", books);

        request.getRequestDispatcher("/borrow.jsp").forward(request, response);
    }
}
