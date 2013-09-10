package com.zemiak.books.service.cron;

import com.zemiak.books.domain.CacheClearEvent;
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
public class ClearCache {
    @Inject
    private javax.enterprise.event.Event<CacheClearEvent> clearEvent;
    
    private static final Logger LOG = Logger.getLogger(ClearCache.class.getName());
    
    @Schedule(dayOfWeek="Fri", hour="3", minute="0")
    public void start() {
        clearEvent.fire(new CacheClearEvent());
        LOG.log(Level.INFO, "CacheClearEvent fired, the cache should be clean now");
    }
}
