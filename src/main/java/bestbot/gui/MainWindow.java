package bestbot.gui;

import bestbot.Bestbot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * MainWindow: Controller for MainWindow.fxml.
 *
 * Handles all user interactions including input from the text field
 * and displaying dialog bubbles in the ListView.
 */
public class MainWindow {

    @FXML
    private ListView<DialogBox> dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    private Bestbot bestbot; // ⚡ backend

    /**
     * Sets the backend Bestbot instance.
     *
     * @param b Bestbot instance
     */
    public void setBestbot(Bestbot b) {
        this.bestbot = b;
    }

    /**
     * Initializes the controller.
     *
     * Binds Enter key press to send messages.
     */
    @FXML
    public void initialize() {
        userInput.setOnAction(event -> handleUserInput());
        sendButton.setOnAction(event -> handleUserInput());
    }

    /**
     * Handles user input: creates dialog boxes for user and bot,
     * adds them to the dialog container, and clears the input field.
     *
     * ⚡ Also ensures both pressing Enter and Send button triggers the same behavior.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) return;

        String response = bestbot.getResponse(input);

        // Add dialog boxes to ListView
        dialogContainer.getItems().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );

        userInput.clear();
    }
}

