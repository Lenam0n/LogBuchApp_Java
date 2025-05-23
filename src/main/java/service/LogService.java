// src/main/java/service/LogService.java
package service;

import model.LogModel;
import org.springframework.stereotype.Service;

import exeption.DuplicateEntryException;
import util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import util.Result;

/**
 * Service zum Verwalten von Log-Einträgen in einer JSON-Datei.
 */
@Service
public class LogService {

    private static final File LOG_FILE = new File("./out/logbuch.json");

    /** Normal speichern – wirft DuplicateEntryException, wenn ID schon da ist */
    public void saveEntry(String text)
            throws IOException, DuplicateEntryException {
        LogModel entry = new LogModel(text);
        JsonUtil.add(entry, LogModel.class, LOG_FILE);
    }

    /** Überschreiben eines bestehenden Eintrags */
    public void overwriteEntry(String text) throws IOException {
        LogModel entry = new LogModel(text);
        JsonUtil.update(entry, LogModel.class, LOG_FILE);
    }

    /** Optional: liefert Result statt Exceptions */
    public Result<LogModel, Exception> trySaveEntry(String text) {
        LogModel entry = new LogModel(text);
        try {
            JsonUtil.add(entry, LogModel.class, LOG_FILE);
            return Result.success(entry);
        } catch (Exception e) {
            return Result.error(e);
        }
    }

    public List<LogModel> getAllEntries() throws IOException {
        return JsonUtil.readAll(LogModel.class, LOG_FILE);
    }

    public void deleteEntry(String id) throws IOException {
        JsonUtil.remove(id, LogModel.class, LOG_FILE);
    }

    public void updateEntry(LogModel updated) throws IOException {
        JsonUtil.update(updated, LogModel.class, LOG_FILE);
    }
}
