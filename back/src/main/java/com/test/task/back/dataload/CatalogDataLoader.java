package com.test.task.back.dataload;

import com.test.task.back.dataload.api.DataLoader;
import com.test.task.back.dataload.sax.CatalogSaxHandler;
import com.test.task.back.dto.CatalogForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
public class CatalogDataLoader implements DataLoader<CatalogForm> {

    @Override
    public CatalogForm parse(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        List<CatalogForm> catalog = new LinkedList<>();
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(in, new CatalogSaxHandler(parsed -> {
                log.debug("Catalog parsed successfully, saving to elastic...");
                if (parsed != null) {
                    catalog.add(parsed);
                }
            }));
            in.close();
        } catch (Exception e) {
            log.error("Error while uploading file: ", e);
            throw e;
        }
        return catalog.size() == 1 ? catalog.get(0) : null;
    }

}
