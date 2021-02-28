package com.davidalmarinho.game_objects;

import com.davidalmarinho.game_objects.components.Component;
import com.davidalmarinho.data_structures.Transform;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObject {
    public final String name;
    public Transform transform;
    public List<Component> components;

    public GameObject(String name, Transform transform) {
        components = new ArrayList<>();
        this.name = name;
        this.transform = transform;
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

    public void removeComponent(Component component) {
        components.remove(component);
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
