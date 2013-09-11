package com.zemiak.books.batch.metadata;

import javax.batch.api.listener.JobListener;
import javax.batch.api.listener.StepListener;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vasko
 */
@Dependent
@Named("MetadataJobListener")
public class Listener implements JobListener, StepListener {
    @Inject
    JobContext jobCtx;
    
    @Inject
    StepContext stepCtx;

    @Override
    public void beforeJob() throws Exception {
        System.out.println("Job starting " + jobCtx.getJobName());
    }

    @Override
    public void afterJob() {
        System.out.println("Job Finished");
    }

    @Override
    public void beforeStep() throws Exception {
        System.out.println("Start STep " + stepCtx.getStepName());
    }

    @Override
    public void afterStep() throws Exception {
        System.out.println("End step");
    }
}
