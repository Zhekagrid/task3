package com.hrydziushka.task3.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Symbol extends TextComponent {
    static final Logger logger = LogManager.getLogger();

    private Character character;

    public Symbol(TextComponentType textComponentType, Character character) {
        super(textComponentType);

        this.character = character;
    }

    @Override
    public boolean add(TextComponent component) {
        logger.log(Level.ERROR, "Cannot apply the add operation to this component because it is a symbol");
        throw new UnsupportedOperationException("Cannot apply the add operation to this component because it is a symbol");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbol)) return false;
        if (!super.equals(o)) return false;

        Symbol symbol = (Symbol) o;

        return getCharacter() != null ? getCharacter().equals(symbol.getCharacter()) : symbol.getCharacter() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCharacter() != null ? getCharacter().hashCode() : 0);
        return result;
    }

    @Override
    public boolean remove(TextComponent component) {
        logger.log(Level.ERROR, "Cannot apply the remove operation to this component because it is a symbol");
        throw new UnsupportedOperationException("Cannot apply the remove operation to this component because it is a symbol");
    }

    @Override
    public List<TextComponent> getChildren() {
        logger.log(Level.ERROR, "Cannot apply the getChildren operation to this component because it is a symbol");
        throw new UnsupportedOperationException("Cannot apply the getChildren operation to this component because it is a symbol");

    }


    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return character.toString();
    }
}
