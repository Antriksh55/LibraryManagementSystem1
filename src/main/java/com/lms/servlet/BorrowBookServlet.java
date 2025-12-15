package com.lms.servlet;

import com.lms.service.LibraryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/borrow")
public class BorrowBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String idStr = req.getParameter("id");
        String borrower = req.getParameter("borrower");

        LibraryService service =
                LibraryService.getInstance(getServletContext().getRealPath("/WEB-INF"));

        boolean success = false;

        try {
            int id = Integer.parseInt(idStr);
            success = service.borrowBook(id, borrower);
        } catch (Exception ignored) {}

        if (success) {
            resp.sendRedirect("availableBooks");
        } else {
            resp.sendRedirect("borrow-page?error=1");
        }
    }
}
