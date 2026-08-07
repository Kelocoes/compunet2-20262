package com.example;

import java.io.*;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.beans.BeanA;
import com.example.model.User;
import com.example.services.impl.UserServiceImpl;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "servlet1", value = "/home")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Dashboard principal!!!!!!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");
        List<User> users = userServiceImpl.findAll();
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<ul>");
        for (User user : users) {
            out.println("<li>" + user.getName() + "</li>");
        }
        out.println("</ul>");
        out.println("<form method='POST' action='/demo/home'>");
        out.println("Nombre: <input type='text' name='name' required />");
        out.println("Email: <input type='email' name='email' required />");
        out.println("Password: <input type='password' name='password' required/>");
        out.println("<button type='submit'>Enviar!</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");
        userServiceImpl.create(name, email, password);
        response.sendRedirect("/demo/home");
    }

    public void destroy() {
    }
}