package com.zemiak.books.batch.tomobi;

import com.zemiak.books.batch.service.CalibreWrapper;
import com.zemiak.books.batch.service.CalibreConversionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.ItemProcessor;

/**
 *
 * @author vasko
 */
public class Processor implements ItemProcessor {
    private static final Logger LOG = Logger.getLogger(Processor.class.getName());
    
    @Override
    public Object processItem(Object book) throws Exception {
        String fileName = (String) book;
        CalibreWrapper converter = new CalibreWrapper();
        
        try {
            converter.convertToMobi(fileName);
            LOG.log(Level.INFO, "Converted to mobi: {0}", fileName);
            return fileName;
        } catch (CalibreConversionException ex) {
            LOG.log(Level.SEVERE, "Cannot convert to mobi {0}: {1}", new Object[]{fileName, ex.getMessage()});
        }

        return null;
    }
}
