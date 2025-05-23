package controller;

import interfaces.PageController;
import interfaces.UIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import exeption.DuplicateEntryException;
import service.LogService;
import view.LogADDFrame;
import javax.swing.SwingUtilities;
import ui.ModalDialog;

@Component
@Scope("prototype")
public class LogController implements PageController {

    private final LogADDFrame view;
    private final LogService   logService;

    @Autowired
    public LogController(LogADDFrame view, LogService logService) {
        this.view       = view;
        this.logService = logService;
        init();
    }

    private void init() {
        view.getSubmitButton().addActionListener(e -> onSubmit());
    }

    @Override
    public void show() {
        SwingUtilities.invokeLater(() -> view.setVisible(true));
    }

    private void onSubmit() {
    	String text = view.getInputText();
        view.clearResults();
        view.clearInputText();

        
        if (text.isEmpty()) {
            showError("Fehler", "Bitte einen Eintrag eingeben!");
            return;
        }

        try {
            logService.saveEntry(text);
            view.showResult("Eintrag gespeichert:\n" + text);

        } catch (DuplicateEntryException dup) {
            // Exceptions, die UIException implementieren, zeigen wir im Modal dialog
            showConfirmation(
            		dup.getDialogTitle(),
            		dup.getDialogMessage(),
                () -> {
                    try {
                        logService.overwriteEntry(text);
                        view.showResult("Eintrag überschrieben:\n" + text);
                    } catch (Exception ioe) {
                        showError("Fehler", ioe.getMessage());
                    }
                }
            );
        } catch (Exception ex) {
            showError("Unerwarteter Fehler", ex.getMessage());
        }
    }

    /**
     * Einfacher Fehler-Dialog mit einem OK-Button.
     */
    private void showError(String title, String message) {
        SwingUtilities.invokeLater(() ->
            ModalDialog.builder(view)
                .title(title)
                .message(message)
                .addButton("OK", e -> { /* nur schließen */ })
                .size(300, 150)
                .centerOnOwner()
                .show()
        );
    }

    /**
     * Bestätigungs-Dialog mit Ja/Nein-Buttons.
     * Führt onConfirm.run() nur bei "Ja" aus.
     */
    private void showConfirmation(String title, String question, Runnable onConfirm) {
        SwingUtilities.invokeLater(() ->
            ModalDialog.builder(view)
                .title(title)
                .message(question)
                .addButton("Ja", e -> onConfirm.run())
                .addButton("Nein", e -> { /* nichts tun */ })
                .size(300, 150)
                .centerOnOwner()
                .show()
        );
    }
}
