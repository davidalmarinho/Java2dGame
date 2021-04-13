package com.davidalmarinho.ui;

import com.davidalmarinho.game_objects.components.Component;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.utils.Vector2;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MainContainer extends Component {
    // Store all the containers
    private final List<Container> containers;
    // Position
    protected Vector2 position;
    // Items
    protected List<Button> buttons;
    protected ItemsTypes itemsType;
    // Stuff to help render and to help doing a "smarter position"
    protected int rows, numberOfButtons;
    protected int columns;
    protected int widthContainer, heightContainer;
    protected boolean firstTime;
    // static, because I want to hide containers by a synchronized way
    protected static boolean selectingItems;

    public MainContainer() {
        this.containers = new ArrayList<>();
    }

    public MainContainer(List<Container> containers) {
        this.containers = containers;
    }

    @Override
    public void update(float dt) {
        if (GameManager.getInstance().getKeyboardInput().isKeyDown(KeyEvent.VK_E)) {
            selectingItems = !selectingItems;
            firstTime = false;
        }

        for (Container container : containers) {
            container.update(dt);
        }
    }

    public void append(Container container) {
        containers.add(container);
    }

    @Override
    public void render(Graphics2D g2) {
        for (Container container : containers) {
            container.render(g2);
        }
    }

    @Override
    public Component copy() {
        // TODO: 13/04/2021 Probably works... Some day I will probably use it, and, indeed, I will have to fix it or just delete this todo
        return new MainContainer(this.containers);
    }
}
