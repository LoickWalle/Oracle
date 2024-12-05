package models;

import interfaces.IText;

public class Text implements IText {
    private String text;

    public Text(String text){
        this.text = text;
    }

    @Override
    public String transform() {
        return text;
    }
}
