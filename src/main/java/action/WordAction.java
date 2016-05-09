package action;

import model.Word;
import util.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        if (action.equals("queryAll")) {
            queryAll(req, resp);
        }
        if (action.equals("search")) {
            search(req, resp);
        }
        if (action.equals("update")) {
            update(req, resp);
        }
        if (action.equals("remove")) {
            remove(req, resp);
        }
        if (action.equals("query")) {
            query(req, resp);
        }
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");

        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM db_dictionary.word WHERE english LIKE ? OR chinese LIKE ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%"+key+"%");
            preparedStatement.setString(2, "%"+key+"%");

            resultSet = preparedStatement.executeQuery();
            List<Word> words = new ArrayList<>();
            while (resultSet.next()) {
                Word word = new Word(resultSet.getInt("id"), resultSet.getString("english"), resultSet.getString("chinese"), resultSet.getString("phonetic"), resultSet.getString("part_of_speech"));
                words.add(word);
            }
            req.getSession().setAttribute("words", words);
            resp.sendRedirect("/index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }

    private void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM db_dictionary.word WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            resp.sendRedirect("/word?action=queryAll");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String english = req.getParameter("english").trim();
        String chinese = req.getParameter("chinese").trim();
        String phonetic = req.getParameter("phonetic").trim();
        String partOfSpeech = req.getParameter("part_of_speech").trim();

        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE db_dictionary.word SET english = ?, chinese = ?, phonetic = ?, part_of_speech = ? WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, english);
            preparedStatement.setString(2, chinese);
            preparedStatement.setString(3, phonetic);
            preparedStatement.setString(4, partOfSpeech);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

            resp.sendRedirect("/word?action=queryAll");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection); // ***
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM db_dictionary.word WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Word word = new Word(resultSet.getInt("id"), resultSet.getString("english"), resultSet.getString("chinese"), resultSet.getString("phonetic"), resultSet.getString("part_of_speech"));
            req.setAttribute("word", word);
            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
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

            resp.sendRedirect("/word?action=queryAll");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection); // ***
        }
    }

    protected void queryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM db_dictionary.word";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<Word> words = new ArrayList<>();
            while (resultSet.next()) {
                Word word = new Word(resultSet.getInt("id"), resultSet.getString("english"), resultSet.getString("chinese"), resultSet.getString("phonetic"), resultSet.getString("part_of_speech"));
                words.add(word);
            }
            req.getSession().setAttribute("words", words);
            resp.sendRedirect("/admin/index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(resultSet, preparedStatement, connection);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
