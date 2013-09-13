package com.zemiak.books.batch.metadata;

import com.zemiak.books.batch.metadata.domain.EpubFile;
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
            EpubFile epub = (EpubFile) obj;

            LOG.log(Level.INFO, "Writing {0}", obj.toString());
            epub.write();

            String mobi = epub.getBook().getMobiFileName();
            if (null != mobi) {
                File file = new File(mobi);
                if (!file.delete()) {
                    LOG.log(Level.SEVERE, "Cannot remove MOBI file: {0}", mobi);
                }
            }
        }
    }
}
