package com.zemiak.books.batch.metadata;

import java.util.Properties;
import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;

/**
 *
 * @author vasko
 */
public class Processor implements ItemProcessor {
    @Inject
    private JobContext jobContext;

    public Object processItem(Object fileURL) throws Exception {
        Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId());
        return null;
    }
}
