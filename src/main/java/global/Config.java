package global;

import interfaces.PageController;
//import controller.LogADDController;
import controller.LogController;

import java.util.Map;

/**
 * Zuordnung von Button-Label → Controller-Klasse.
 * Die Controller übernehmen dann das Anzeigen ihrer View.
 */
public final class Config {
    private Config() {}

    public static final Map<String, Class<? extends PageController>> TOPIC_CONTROLLERS = Map.of(
        //"ADD Entry",  LogADDController.class,
        "View Logs",  LogController.class
    );
}
