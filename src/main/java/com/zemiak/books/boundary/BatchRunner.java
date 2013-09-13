package com.zemiak.books.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.ejb.Stateless;
import org.apache.commons.lang.enums.EnumUtils;

/**
 *
 * @author vasko
 */
@Stateless
public class BatchRunner {
    private static final Logger LOG = Logger.getLogger(BatchRunner.class.getName());
    
    JobOperator operator;
    Long updateCollectionJob = null;
    
    public BatchRunner() {
    }
    
    @PostConstruct
    public void init() {
        operator = BatchRuntime.getJobOperator();
    }
    
    public void runUpdateCollection() {
        if (isRunning(updateCollectionJob)) {
            LOG.log(Level.INFO, "Job updateCollectionJob is still running");
            return;
        }
        
        updateCollectionJob = operator.start("update-books", null);
        LOG.log(Level.INFO, "Submitted updateCollectionJob {0}", updateCollectionJob);
    }

    private boolean isRunning(Long id) {
        if (null == id) {
            return false;
        }

        BatchStatus status = operator.getJobExecution(id).getBatchStatus();
        return (status == BatchStatus.STARTING || status == BatchStatus.STOPPING
                || status == BatchStatus.STARTED);
    }
    
    public boolean isUpdateCollectionRunning() {
        return isRunning(updateCollectionJob);
    }
}
