package com.test.task.back.dataload.api;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public interface DataLoader<T> {
    T parse(InputStream in) throws IOException, SAXException, ParserConfigurationException;
}
