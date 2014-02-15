package com.zemiak.books.batch.service;

import com.zemiak.books.batch.service.log.BatchLogger;
import com.zemiak.books.service.CustomResourceLookup;
import com.zemiak.books.service.Storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

/**
 *
 * @author vasko
 */
public class CalibreWrapper {
    private static final BatchLogger LOG = BatchLogger.getLogger(CalibreWrapper.class.getName());
    final String calibrePath;
    final Map<String, String> optionForExt = new HashMap<>();

    public CalibreWrapper() {
        final Properties conf = new CustomResourceLookup().lookup("com.zemiak.books");
        calibrePath = conf.getProperty("calibrePath");

        optionForExt.put("txt", "--pretty-print");
        optionForExt.put("pdb", "--input-encoding=1250");
        optionForExt.put("epub", "--mobi-ignore-margins");
    }

    public void convertToEpub(String name) throws CalibreConversionException {
        String ext = name.substring(name.lastIndexOf(".") + 1).trim().toLowerCase();

        List<String> options = new ArrayList<>();
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

        run(calibrePath, options);
    }

    private void preProcessRtf(String name) {
        File file = new File(name);
        String content = Storage.readFileContent(file);

        if (null == content) {
            return;
        }

        content = content.replace("ansicpg1252", "ansicpg1250");

        try (FileWriter stream = new FileWriter(name)) {
            stream.write(content);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Cannot open " + name + " for writing.", ex);
        }
    }

    private void run(final String command, final List<String> options) throws CalibreConversionException {
        try {
            CommandLine.execCmd(command, options);
        } catch (IOException ex) {
            throw new CalibreConversionException("IO Error in Calibre: " + ex.getMessage());
        } catch (InterruptedException ex) {
            throw new CalibreConversionException("Process interrupted: " + ex.getMessage());
        } catch (IllegalStateException ex) {
            throw new CalibreConversionException("Cannot convert: " + ex.getMessage());
        }
    }

    public void convertToMobi(String name) throws CalibreConversionException {
        List<String> options = new ArrayList<>();
        options.add(name);
        options.add(name.substring(0, name.lastIndexOf(".")) + ".mobi");
        options.add("--mobi-ignore-margins");

        run(calibrePath, options);
    }
}
