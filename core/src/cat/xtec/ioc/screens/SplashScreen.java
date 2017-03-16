package cat.xtec.ioc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cat.xtec.ioc.SpaceRace;
import cat.xtec.ioc.helpers.AssetManager;
import cat.xtec.ioc.utils.Settings;


public class SplashScreen implements Screen {

    private Stage stage;
    private SpaceRace game;

    private Label.LabelStyle textStyle;
    private Label textLbl;
    private Label textLbl2;
    private Label textLbl3;
    private Container containerF, containerM, containerD;


    public SplashScreen(SpaceRace game) {

        this.game = game;

        // Creem la càmera de les dimensions del joc
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        // Posant el paràmetre a true configurem la càmera per a
        // que faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(true);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        // Afegim el fons
        stage.addActor(new Image(AssetManager.background));

        // Creem l'estil de l'etiqueta i l'etiqueta
        textStyle = new Label.LabelStyle(AssetManager.font, null);
       // textStyle2 = new Label.LabelStyle(AssetManager.font, null);
        //textStyle3 = new Label.LabelStyle(AssetManager.font, null);
        textLbl = new Label("Facil", textStyle);
        textLbl2 = new Label("Medio", textStyle);
        textLbl3 = new Label("Dificil", textStyle);

        // Creem el contenidor necessari per aplicar-li les accions
         containerF = new Container(textLbl);
         containerM = new Container(textLbl2);
         containerD = new Container(textLbl3);

        containerF.setTransform(true);
        containerF.center();
        containerF.setPosition(Settings.GAME_WIDTH / 6,20);

        containerM.setTransform(true);
        containerM.center();
        containerM.setPosition(Settings.GAME_WIDTH /2 ,20);

        containerD.setTransform(true);
        containerD.center();
        containerD.setPosition(Settings.GAME_WIDTH*5/6,20);



        // Afegim les accions de escalar: primer es fa gran i després torna a l'estat original ininterrompudament
       // containerF.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.scaleTo(1.5f, 1.5f, 1), Actions.scaleTo(1, 1, 1))));
        containerF.setName("Facil");
        stage.addActor(containerF);

      //  containerM.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.scaleTo(1.5f, 1.5f, 1), Actions.scaleTo(1, 1, 1))));
        stage.addActor(containerM);

      //  containerD.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.scaleTo(1.5f, 1.5f, 1), Actions.scaleTo(1, 1, 1))));
        stage.addActor(containerD);

        // Creem la imatge de la nau i li assignem el moviment en horitzontal
        Image spacecraft = new Image(AssetManager.spacecraft);
        float y = Settings.GAME_HEIGHT / 2 + textLbl.getHeight();
        spacecraft.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.moveTo(0 - spacecraft.getWidth(), y), Actions.moveTo(Settings.GAME_WIDTH, y, 5))));

        stage.addActor(spacecraft);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        stage.act(delta);
        Vector3 vector;

        // Si es fa clic en la pantalla, canviem la pantalla
        if (Gdx.input.isTouched()) {
                   // vector = new Vector3 (Gdx.input.getX(), Gdx.input.getY(), 0);
                   // stage.getCamera().unproject(vector);

            if(Gdx.input.getX()/2 >= containerF.getRight()-25 && Gdx.input.getX()/2 <= containerF.getRight()+20 && Gdx.input.getY() >= containerF.getTop() &&Gdx.input.getY() <= containerF.getTop()+30){
                Gdx.app.log ("coordenada Facil has clickado en" + Float.toString (Gdx.input.getX()), Float.toString(containerF.getRight()));
                Settings.setSpacecraftVelocity(50);
                Settings.setAsteroidSpeed(-150);
                Settings.setMaxAsteroid(1.5f);
                game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport()));
                dispose();
            }
            if(Gdx.input.getX()/2 >= containerM.getRight()-25 && Gdx.input.getX()/2 <= containerM.getRight()+23 && Gdx.input.getY() >= containerM.getTop() &&Gdx.input.getY() <= containerM.getTop()+30){
                Gdx.app.log ("coordenada Medio has clickado en" + Float.toString (Gdx.input.getX()), Float.toString(containerM.getRight()));
                Settings.setSpacecraftVelocity(70);
                Settings.setAsteroidSpeed(-125);
                Settings.setMaxAsteroid(2f);
                game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport()));
                dispose();
            }
            if(Gdx.input.getX()/2 >= containerD.getRight()-25 && Gdx.input.getX()/2 <= containerD.getRight()+25 && Gdx.input.getY() >= containerD.getTop() &&Gdx.input.getY() <= containerD.getTop()+30){
                Gdx.app.log ("coordenada Facil has clickado en" + Float.toString (Gdx.input.getX()), Float.toString(containerD.getRight()));
                Settings.setSpacecraftVelocity(200);
                Settings.setAsteroidSpeed(-100);
                Settings.setMaxAsteroid(2.5f);
                game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport()));
                dispose();
            }

           /* Gdx.app.log ("facil dificil medio left", Double.toString(containerF.getPadLeft())+ " " +Double.toString(containerD.getPadLeft())+ " "+Double.toString(containerM.getPadLeft()));
            Gdx.app.log ("facil dificil medio right", Double.toString(containerF.getRight()) + " " +Double.toString(containerD.getRight())+ " "+Double.toString(containerM.getRight()));
            Gdx.app.log ("facil dificil medio top", Double.toString(containerF.getPadTop())+ " " +Double.toString(containerD.getPadTop())+ " "+Double.toString(containerM.getPadTop()));
            Gdx.app.log ("facil dificil medio", Double.toString(containerF.getPadBottom())+ " " +Double.toString(containerD.getPadBottom())+ " "+Double.toString(containerM.getPadBottom()));
            Gdx.app.log ("facil dificil medio PadX", Float.toString(containerF.getPadX())+ " " +Float.toString(containerD.getPadX())+ " "+Float.toString(containerM.getPadX()));
            Gdx.app.log ("coordenada Facil", Float.toString(containerF.getTop()));
*/
/*

            */
        }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
