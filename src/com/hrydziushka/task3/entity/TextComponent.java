package com.hrydziushka.task3.entity;

import java.util.List;

public abstract class TextComponent {
    private TextComponentType textComponentType;

    public TextComponent(TextComponentType textComponentType) {
        this.textComponentType = textComponentType;
    }

    public TextComponentType getTextComponentType() {
        return textComponentType;
    }

    public void setTextComponentType(TextComponentType textComponentType) {
        this.textComponentType = textComponentType;
    }

    public abstract boolean add(TextComponent component);

    public abstract boolean remove(TextComponent component);

    public abstract List<TextComponent> getChildren();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextComponent)) return false;

        TextComponent component = (TextComponent) o;

        return getTextComponentType() == component.getTextComponentType();
    }

    @Override
    public int hashCode() {
        return getTextComponentType() != null ? getTextComponentType().hashCode() : 0;
    }

    public abstract String toString();

}
