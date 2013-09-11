package com.zemiak.books.batch.metadata;

import java.io.Serializable;
import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;

/**
 *
 * @author vasko
 */
@Named("MetadataItemWriter")
public class Writer extends AbstractItemWriter {
    @Override
    public void open(Serializable checkpoint) throws Exception {
        System.out.println("open item writing stage");
    }

    @Override
    public void writeItems(List list) throws Exception {
        System.out.println("Write "+list.size()+" file items");
        for (Object obj : list) {
            System.out.println("Write file item "+obj);
        }
    }
}
