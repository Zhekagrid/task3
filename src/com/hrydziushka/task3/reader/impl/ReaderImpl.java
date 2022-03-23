package com.hrydziushka.task3.reader.impl;

import com.hrydziushka.task3.exception.CustomTextException;
import com.hrydziushka.task3.reader.Reader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReaderImpl implements Reader {
    static final Logger logger = LogManager.getLogger();

    @Override
    public String readLinesFromFile(String filePath) throws CustomTextException {

        String text = "";

        try {
            logger.log(Level.INFO, "Start reading; filepath: " + filePath);
            Path path = Paths.get(filePath);
            text = Files.readString(path);
        } catch (IOException e) {
            logger.log(Level.ERROR, "IOexception while reading file  " + filePath, e);
            throw new CustomTextException("IOexception while reading file " + filePath, e);
        }

        logger.log(Level.INFO, "End reading; filepath: " + filePath);
        return text;

    }
}

