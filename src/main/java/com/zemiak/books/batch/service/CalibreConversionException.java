package com.zemiak.books.batch.service;

import java.io.IOException;
import javax.ejb.ApplicationException;

/**
 *
 * @author vasko
 */
@ApplicationException
public class CalibreConversionException extends IOException {
    public CalibreConversionException(String text) {
        super(text);
    }
    
    public CalibreConversionException() {
        super();
    }
}
