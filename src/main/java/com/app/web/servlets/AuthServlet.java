package com.app.web.servlets;

import com.app.db.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        UserRepository userRepo = (UserRepository) getServletContext().getAttribute("userRepository");

        try {
            if ("register".equals(action)) {
                if (userRepo.register(login, pass)) {
                    req.setAttribute("message", "Регистрация успешна! Теперь войдите.");
                } else {
                    req.setAttribute("error", "Логин уже занят.");
                }
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);

            } else if ("login".equals(action)) {
                if (userRepo.authenticate(login, pass)) {
                    req.getSession().setAttribute("user", login);
                    resp.sendRedirect(req.getContextPath() + "/routes");
                } else {
                    req.setAttribute("error", "Неверный логин или пароль.");
                    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}