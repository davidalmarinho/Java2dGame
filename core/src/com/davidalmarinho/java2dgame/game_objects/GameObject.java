package com.davidalmarinho.java2dgame.game_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davidalmarinho.java2dgame.game_objects.components.Component;
import com.davidalmarinho.java2dgame.data_structures.Transform;

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

    public GameObject copy() {
        GameObject gameObject = new GameObject(this.name, this.transform.copy());
        gameObject.depth = this.depth;
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

    public void render(SpriteBatch batch) {
        for (Component c : components) {
            c.render(batch);
        }
    }
}
