package com.zemiak.books.batch.metadata;

import com.zemiak.books.domain.CacheClearEvent;
import com.zemiak.books.service.Storage;
import java.io.File;
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
@Named("MetadataRemoveFileList")
public class RemoveFileList implements Batchlet {
    @Inject
    JobContext jobCtx;

    private static final Logger LOG = Logger.getLogger(RemoveFileList.class.getName());
    
    @Inject 
    private javax.enterprise.event.Event<CacheClearEvent> clearEvent;
    
    public RemoveFileList() {
    }

    @Override
    public String process() throws Exception {
        String fileList = getFileListName();
        
        // clear Storage cache
        clearEvent.fire(new CacheClearEvent());
        
        File file = new File(fileList);
        if (! file.delete()) {
            LOG.log(Level.SEVERE, "Cannot remove temp file: {0}", fileList);
            return "delete-error";
        }
        
        return "ok";
    }
    
    private String getFileListName() {
        return jobCtx.getProperties().getProperty("fileList").replace("/", Storage.PATH_SEPARATOR);
    }

    @Override
    public void stop() throws Exception {
    }
}
