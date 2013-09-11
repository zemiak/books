package com.zemiak.books.batch.metadata;

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
    
    public PrepareFileList() {
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
