package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.example.model.User;
import com.example.service.IUserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * BUENA PRÁCTICA: Este servlet obtiene el ApplicationContext Singleton
 * que el ContextLoaderListener inicializó al arrancar Tomcat.
 * 
 * Ventajas frente a crear un contexto por petición:
 *  - El contexto se crea UNA SOLA VEZ al arrancar la app
 *  - Todos los servlets comparten los mismos beans (singleton por defecto)
 *  - El estado de los datos se mantiene entre peticiones (en memoria)
 *  - Es mucho más eficiente en memoria y rendimiento
 */
@WebServlet(name = "userContextServlet", value = "/users-context")
public class UserContextServlet extends HttpServlet {

    private IUserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                com.example.config.AppConfig.class);

        this.userService = (IUserService) context.getBean("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = userService.findAll();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><body>");
        out.println("<h2>Usuarios registrados</h2>");
        out.println("<ul>");
        for (User u : users) {
            out.println("<li>" + u.getId() + " - " + u.getName() + " (" + u.getEmail() + ")</li>");
        }
        out.println("</ul>");
        out.println("<hr>");
        out.println("<h3>Registrar nuevo usuario</h3>");
        out.println("<form method='post' action='/demo/users-context'>");
        out.println("  Nombre: <input type='text' name='name' required /><br/>");
        out.println("  Email:  <input type='email' name='email' required /><br/><br/>");
        out.println("  <button type='submit'>Guardar</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name  = request.getParameter("name");
        String email = request.getParameter("email");

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword("default");
        userService.save(newUser);

        response.sendRedirect(request.getContextPath() + "/users-context");
    }
}
