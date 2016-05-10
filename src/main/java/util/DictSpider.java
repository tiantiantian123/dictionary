package util;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.Word;

import java.io.*;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 下午2:59 16-5-9
 */
public class DictSpider {

    private static final String LINK = "http://dict.cn/";
    private static List<String> englishList;
    private static List<Word> words;

    private static void dump() {
        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO db_dictionary.word VALUES(NULL, ?, ?, ?, ?)";

        try {
            if (connection == null) {
                return;
            }
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);

            for (Word word : words) {
                preparedStatement.setString(1, word.getEnglish());
                preparedStatement.setString(2, word.getChinese());
                preparedStatement.setString(3, word.getPhonetic());
                preparedStatement.setString(4, word.getPartOfSpeech());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(null, preparedStatement, connection);
        }
    }

    private static Word getWord(String english) {
        URL url = null;
        String chinese = null, phonetic = null, partOfSpeech = null;
        InputStream inputStream = null;
        try {
            url = new URL(LINK.concat(english));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url == null) {
            return null;
        }
        try {
            inputStream = url.openStream();
        } catch (ConnectException e) {
            System.out.println("\t timeout...");
            getWord(english);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return null;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
                if (line.contains("<li><span>")) {
                    partOfSpeech = line.substring(line.indexOf("<span>") + 6, line.indexOf("</span>"));
                    chinese = line.substring(line.indexOf("<strong>") + 8, line.contains("</strong>")?line.indexOf("</strong>"):line.length());
                }
                if (line.contains("<span>英 <bdo>")) {
                    phonetic = line.substring(line.indexOf("["), line.indexOf("]") + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Word word = new Word(null, english, chinese, phonetic, partOfSpeech);
        System.out.println(english);
        return word;
    }

    private static void getEnglishList() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data/CET-4.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                englishList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        englishList = new ArrayList<>();
        getEnglishList();
        words = new ArrayList<>();
        for (String english : englishList) {
            Word word = getWord(english);
            if (word != null) {
                words.add(word);
            }
        }
        WordUtil.write(words);
//        words = WordUtil.read(words.size());
        dump();

//        getWord("export");
    }
}
