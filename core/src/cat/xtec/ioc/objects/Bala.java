package cat.xtec.ioc.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cat.xtec.ioc.helpers.AssetManager;
import cat.xtec.ioc.utils.Settings;

/**
 * Created by ALUMNEDAM on 15/03/2017.
 */

public class Bala extends Actor {

    // Distintes posicions de la spacecraft, recta, pujant i baixant
    public static final int BALA_VIS = 0;
    public static final int BALA_IN = 1;
    private float velocityX, velocityY;
    // Paràmetres de la spacecraft
    private Vector2 position;
    private int width, height;
    private int estado;

    private Rectangle collisionRect;

    public Bala(float x, float y, int width, int height) {

        // Inicialitzem els arguments segons la crida del constructor
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        this.velocityX = 0;
        this.velocityY = 0;
        // Inicialitzem la spacecraft a l'estat normal
        estado = BALA_IN;

        // Creem el rectangle de col·lisions
        collisionRect = new Rectangle();

        // Per a la gestio de hit
        /*setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);*/
    }

    public void muestra (){
            estado = BALA_VIS;

    }

    public void nomuestra(){
        estado = BALA_IN;
        reset();
    }

    // Getters dels atributs principals
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        switch (estado) {
            case BALA_VIS:
                if (this.position.x + width + velocityX * delta < Settings.GAME_WIDTH){
                    this.position.x += velocityX * delta;
                }
                break;
        }
    }

    public void goLeft(){
        estado = BALA_VIS;
        velocityX = +Settings.Bala_velocidad;
    }

    // Obtenim el TextureRegion depenent de la posició de la spacecraft
    public TextureRegion getSpacecraftTexture() {

        switch (estado) {

            case BALA_IN:
                return AssetManager.disparoI;
            case BALA_VIS:
                return AssetManager.disparo;
            default:
                return AssetManager.disparoI;
        }
    }

    public void reset() {

        // La posem a la posició inicial i a l'estat normal
        //bala = new Bala(Settings.SPACECRAFT_STARTX+36, Settings.SPACECRAFT_STARTY+5, 50, 20);
        position.x = Settings.SPACECRAFT_STARTX+36;
        position.y = Settings.SPACECRAFT_STARTY+5;
        estado = BALA_IN;
        collisionRect = new Rectangle();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getSpacecraftTexture(), position.x, position.y, width, height);
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }
}
