package com.davidalmarinho.ui;

import com.davidalmarinho.game_objects.components.Component;
import com.davidalmarinho.game_objects.components.Spritesheet;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;
import com.davidalmarinho.utils.WarningFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Container extends MainContainer {
    private final Spritesheet spritesheet;

    public Container(Spritesheet spritesheet, ItemsTypes itemsType, int numberOfButtons, Vector2 position, int rows, int columns) {
        this.spritesheet = spritesheet;
        this.itemsType = itemsType;
        this.numberOfButtons = numberOfButtons;
        this.position = position;
        this.rows = rows;
        this.columns = columns;

        // Boolean responsible to show tip
        firstTime = true;

        // Configure buttons' organization
        buttons = new ArrayList<>();
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
        int countButtons = 0;
        // To make the button's organization and design as we wish
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                Button button = new Button(this.itemsType, spritesheet.sprites.get(x + y * columns), new Vector2(
                        (int)(position.x) + x * Constants.BUTTON_WIDTH
                                + x * Constants.SPACE_BETWEEN_BUTTONS_HORIZONTAL,
                        (int)(position.y) + y * Constants.BUTTON_HEIGHT
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
        for (Button button : buttons) {
            button.update(dt);

            // Hide items after selecting an item
            if (button.selected) {
                selectingItems = false;
                button.selected = false;
            }
        }
    }

    @Override
    public void render(Graphics2D g2) {
        if (selectingItems) {
            g2.setColor(new Color(150, 150, 150));
            g2.fillRect((int)(position.x) - Constants.SPACE_BETWEEN_BUTTONS_HORIZONTAL,
                    (int)(position.y) - Constants.SPACE_BETWEEN_BUTTONS_VERTICAL,
                    widthContainer, heightContainer);

            for (Button button : buttons) {
                button.render(g2);
            }
        }

        if (firstTime) {
            // Show tip
            g2.setColor(new Color(100, 100, 100));
            g2.setFont(new Font("arial", Font.BOLD, 16));
            g2.drawString("Press 'E' to open items' list", 110, Constants.WINDOW_HEIGHT - 80);
        }
    }

    @Override
    public Component copy() {
        return new Container(this.spritesheet, this.itemsType, numberOfButtons, position.copy(), rows, columns);
    }
}
