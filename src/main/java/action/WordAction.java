package action;

import util.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by non-perfectionist
 * 9:14 2016/5/9.
 */
@WebServlet(urlPatterns = "/word")
public class WordAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("add")) {
            add(req, resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String english = req.getParameter("english").trim();
        String chinese = req.getParameter("chinese").trim();
        String phonetic = req.getParameter("phonetic").trim();
        String partOfSpeech = req.getParameter("part_of_speech").trim();

        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO db_dictionary.word VALUES (NULL,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, english);
            preparedStatement.setString(2, chinese);
            preparedStatement.setString(3, phonetic);
            preparedStatement.setString(4, partOfSpeech);
            preparedStatement.executeUpdate();

            resp.sendRedirect("/admin/index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection); // ***
        }
    }

    protected void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
