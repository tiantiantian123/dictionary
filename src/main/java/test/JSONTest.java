package test;

import model.Word;
import org.json.JSONObject;

/**
 * Created by Administrator
 * 上午9:03 16-5-11
 */
public class JSONTest {
    public static void main(String[] args) {

//        json.org

        Word word = new Word(1, "english", "英语", "/english/", "n.");

        JSONObject jsonObject = new JSONObject(word);

        System.out.println(jsonObject.toString());
    }
}
