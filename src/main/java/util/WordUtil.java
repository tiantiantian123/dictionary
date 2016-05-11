package util;

import model.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 下午3:01 16-5-10
 */
public class WordUtil {

    private static final String FILE_NAME = "data/words";

    public static void main(String[] args) {
        List<Word> words = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Word word = new Word(i + 1, "english", "chinese", "phonetic", "part of speech");
            words.add(word);
        }
        write(words);
        read(words.size());
    }

    public static void write(List<Word> words) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Word word : words) {
                objectOutputStream.writeObject(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Word> read(int size) {
        List<Word> words = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            for (int i = 0; i < size; i++) {
                Word word = (Word) objectInputStream.readObject();
                words.add(word);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    }
}
