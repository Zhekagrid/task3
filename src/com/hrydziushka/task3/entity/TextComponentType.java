package com.hrydziushka.task3.entity;

public enum TextComponentType {
    TEXT,
    PARAGRAPH("\t", "\n"),
    SENTENCE,
    LEXEME("", " "),
    WORD,
    EXPRESSION,
    LETTER,
    DIGIT,
    PUNCTUATION;
    private String beginning;
    private String end;

    TextComponentType() {
        this.beginning = "";
        this.end = "";
    }

    TextComponentType(String beginning, String end) {
        this.beginning = beginning;
        this.end = end;
    }

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
