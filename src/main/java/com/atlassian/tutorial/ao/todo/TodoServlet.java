package com.atlassian.tutorial.ao.todo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.*;

public final class TodoServlet extends HttpServlet
{
    private final TodoService todoService;

    public TodoServlet(TodoService todoService)
    {
        this.todoService = checkNotNull(todoService);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        final PrintWriter w = res.getWriter();
        w.write("<html lang=\"ru\">");
        w.write("<head>");
        w.write("<title>Чек-листы</title>");
        w.write("<meta name=\"decorator\" content=\"atl.general\">");
        w.write("<meta name=\"decorator\" content=\"atl.admin\">");
        w.write("</head>");
        w.write("<body>");
        w.write("<h1>Создать запись</h1>");

        // the form to post more TODOs
        w.write("<form method=\"post\">");
        w.write("<label>Тип проекта<input type=\"text\" name=\"projecttype\" size=\"25\"/></label>");
        w.write("  ");
        w.write("<br>");
        w.write("<label>Тип задачи<input type=\"text\" name=\"issuetype\" size=\"25\"/></label>");
        w.write("  ");
        w.write("<br>");
        w.write("<label>1.<input type=\"text\" name=\"task1\" size=\"25\"/></label>");
        w.write("  ");
        w.write("<br>");
        w.write("<label>2.<input type=\"text\" name=\"task2\" size=\"25\"/></label>");
        w.write("  ");
        w.write("<br>");
        w.write("<label>3.<input type=\"text\" name=\"task3\" size=\"25\"/></label>");
        w.write("  ");
        w.write("<input type=\"submit\" name=\"submit\" value=\"Add\"/>");
        w.write("</form>");



        Set<String> issuetypes = new HashSet<>();
        for (Todo todo: todoService.todotest()) {
            issuetypes.add(todo.getIssueType());
        }

        for (String s:issuetypes) {
            w.print(s);
            w.print("------------------------------------------");
            w.write("<ol>");
            for (Todo todo : todoService.todoofthisissuetype(s)) {
                if (todo.getIssueCode()=="test") {
                    w.printf("<li><%2$s> %s </%2$s></li>", todo.getDescription(), todo.isComplete() ? "strike" : "strong");
                }
            }
            w.write("</ol>");
        }



        w.write("<script language='javascript'>document.forms[0].elements[0].focus();</script>");
        w.write("</body>");
        w.write("</html>");
        w.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        final String description1 = req.getParameter("task1");
        final String description2 = req.getParameter("task2");
        final String description3 = req.getParameter("task3");
        final String issuetype = req.getParameter("issuetype");
        final String projecttype = req.getParameter("projecttype");
        todoService.add(description1, issuetype, projecttype, "test");
        todoService.add(description2, issuetype, projecttype, "test");
        todoService.add(description3, issuetype, projecttype, "test");
        res.sendRedirect(req.getContextPath() + "/plugins/servlet/todo/list");
    }
}