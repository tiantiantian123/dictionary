package test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Word;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 上午9:03 16-5-11
 */
public class JSONTest {
    public static void main(String[] args) {

//        json.org
/*
        Word word = new Word(1, "english", "英语", "/english/", "n.");

        JSONObject jsonObject = new JSONObject(word);

        System.out.println(jsonObject.toString(4));*/
/*
        List<Word> words = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Word word = new Word(i + 1, "a", "b", "c", "d");
            words.add(word);
        }

        JSONArray jsonArray = new JSONArray(words);
        System.out.println(jsonArray.toString(4));*/

//        jackson
/*

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        Word word = new Word(1, "english", "英语", "/english/", "n.");

        try {
            String json = objectMapper.writeValueAsString(word);
            System.out.println(objectMapper.writeValueAsString(word));
            Word word1 = objectMapper.readValue(json, Word.class);
            System.out.println(word1);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

//        Gson
/*
        Word word = new Word(1, "english", "英语", "/english/", "n.");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(word);
        System.out.println(json);

        System.out.println(gson.fromJson(json, Word.class));*/

//        fastJson

        Word word = new Word(1, "english", "英语", "/english/", "n.");

        String json = JSON.toJSONString(word, true);
        System.out.println(json);
        System.out.println(JSON.parseObject(json, Word.class));
    }
}
