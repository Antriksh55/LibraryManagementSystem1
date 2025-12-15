package com.lms.servlet;

import com.lms.service.LibraryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LibraryService svc = LibraryService.getInstance(getServletContext().getRealPath("/WEB-INF"));
        req.setAttribute("history", svc.getHistory());
        try {
            req.getRequestDispatcher("history.jsp").forward(req, resp);
        } catch (Exception e) { resp.sendError(500, e.getMessage()); }
    }
}
