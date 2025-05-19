package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import interfaces.PageController;
import service.LogService;         
import view.LogADDFrame;

import javax.swing.SwingUtilities;

/**
 * Controller für das Log‐Add‐Frame.
 * Er nimmt Button-Klicks entgegen, validiert die Eingabe,
 * ruft den LogService auf und aktualisiert die View.
 */
@Component
@Scope("prototype")
public class LogController implements PageController {

    private final LogADDFrame view;
    private final LogService  logService;

    @Autowired
    public LogController(LogADDFrame view, LogService logService) {
        this.view       = view;
        this.logService = logService;
        init();
    }

    private void init() {
        SwingUtilities.invokeLater(() -> {
            view.getSubmitButton().addActionListener(e -> onSubmit());
            view.setVisible(true);
        });
    }

    private void onSubmit() {
        view.clearResults();
        String text = view.getInputText();
        if (text.isEmpty()) {
            view.showError("Bitte einen Eintrag eingeben!");
            return;
        }

        try {
            logService.saveEntry(text);
            view.showResult("Eintrag gespeichert:\n" + text);
            view.clearInputText();
            
        } catch (Exception ex) {
            view.showError("Fehler beim Speichern: " + ex.getMessage());
            System.out.println("Fehler beim Speichern: " + ex.getMessage());
        }
    }

    /**
     * Aus PageController: zeigt die View im EDT.
     */
    @Override
    public void show() {
        SwingUtilities.invokeLater(() -> view.setVisible(true));
    }
}
