package com.davidalmarinho.java2dgame.game_objects.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprite extends Component {
    public TextureRegion image;
    private final int width, height;

    /*public Sprite(String path) {
        image = new Texture(Gdx.files.internal(path));
        this.width = image.getWidth();
        this.height = image.getHeight();
    }*/

    public Sprite(TextureRegion textureRegion) {
        this.image = textureRegion;
        this.width = textureRegion.getRegionWidth();
        this.height= textureRegion.getRegionHeight();
    }

    @Override
    public Component copy() {
        return new Sprite(this.image);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void render(SpriteBatch batch) {
        drawQuick(batch, this);
    }
}
