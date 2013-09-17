package com.zemiak.books.batch.service;

import com.zemiak.books.service.Storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vasko
 */
public class CalibreWrapper {
    String calibrePath = null;
    Map<String, String> optionForExt = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(CalibreWrapper.class.getName());
    
    public CalibreWrapper() {
        whichCalibre();
        
        optionForExt.put("txt", "--pretty-print");
        optionForExt.put("pdb", "--input-encoding=1250");
        optionForExt.put("epub", "--mobi-ignore-margins");
    }

    private void whichCalibre() {
        List<String> possiblePaths = new ArrayList<>();
        
        calibrePath = null;
        
        possiblePaths.add("c:\\Program Files\\Calibre2\\ebook-convert.exe");
        possiblePaths.add("c:\\Program Files\\Calibre\\ebook-convert.exe");
        possiblePaths.add("c:\\Program Files (x86)\\Calibre2\\ebook-convert.exe");
        possiblePaths.add("c:\\Program Files (x86)\\Calibre\\ebook-convert.exe");
        possiblePaths.add("/Applications/calibre.app/Contents/MacOS/ebook-convert");
        possiblePaths.add("/usr/bin/ebook-convert");
        possiblePaths.add("/usr/local/bin/ebook-convert");
        possiblePaths.add("/opt/calibre/ebook-convert");
        
        for (String possiblePath: possiblePaths) {
            File possibleCalibre = new File(possiblePath);
            
            if (possibleCalibre.isFile()) {
                calibrePath = possiblePath;
                break;
            }
        }
        
        if (null == calibrePath) {
            throw new RuntimeException("Calibre not found");
        }
    }
    
    public void convertToEpub(String name) throws CalibreConversionException {
        String ext = name.substring(name.lastIndexOf(".") + 1).trim().toLowerCase();
        
        List<String> options = new ArrayList<>();
        options.add(calibrePath);
        options.add(name);
        options.add(name.substring(0, name.lastIndexOf(".")) + ".epub");
        options.add("--no-default-epub-cover");
        options.add("--language=cz");
        
        if (ext.equals("rtf")) {
            preProcessRtf(name);
        }
        
        if (optionForExt.containsKey(ext)) {
            options.add(optionForExt.get(ext));
        }
        
        run(options);
    }

    private void preProcessRtf(String name) {
        File file = new File(name);
        String content = Storage.readFileContent(file);
        
        content = content.replace("ansicpg1252", "ansicpg1250");
        
        try (FileWriter stream = new FileWriter(name)) {
            stream.write(content);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Cannot open " + name + " for writing.", ex);
        }
    }

    private void run(List<String> options) throws CalibreConversionException {
        Process proc;
        int result = 0;
        
        try {
            proc = Runtime.getRuntime().exec(options.toArray(new String[]{}));
        } catch (IOException e) {
            throw new CalibreConversionException("IO Error in Calibre: " + e.getMessage());
        }
        
        try {
            result = proc.waitFor();
        } catch (InterruptedException e) {
            throw new CalibreConversionException("Process interrupted: " + e.getMessage());
        }
        
        if (result != 0) {
            throw new CalibreConversionException("Cannot convert: " + result);
        }
    }

    public void convertToMobi(String name) throws CalibreConversionException {
        List<String> options = new ArrayList<>();
        options.add(calibrePath);
        options.add(name);
        options.add(name.substring(0, name.lastIndexOf(".")) + ".mobi");
        options.add("--mobi-ignore-margins");
        
        run(options);
    }
}
