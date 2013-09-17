package com.zemiak.books.batch.tomobi;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.service.Configuration;
import com.zemiak.books.service.Storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vasko
 */
@Named("TomobiPrepareFileList")
public class PrepareFileList implements Batchlet {
    @Inject
    JobContext jobCtx;
    
    @Inject
    Collection col;
    
    @Inject
    Configuration conf;
    
    List<String> files = new ArrayList<>();
    
    private static final Logger LOG = Logger.getLogger(PrepareFileList.class.getName());
    
    public PrepareFileList() {
    }

    @Override
    public String process() throws Exception {
        String fileList = getFileListName();
        long counter = 0;
        
        File mainDir = new File(conf.getBookPath());
        try (FileWriter stream = new FileWriter(fileList)) {
            final String newLine = System.getProperty("line.separator");
            
            readDataFromFiles(mainDir);
            
            for (String name: files) {
                stream.write(name + newLine);
                counter++;
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Cannot open " + fileList + " for writing.", ex);
            throw ex;
        }
        
        LOG.log(Level.INFO, "Going to convert {0} books to MOBI.", counter);
        
        return "done";
    }

    @Override
    public void stop() throws Exception {
        File file = new File(getFileListName());
        file.delete();
    }
    
    private String getFileListName() {
        return jobCtx.getProperties().getProperty("fileList").replace("/", Storage.PATH_SEPARATOR);
    }
    
    private void readDataFromFiles(File mainDir) {
        for (String letter : mainDir.list()) {
            File letterFile = new File(mainDir.getAbsolutePath() + Storage.PATH_SEPARATOR + letter);

            if ((letterFile.isDirectory()) && (letterFile.getName().length() == 1)) {
                readAuthors(letterFile);
            }
        }
    }
    
    private void readAuthors(File letterFile) {

        for (String author: letterFile.list()) {
            File authorFile = new File(letterFile.getAbsolutePath() + Storage.PATH_SEPARATOR + author);

            if (authorFile.isDirectory() && !authorFile.getName().startsWith(".")) {
                readAuthor(authorFile);
            }
        }
    }
    
    private void readAuthor(File authorFile) {
        File en = new File(authorFile.getAbsolutePath() 
                + Storage.PATH_SEPARATOR + "en" + Storage.PATH_SEPARATOR);

        if (en.isDirectory()) {
            for (String fileName : en.list()) {
                checkBookFile(en.getAbsolutePath() + Storage.PATH_SEPARATOR + fileName);
                
            }
        }

        for (String fileName : authorFile.list()) {
            checkBookFile(authorFile.getAbsolutePath() + Storage.PATH_SEPARATOR + fileName);
        }
    }

    private void checkBookFile(String name) {
        String ext = name.substring(name.lastIndexOf(".") + 1).trim().toLowerCase();
        String mobi = name.substring(0, name.lastIndexOf(".")) + ".mobi";
        
        if (ext.equals("epub") && !(new File(mobi).isFile())) {
            files.add(name);
        }
    }
}
