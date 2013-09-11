package com.zemiak.books.batch.metadata;

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
    
    public RemoveFileList() {
    }

    @Override
    public String process() throws Exception {
        //Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobCtx.getExecutionId());
        String fileList = jobCtx.getProperties().getProperty("fileList");
        
        return "done";
    }

    @Override
    public void stop() throws Exception {
    }
    
}
