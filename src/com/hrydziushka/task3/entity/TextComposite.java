package com.hrydziushka.task3.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComposite extends TextComponent {
    static final Logger logger = LogManager.getLogger();
    private List<TextComponent> components;


    public TextComposite(TextComponentType textComponentType) {
        super(textComponentType);
        components = new ArrayList<>();
        logger.log(Level.INFO, "Composite was created " + textComponentType);
    }

    public TextComposite(TextComponentType textComponentType, List<TextComponent> components) {
        super(textComponentType);
        this.components = components;
        logger.log(Level.INFO, "Composite was created " + textComponentType);

    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return components.remove(component);
    }

    public List<TextComponent> getChildren() {
        return new ArrayList<>(components);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextComposite)) return false;
        if (!super.equals(o)) return false;

        TextComposite that = (TextComposite) o;

        return components != null ? components.equals(that.components) : that.components == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (components != null ? components.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        for (TextComponent component : components) {
            TextComponentType componentType = component.getTextComponentType();
            stringBuffer.append(componentType.getBeginning());
            stringBuffer.append(component);
            stringBuffer.append(componentType.getEnd());
        }
        return stringBuffer.toString();
    }
}
