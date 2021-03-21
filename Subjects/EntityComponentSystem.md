# 

# *Entity Component System:*

This game uses Entity Component System, but what this means?

---

## In the past:

So, in the past, game developers created a system based in OOP (Oriented Objects Programming).
In this system, we have an Entity class that is mother of Player, Enemy... And others types of class like Tile that is mother of Wall, Floor...

This is a map to show how this system may looks like

```mermaid
classDiagram
    class Entity

    Entity <|-- Player
    Entity <|-- Enemy
    Entity<|-- Bullet

    Enemy <|-- Scout
    Enemy <|-- Juggernaut

    Bullet <|-- Ray
    Bullet <|-- Missile

    Tile <|-- Wall
    Tile <|-- Floor
    Wall <|-- BrickWall
    Wall <|-- StoneWall

    Entity : int x
    Entity : int y
    Entity : int width
    Entity : int height
    Entity : update()
    Entity : draw()
    Entity : isColliding(entity1, entity2)
    class Player {
        int x
        int y
        int width
        int height
        update()
        draw()
    }

    class Enemy {
        int x
        int y
        int width
        int height
        update()
        draw()
    }

    class Bullet {
        int x
        int y
        int width
        int height
        update()
        draw()
    }

    class Juggernaut {
        int x
        int y
        int width
        int height
        update()
        draw()
    }

    class Scout {
        int x
        int y
        int width
        int height
        update()
        draw()   
    }

    class Missile {
        int x
        int y
        int width
        int height
        update()
        draw()
    }

    class Ray {
        int x
        int y
        int width
        int height
        update()
        draw()
    }

    class Tile {
        int x
        int y
        draw()
    }

    class Wall {
        int x
        int y
        draw()
    }

    class BrickWall {
        int x
        int y
        draw()
    }

    class StoneWall {
        int x
        int y
        draw()
    }

    class Floor {
        int x
        int y
        draw()
    }
```

| Note: | Tiles are the Game Objects that don't move, which are frozen in the map, like trees, walls, grass... |
| ----- | ---------------------------------------------------------------------------------------------------- |

It seems complicated, doesn't it?
But, luckily, we have Entity Component System nowadays.

---

### Nowadays:

Today, we have a better, faster and easier system, Entity Component System.
According to this system, any game object must extend GameObject class.

But how we make the collisions between player and enemies, or just create a button which is very different from the Player but have the same ancestor?
Well, according this system, Game Objects are like common cars. It has a door, windows, seats... 

But if you want air conditioner, a TV or something unusual, you have to attach it to your car.
This system it's literally that.

If we want to add collision to the Player for example, we attach it a RigidBody Component, if we want to add a shape or a size to it, we attach it a BoxBounds Component...
And this is how (more or less) an Entity Component System looks like:

```mermaid
classDiagram
    Component <|-- Player
    Component <|-- RigidBody
    Component <|-- Sprite

    Component : Name
    Component : Transform
    Component : update(deltaTime)
    Component : draw(graphicsRendering)  

    class Player {
        boolean attacking
        update()
        draw()
    }

    class Sprite {
        int width
        int height
        update()
        draw()
    }

    class RigidBody {
        Vector2 velocity
        update()
        draw()
    }

    class GameObject {
        List<> Components
        addComponent()
        removeComponent()
    }
```

Very simpler and easy to understand, isn't it?

---

This is how Entity Component System works. I hope that I had turned the project more understandable and made you learning curve more interesting and funny.

Thank you :D
