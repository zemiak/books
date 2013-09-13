package com.zemiak.books.batch.metadata;

import com.zemiak.books.batch.metadata.domain.EpubFile;
import com.zemiak.books.boundary.Collection;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vasko
 */
@Named("MetadataItemReader")
public class Reader extends AbstractItemReader {
    private static final Logger LOG = Logger.getLogger(Reader.class.getName());
    
    @Inject
    JobContext jobCtx;
    
    @Inject
    Collection col;
    
    FileInputStream inputStream;
    BufferedReader br;
    int recordNumber;

    public Reader() {
    }

    @Override
    public void open(Serializable prevCheckpointInfo) throws IOException {
        String fileList = jobCtx.getProperties().getProperty("fileList");

        inputStream = new FileInputStream(fileList);
        br = new BufferedReader(new InputStreamReader(inputStream));

        if (prevCheckpointInfo != null) {
            recordNumber = (Integer) prevCheckpointInfo;
        }

        for (int i = 1; i < recordNumber; i++) {   //Skip upto recordNumber
            br.readLine();
        }

        LOG.log(Level.INFO, "Opened file list from record:{0}", recordNumber);
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return recordNumber;
    }

    @Override
    public Object readItem() throws IOException {
        String line = br.readLine();

        if (null != line) {
            Integer id = Integer.valueOf(line.trim());

            return new EpubFile(col.getBook(id));
        }

        return null;
    }
}
