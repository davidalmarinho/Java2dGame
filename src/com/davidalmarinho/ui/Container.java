package com.davidalmarinho.ui;

import com.davidalmarinho.game_objects.components.Component;
import com.davidalmarinho.game_objects.components.Spritesheet;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.scenes.LevelEditorScene;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;
import com.davidalmarinho.utils.WarningFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Container extends Component {
    private final List<Button> buttons;
    private final int rows, numberOfButtons;
    private int columns;
    private boolean selectingItems;
    private int widthContainer, heightContainer;
    private boolean firstTime;

    public Container(int numberOfButtons, int rows, int columns) {
        firstTime = true;
        buttons = new ArrayList<>();
        this.numberOfButtons = numberOfButtons;
        this.rows = rows;
        this.columns = columns;
        if (numberOfButtons > rows * columns) {
            new WarningFrame("Something went wrong in " + "'" + getClass() + "'"
                    + "\nExpected buttons: " + numberOfButtons + "\nNumber of buttons: " + rows * columns
                    + "\nPlease, increase the number of rows and columns or decrease the number of buttons to fix.");
            // To adjust the impossible values to possible values
            while (numberOfButtons > rows * this.columns) {
                this.columns++;
            }
        }
        init();
    }

    @Override
    public void init() {
        Spritesheet editSpritesheet = ((LevelEditorScene) GameManager.getInstance().getCurrentScene()).editorSpritesheet;
        int countButtons = 0;
        // To make the button's organization and design as we wish
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                Button button = new Button(editSpritesheet.sprites.get(x + y * columns), new Vector2(
                        Constants.X_CONTAINER + x * Constants.BUTTON_WIDTH
                                + x * Constants.SPACE_BETWEEN_BUTTONS_HORIZONTAL,
                        Constants.Y_CONTAINER + y * Constants.BUTTON_HEIGHT
                                + y * Constants.SPACE_BETWEEN_BUTTONS_VERTICAL));
                buttons.add(button);
                // If we already arrived to the maximum number of buttons, we will stop
                countButtons++;
                if (countButtons == numberOfButtons) {
                    // Setting the size of the selection of items
                    widthContainer = (x + 1) * Constants.BUTTON_WIDTH
                            + x * Constants.SPACE_BETWEEN_BUTTONS_HORIZONTAL
                            // This part is to set space outside of the buttons
                            + Constants.SPACE_BETWEEN_BUTTONS_HORIZONTAL * 2;
                    heightContainer = (y + 1) * Constants.BUTTON_HEIGHT
                            + y * Constants.SPACE_BETWEEN_BUTTONS_VERTICAL
                            + Constants.SPACE_BETWEEN_BUTTONS_VERTICAL * 2;
                    return;
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        if (GameManager.getInstance().getKeyboardInput().isKeyDown(KeyEvent.VK_E)) {
            selectingItems = !selectingItems;
            firstTime = false;
        }

        if (selectingItems) {
            for (Button button : buttons) {
                button.update(dt);

                // Hide items after selecting an item
                if (button.selected) {
                    selectingItems = false;
                    button.selected = false;
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g2) {
        if (selectingItems) {
            g2.setColor(new Color(150, 150, 150));
            g2.fillRect(Constants.X_CONTAINER - Constants.SPACE_BETWEEN_BUTTONS_HORIZONTAL,
                    Constants.Y_CONTAINER - Constants.SPACE_BETWEEN_BUTTONS_VERTICAL,
                    widthContainer, heightContainer);

            for (Button button : buttons) {
                button.render(g2);
            }
        }

        if (firstTime) {
            g2.setColor(new Color(100, 100, 100));
            g2.setFont(new Font("arial", Font.BOLD, 16));
            g2.drawString("Press 'E' to open items' list", 110, Constants.WINDOW_HEIGHT - 80);
        }
    }

    @Override
    public Component copy() {
        return new Container(numberOfButtons, rows, columns);
    }

    public boolean isSelectingItems() {
        return selectingItems;
    }
}
