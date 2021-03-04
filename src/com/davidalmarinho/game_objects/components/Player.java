package com.davidalmarinho.game_objects.components;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Player extends Component {
    private final Sprite sprite;

    public Player(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Graphics2D g2) {
        /*g2.setColor(new Color(255, 100, 100));
        g2.fillRect(gameObject.transform.position.x, gameObject.transform.position.y, 64, 64);*/
        AffineTransform transform = new AffineTransform();
        transform.setToIdentity();
        transform.translate(gameObject.transform.position.x, gameObject.transform.position.y);
        transform.scale(gameObject.transform.scale.x, gameObject.transform.scale.y);
        g2.drawImage(sprite.image, transform, null);
    }
}
