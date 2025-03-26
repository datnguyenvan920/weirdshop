/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

import dao.LoginDao;
import model.User;

/**
 *
 * @author thang
 */
@WebServlet(name = "login", urlPatterns = {"/login", "/register", "/logout"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/loginpage":
                request.getRequestDispatcher("/WEB-INF/view/login/Login.jsp").forward(request, response);
                break;
            case "/register":
                registerNewUser(request, response);
                break;
            case "/login":
                checkLogin(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    //Login function
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginDao logindao = new LoginDao();
        String emailpara = request.getParameter("email");
        String passwordpara = request.getParameter("password");

        if (checkEmailExist(emailpara) == -1) {
            request.setAttribute("loginmessage", "No such email found");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }

        if (!checkPasswordValid(passwordpara)) {
            request.setAttribute("loginmessage", "Password must be 8 character or more");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }

        HttpSession session = request.getSession();

        // Check if email exists and if the password is correct
        int userId = checkEmailExist(emailpara);
        if (findPassword(passwordpara, userId)) {
            // Add the userId to the session
            User user = logindao.findUserbyId(userId);
            session.setAttribute("user", user);

            // Optionally, redirect or forward to a logged-in page
            response.sendRedirect(request.getContextPath()+"/home");
        } else {
            request.setAttribute("loginmessage", "Wrong password");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
    }

    public int checkEmailExist(String email) {
        LoginDao logindao = new LoginDao();
        ArrayList<User> mail = logindao.getUserEmail();
        String currentmail;
        for (int i = 0; i < mail.size(); i++) {
            currentmail = mail.get(i).getEmail();
            if (email.equals(currentmail)) {    //return true if found email in db
                return mail.get(i).getUserID();
            }
        }
        return -1;     //return false if not found
    }

    public boolean checkPasswordValid(String password) {
        return password.length() >= 8; //password validation
    }

    public boolean findPassword(String password, int userId) {
        LoginDao logindao = new LoginDao();
        ArrayList<User> pass = logindao.getUserPassword();

        for (int i = 0; i < pass.size(); i++) {
            User user = pass.get(i);
            if (userId == user.getUserID()) {
                String currentpass = user.getPassword();
                // Check if the passwords match
                return password.equals(currentpass);
            }
        }
        return false;
    }

    
    
    public void registerNewUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LoginDao logindao = new LoginDao();
        String emailpara = request.getParameter("email");
        String usernamepara = request.getParameter("username");
        String passwordpara = request.getParameter("password");

        // Null check before isEmpty()
        if (emailpara == null || emailpara.trim().isEmpty()) {
            request.setAttribute("signupmessage", "Invalid email");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
            return;
        }

        if (usernamepara == null || usernamepara.trim().isEmpty()) {
            request.setAttribute("signupmessage", "Invalid username");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
            return;
        }

        if (passwordpara == null || passwordpara.trim().isEmpty()) {
            request.setAttribute("signupmessage", "Invalid password");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
            return;
        }
        
        if(checkEmailExist(emailpara)==-1){
            LoginDao loginDao = new LoginDao();
            loginDao.registerUser(usernamepara, emailpara, passwordpara);
            
            HttpSession session = request.getSession();
            User user = logindao.findUserbyId(loginDao.findLatestUser());
            session.setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + "/home");
            
        }else{
            request.setAttribute("signupmessage", "Email Already Exist");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
        
        
        // Continue with user registration process...
    }

    //Logout function
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the current session
        HttpSession session = request.getSession(false); // Use false to not create a new session if it doesn't exist
        // If session exists, invalidate it
        if (session != null) {
            session.invalidate();  // This removes all session attributes, including "userId"
        }

        // Redirect the user to the login page or home page
        response.sendRedirect(request.getContextPath() + "/home");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
