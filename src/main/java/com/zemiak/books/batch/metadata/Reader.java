package com.zemiak.books.batch.metadata;

import java.io.Serializable;
import java.util.Properties;
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
    @Inject
    private JobContext jobContext;
    private String[] fileURLArray;
    private int counter; 

    public Reader() {
    }
    
    public void open(Serializable e) {
        System.out.println("Open readItems stage - prepare item list");
        Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId());
        String fileList = jobParameters.getProperty("fileList");
        fileURLArray = fileList.split(";");
        counter=0;                
    }

    @Override
    public Object readItem() {
        if (counter< fileURLArray.length) {
            System.out.println("ReadItem returns "+fileURLArray[counter]);
           return fileURLArray[counter++];   
        } else {
        return null;
        }
    }
}
