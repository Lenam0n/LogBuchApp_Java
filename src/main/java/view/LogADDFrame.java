package view;

import ui.Button;
import ui.ErrorLabel;
import ui.Frame;
import ui.Input;
import ui.Label;
import ui.Separator;
import ui.Title;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import javax.swing.*;

@Component
@Scope("prototype")
public class LogADDFrame extends Frame {

    // UI-Komponenten als Felder
    private final Title      title;
    private final Input      input;
    private final Button     submitButton;
    private final JPanel     content;   

    public LogADDFrame() {
        super("Demo mit Submit-Button", 400, 200);

        // Komponenten
        title     = Title.create("Logbuch");
        input     = Input.create("Gebe hier deinen Logbuch-Eintrag ein", 100);
        submitButton = Button.create("Submit");
        content      = getContentPanel(); 

        // Zum Layout hinzufügen
        add(0, 0, 1, title)
        .add(1, 0, 1, input)
        .add(0, 1, 2, submitButton);

    }

    public Button    getSubmitButton() { return submitButton; }
    public String     getInputText()    { return input.getText().trim(); }
    public void       clearResults()    { removeRowsStartingAt(2); }   
    public void       clearInputText()    {  input.setText(""); input.repaint(); }   
    public void       showResult(String txt) {
        add(0, 2, 2, Label.create(txt));
        refresh();
    }
    public void       showError(String err) {
        add(0, 2, 2, ErrorLabel.create(err));
        refresh();
    }

    private void removeRowsStartingAt(int row) {
        content.removeAll();
        
        add(0, 0, 1, title)
        .add(1, 0, 1, input)
        .add(0, 1, 2, submitButton);
        refresh();
    }
}
