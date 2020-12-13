
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * 
 * @author Aoife
 */
public class ExitHandlerClass implements EventHandler<ActionEvent> {
    /**
     * Exits the program
     *
     * @param e
     */
    @Override
    public void handle(ActionEvent e) {
        System.exit(0);
    }
}