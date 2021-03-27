package com.davidalmarinho.game_objects;

import com.davidalmarinho.game_objects.components.Component;
import com.davidalmarinho.data_structures.Transform;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameObject {
    public final String name;
    public Transform transform;
    public List<Component> components;
    public int depth;

    public GameObject(String name, Transform transform) {
        components = new ArrayList<>();
        this.name = name;
        this.transform = transform;
        depth = 0;
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component c : components) {
            if (componentClass.isAssignableFrom(c.getClass())) {
                return componentClass.cast(c);
            }
        }
        return null;
    }

    public void addComponent(Component component) {
        components.add(component);
        component.gameObject = this;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        for (Component c : components) {
            if (componentClass.isAssignableFrom(c.getClass())) {
                return;
            }
        }
    }

    /* Creating a comparator to after use for Depth in Renderer class (the gameObject that as a
     * smaller depth is rendered earlier than the gameObject that as a higher depth)
     */
    public static Comparator<GameObject> gameObjectSorter =
            Comparator.comparingInt(gameObject -> gameObject.depth);

    public GameObject copy() {
        GameObject gameObject = new GameObject(this.name, this.transform.copy());
        for (Component c : components) {
            if (c.copy() != null) {
                gameObject.addComponent(c.copy());
            }
        }
        return gameObject;
    }

    public void update(float dt) {
        for (Component c : components) {
            c.update(dt);
        }
    }

    public void render(Graphics2D g2) {
        for (Component c : components) {
            c.render(g2);
        }
    }
}
