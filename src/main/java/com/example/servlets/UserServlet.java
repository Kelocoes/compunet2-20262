package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.model.User;
import com.example.service.IUserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "userServlet", value = "/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService userService = (IUserService) context.getBean("userService");

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
        out.println("<h3>Registrar nuevo usuario!</h3>");
        out.println("<form method='post' action='/demo/users'>");
        out.println("  Nombre: <input type='text' name='name' required /><br/>");
        out.println("  Email:  <input type='email' name='email' required /><br/><br/>");
        out.println("  <button type='submit'>Guardar</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService userService = (IUserService) context.getBean("userService");

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword("default");
        userService.save(newUser);

        response.sendRedirect(request.getContextPath() + "/users");
    }
}
