package com.zemiak.books.batch.metadata;

import com.zemiak.books.batch.metadata.domain.EpubFile;
import javax.batch.api.chunk.ItemProcessor;

/**
 *
 * @author vasko
 */
public class Processor implements ItemProcessor {
    @Override
    public Object processItem(Object book) throws Exception {
        EpubFile epub = (EpubFile) book;
        
        epub.update();
        
        return epub;
    }
}
