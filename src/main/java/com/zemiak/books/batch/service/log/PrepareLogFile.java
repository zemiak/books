package com.zemiak.books.batch.service.log;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vasko
 */
@Named("PrepareLogFile")
public class PrepareLogFile implements Batchlet {
    @Inject
    JobContext jobCtx;

    public PrepareLogFile() {
    }

    @Override
    public String process() throws Exception {
        BatchLogger.deleteLogFile();

        return "done";
    }

    @Override
    public void stop() throws Exception {
    }
}
