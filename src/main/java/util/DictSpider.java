package util;

import model.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
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

    }

    private static Word getWord(String english) {
        URL url = null;
        String chinese = null, phonetic = null, partOfSpeech = null;
        try {
            url = new URL(LINK.concat(english));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
                if (line.contains("<li><span>")) {
                    partOfSpeech = line.substring(line.indexOf("<span>") + 6, line.indexOf("</span>"));
                    chinese = line.substring(line.indexOf("<strong>") + 8, line.indexOf("</strong>"));
                }
                if (line.contains("<span>英 <bdo>")) {
                    phonetic = line.substring(line.indexOf("["), line.indexOf("]")+1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Word word = new Word(null, english, chinese, phonetic, partOfSpeech);
        System.out.println(word);
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
            words.add(getWord(english));
        }
        dump();

//        getWord("absolute");
    }
}
