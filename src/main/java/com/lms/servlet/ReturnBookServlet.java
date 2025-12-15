package com.lms.servlet;

import com.lms.service.LibraryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");
        LibraryService svc = LibraryService.getInstance(getServletContext().getRealPath("/WEB-INF"));
        boolean ok = false;
        try {
            int id = Integer.parseInt(idStr);
            ok = svc.returnBook(id);
        } catch (Exception ignored) {}
        if (ok) resp.sendRedirect("listBooks");
        else resp.sendRedirect("return.jsp?error=1");
    }
}
