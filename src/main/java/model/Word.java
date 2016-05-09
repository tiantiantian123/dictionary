package model;

/**
 * Created by Administrator
 * 下午1:02 16-5-9
 */
public class Word {
    private Integer id;
    private String english;
    private String chinese;
    private String phonetic;
    private String partOfSpeech;

    public Word() {
    }

    public Word(Integer id, String english, String chinese, String phonetic, String partOfSpeech) {
        this.id = id;
        this.english = english;
        this.chinese = chinese;
        this.phonetic = phonetic;
        this.partOfSpeech = partOfSpeech;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
}
