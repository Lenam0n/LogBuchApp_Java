// src/main/java/util/JsonUtil.java
package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import exeption.DuplicateEntryException;
import interfaces.Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility-Klasse zum Laden und Speichern von Listen von Model-Objekten in einer JSON-Datei.
 */
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil() { /* nicht instanziierbar */ }

    /**
     * Fügt ein neues Model-Objekt zur JSON-Liste hinzu.
     *
     * @param model     Das neue Objekt. Seine ID darf noch nicht vorhanden sein.
     * @param type      Der Typ des Models (z.B. User.class).
     * @param jsonFile  Die Datei, in der die Liste gespeichert ist.
     * @param <T>       Ein Typ, der Model implementiert.
     * @throws IOException      Bei IO-Fehlern
     * @throws IllegalArgumentException Wenn bereits ein Objekt mit derselben ID existiert.
     */
    public static <T extends Model> void add(
            T model,
            Class<T> type,
            File jsonFile
        ) throws IOException, DuplicateEntryException {
            List<T> list = readAll(type, jsonFile);
            boolean exists = list.stream()
                .anyMatch(m -> m.getId().equals(model.getId()));
            if (exists) {
                throw new DuplicateEntryException(
                    "Eintrag mit ID '" + model.getId() + "' existiert bereits."
                );
            }
            list.add(model);
            writeList(list, jsonFile);
        }

    /**
     * Entfernt ein Model-Objekt anhand seiner ID aus der JSON-Liste.
     *
     * @param id        Die ID des zu entfernenden Objekts.
     * @param type      Der Typ des Models.
     * @param jsonFile  Die Datei, in der die Liste gespeichert ist.
     * @param <T>       Ein Typ, der Model implementiert.
     * @throws IOException Bei IO-Fehlern
     */
    public static <T extends Model> void remove(String id, Class<T> type, File jsonFile) throws IOException {
        List<T> list = readList(type, jsonFile);
        List<T> filtered = list.stream()
                               .filter(m -> !m.getId().equals(id))
                               .collect(Collectors.toList());
        writeList(filtered, jsonFile);
    }

    /**
     * Updated ein existierendes Model-Objekt in der JSON-Liste (identifiziert über ID).
     *
     * @param model     Das aktualisierte Objekt (mit derselben ID wie das zu ersetzende).
     * @param type      Der Typ des Models.
     * @param jsonFile  Die Datei, in der die Liste gespeichert ist.
     * @param <T>       Ein Typ, der Model implementiert.
     * @throws IOException      Bei IO-Fehlern
     * @throws IllegalArgumentException Wenn kein Objekt mit der gegebenen ID gefunden wird.
     */
    public static <T extends Model> void update(T model, Class<T> type, File jsonFile) throws IOException {
        List<T> list = readList(type, jsonFile);
        boolean replaced = false;
        List<T> updated = new ArrayList<>();
        for (T m : list) {
            if (m.getId().equals(model.getId())) {
                updated.add(model);
                replaced = true;
            } else {
                updated.add(m);
            }
        }
        if (!replaced) {
            throw new IllegalArgumentException("Kein Objekt mit ID '" + model.getId() + "' gefunden.");
        }
        writeList(updated, jsonFile);
    }

    // Hilfsmethoden zum Lesen/Schreiben

    private static <T extends Model> List<T> readList(Class<T> type, File jsonFile) throws IOException {
        if (!jsonFile.exists() || jsonFile.length() == 0) {
            return new ArrayList<>();
        }
        // Liest eine List<T> aus der Datei
        return MAPPER.readValue(jsonFile, new TypeReference<List<T>>() {});
    }

    private static <T extends Model> void writeList(List<T> list, File jsonFile) throws IOException {
        // Sorgt dafür, dass das Parent-Verzeichnis existiert
        File parent = jsonFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        MAPPER.writerWithDefaultPrettyPrinter().writeValue(jsonFile, list);
    }
    public static <T extends Model> List<T> readAll(Class<T> type, File jsonFile) throws IOException {
        if (!jsonFile.exists() || jsonFile.length() == 0) {
            return new ArrayList<>();
        }
        return MAPPER.readValue(jsonFile, new TypeReference<List<T>>() {});
    }

}
