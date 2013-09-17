package com.zemiak.books.batch.tomobi;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemWriter;

/**
 *
 * @author vasko
 */
public class Writer extends AbstractItemWriter {

    private static final Logger LOG = Logger.getLogger(Writer.class.getName());

    @Override
    public void writeItems(List list) throws Exception {
        for (Object obj : list) {
            String epub = (String) obj;
            String mobi = epub.substring(0, epub.lastIndexOf(".")) + ".mobi";

            if (new File(mobi).isFile()) {
                LOG.log(Level.INFO, "Written {0}", mobi);
            } else {
                LOG.log(Level.SEVERE, "Cannot find MOBI {0}", mobi);
            }
        }
    }
}
