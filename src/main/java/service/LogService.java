package service;

import model.LogModel;
import org.springframework.stereotype.Service;
import util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Service zum Verwalten von Log-Einträgen in einer JSON-Datei.
 */
@Service
public class LogService {

    /** Pfad zur JSON-Datei (relativ zum Arbeitsverzeichnis) */
    private static final File LOG_FILE = new File("./out/logbuch.json");

    /**
     * Speichert einen neuen Log-Eintrag.
     * @param text Der Text des Eintrags.
     */
    public void saveEntry(String text) throws IOException {
        LogModel entry = new LogModel(text);
        JsonUtil.add(entry, LogModel.class, LOG_FILE);
    }

    /**
     * Liest alle vorhandenen Log-Einträge.
     * @return Liste aller Einträge.
     */
    public List<LogModel> getAllEntries() throws IOException {
        return JsonUtil.readAll(LogModel.class, LOG_FILE);
    }

    /**
     * Löscht einen Eintrag anhand seiner ID.
     * @param id Die ID des zu löschenden Eintrags.
     */
    public void deleteEntry(String id) throws IOException {
        JsonUtil.remove(id, LogModel.class, LOG_FILE);
    }

    /**
     * Aktualisiert einen bestehenden Eintrag.
     * @param updatedEntry Das geänderte LogModel (gleiche ID).
     */
    public void updateEntry(LogModel updatedEntry) throws IOException {
        JsonUtil.update(updatedEntry, LogModel.class, LOG_FILE);
    }
}
