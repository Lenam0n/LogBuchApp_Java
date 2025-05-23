package interfaces;

/**
 * Marker für Exceptions, die eigene Titel und Nutzer-Texte mitbringen.
 */
public interface UIException {
    /** Titelzeile im Modal-Dialog */
    String getDialogTitle();
    /** Haupttext im Modal-Dialog */
    String getDialogMessage();
}
