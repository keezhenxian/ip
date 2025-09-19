package bestbot.gui;

import bestbot.Bestbot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * Controller class for MainWindow.fxml.
 * Handles user input and displays dialog messages.
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

    // ⚡ Add Bestbot backend
    private Bestbot bestbot;

    /**
     * Setter for the backend Bestbot instance.
     *
     * @param b the Bestbot instance to use
     */
    public void setBestbot(Bestbot b) {
        this.bestbot = b;
    }

    /**
     * Handles user input when the Send button is pressed.
     * Creates dialog bubbles for both user and bot and adds them to the ListView.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) return;

        String response = bestbot.getResponse(input); // Bestbot backend

        dialogContainer.getItems().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );

        userInput.clear();
    }

    /**
     * Generates a response from the bot.
     * Replace with actual backend logic when integrated.
     *
     * @param input User input string
     * @return Response string from bot
     */
    private String getResponse(String input) {
        return "Echo: " + input; // placeholder for real response
    }
}

