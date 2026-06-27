package com.app.web.servlets;

import com.app.model.*;
import com.app.db.RouteRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Collection;

@WebServlet("/routes")
public class RouteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth");
            return;
        }

        RouteRepository routeRepo = (RouteRepository) getServletContext().getAttribute("routeRepository");
        try {
            Collection<Route> routes = routeRepo.loadAll();
            req.setAttribute("routes", routes);
            req.setAttribute("currentUser", session.getAttribute("user"));
            req.getRequestDispatcher("/WEB-INF/views/routes.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/auth");
            return;
        }

        String userLogin = (String) session.getAttribute("user");
        RouteRepository routeRepo = (RouteRepository) getServletContext().getAttribute("routeRepository");

        try {
            String name = req.getParameter("name");
            double coordX = Double.parseDouble(req.getParameter("coordX"));
            int coordY = Integer.parseInt(req.getParameter("coordY"));

            long fromX = Long.parseLong(req.getParameter("fromX"));
            int fromY = Integer.parseInt(req.getParameter("fromY"));
            float fromZ = Float.parseFloat(req.getParameter("fromZ"));

            long toX = Long.parseLong(req.getParameter("toX"));
            int toY = Integer.parseInt(req.getParameter("toY"));
            float toZ = Float.parseFloat(req.getParameter("toZ"));

            float distance = Float.parseFloat(req.getParameter("distance"));

            Route route = new Route();
            route.setName(name);
            route.setCoordinates(new Coordinates(coordX, coordY));
            route.setFrom(new Location(fromX, fromY, fromZ));
            route.setTo(new Location(toX, toY, toZ));
            route.setDistance(distance);

            routeRepo.insert(route, userLogin);

            resp.sendRedirect(req.getContextPath() + "/routes");

        } catch (Exception e) {
            resp.getWriter().write("Ошибка добавления маршрута: " + e.getMessage());
        }
    }
}