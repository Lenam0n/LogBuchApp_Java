package exeption;

import interfaces.UIException;

public class DuplicateEntryException extends Exception implements UIException {
    public DuplicateEntryException(String message) {
        super(message);
    }

    @Override
    public String getDialogTitle() {
        return "Eintrag existiert bereits";
    }

    @Override
    public String getDialogMessage() {
        return getMessage();
    }
}
