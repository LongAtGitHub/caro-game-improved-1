package Pages;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.ui.*;

public class CaroBoard {

    public CaroBoard() {
        createFrame();
        createMainPanel();
        createTitleLabel();
        createButtons();
        addComponentsToMainPanel();
        addMainPanelToFrame();
        adjustFrameSizeAndVisibility();
    }

    public GraphicsGroup Title() {
        
    }
    public static void main(String[] args) {
        new CaroBoard();
    }


}
