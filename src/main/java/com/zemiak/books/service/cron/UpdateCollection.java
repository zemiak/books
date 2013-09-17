package com.zemiak.books.service.cron;

import com.zemiak.books.boundary.BatchRunner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author vasko
 */
@Singleton
public class UpdateCollection {
    private static final Logger LOG = Logger.getLogger(UpdateCollection.class.getName());
    
    @Inject
    BatchRunner runner;
    
    @Schedule(dayOfWeek="Fri", hour="3", minute="0")
    public void start() {
        if (runner.isUpdateCollectionRunning()) {
            LOG.log(Level.SEVERE, "Update Job is already running !!!");
        } else {
            LOG.log(Level.INFO, "Update Collection started");
            runner.runUpdateCollection();
        }
    }
}
