package com.zemiak.books.batch.toepub;

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
            String pdb = (String) obj;
            String epub = pdb.substring(0, pdb.lastIndexOf(".")) + ".epub";
            String mobi = pdb.substring(0, pdb.lastIndexOf(".")) + ".mobi";

            if (new File(epub).isFile()) {
                if (!new File(pdb).delete()) {
                    LOG.log(Level.SEVERE, "Cannot remove PDB file: {0}", mobi);
                }

                File mobiFile = new File(mobi);
                if (mobiFile.isFile()) {
                    if (!mobiFile.delete()) {
                        LOG.log(Level.SEVERE, "Cannot remove MOBI file: {0}", mobi);
                    } else {
                        LOG.log(Level.INFO, "Removed MOBI file: {0}", mobi);
                    }
                }
                
                LOG.log(Level.INFO, "Written {0}", epub);
            } else {
                LOG.log(Level.SEVERE, "Cannot find EPUB {0}", epub);
            }
        }
    }
}
