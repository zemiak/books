package com.zemiak.books.batch.metadata;

import com.zemiak.books.batch.metadata.domain.EpubFile;
import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Book;
import com.zemiak.books.service.Storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vasko
 */
@Named("MetadataPrepareFileList")
public class PrepareFileList implements Batchlet {
    @Inject
    JobContext jobCtx;
    
    @Inject
    Collection col;
    
    private static final Logger LOG = Logger.getLogger(PrepareFileList.class.getName());
    
    public PrepareFileList() {
    }

    @Override
    public String process() throws Exception {
        String fileList = getFileListName();
        long counter = 0;
        
        try (FileWriter stream = new FileWriter(fileList)) {
            String newLine = System.getProperty("line.separator");
            
            for (Book book: col.getBooks()) {
                EpubFile file;
                
                file = new EpubFile(book);
                
                if (! file.isUpToDate()) {
                    stream.write(book.getId() + newLine);
                    counter++;
                }
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Cannot open " + fileList + " for writing.", ex);
            throw ex;
        }
        
        LOG.log(Level.INFO, "Going to update {0} books.", counter);
        
        return "done";
    }

    @Override
    public void stop() throws Exception {
        File file = new File(getFileListName());
        file.delete();
    }
    
    private String getFileListName() {
        return jobCtx.getProperties().getProperty("fileList").replace("/", Storage.PATH_SEPARATOR);
    }
}
