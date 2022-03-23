package com.hrydziushka.task3.reader;

import com.hrydziushka.task3.exception.CustomTextException;

public interface Reader {
    String readLinesFromFile(String filePath) throws CustomTextException;
}
