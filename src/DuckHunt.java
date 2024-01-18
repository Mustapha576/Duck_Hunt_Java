import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Paths;

public class DuckHunt extends Application{

    Stage window;
    Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7, scene8;
    public static final double scale = 3;
    Selection selection;
    public static final double volume = 0.025;
    MediaPlayer mediaPlayer;
    private double velocityX = 2;
    private double velocityXForBlack = 2;
    private double velocityY = 1;
    private double velocityYForBlack = 1;
    //ImageView duck1, duckForBlack;


    public static void DuckHunt(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        // Set up the primary stage
        window = primaryStage;
        window.setTitle("HUBBM Duck Hunt");
        window.getIcons().add(new Image(new File("assets/favicon/1.png").toURI().toString()));

        // Set up the title scene and display it
        titleScene(window);
        window.setScene(scene1);
        window.show();

    }


    public void titleScene(Stage windowStage) throws Exception{
        StackPane pane = new StackPane();

        // Set up the background image
        Image image = new Image(new File("assets/welcome/1.png").toURI().toString());
        ImageView backgroundImageView = new ImageView(image);

        // Set the dimensions of the background image
        backgroundImageView.setFitWidth(image.getWidth() * scale);
        backgroundImageView.setFitHeight(image.getHeight() * scale);
        pane.getChildren().add(backgroundImageView);


        // Set up the welcome text
        Text welcome = new Text("PRESS ENTER TO START\nPRESS ESC TO EXIT");
        welcome.setTextAlignment(TextAlignment.CENTER);
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        welcome.setFill(Color.ORANGE);
        welcome.setTranslateY(38 * scale);
        pane.getChildren().add(welcome);

        // Set up a timeline for the welcome text animation
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> welcome.setVisible(true)),
                new KeyFrame(Duration.seconds(1), e -> welcome.setVisible(false)),
                new KeyFrame(Duration.seconds(2), e -> welcome.setVisible(true))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Set up the scene with the pane
        scene1 = new Scene(pane);

        // Set up background music
        String musicFile = "assets/effects/Title.mp3";
        Media media = new Media(Paths.get(musicFile).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();


        // Handle key events on the scene
        scene1.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE){
                window.close();
            }
            else if(event.getCode() == KeyCode.ENTER){
                try {
                    backgroundScene(window);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
    public void backgroundScene(Stage backgroundStage){
        StackPane pane = new StackPane();
        selection = new Selection();

        // Set up the background image
        Image backgroundImage = new Image(new File("assets/background/1.png").toURI().toString());
        selection.setBackground(new ImageView(backgroundImage));

        // Set the dimensions of the background image
        selection.getBackground().setFitWidth(backgroundImage.getWidth() * scale);
        selection.getBackground().setFitHeight(backgroundImage.getHeight() * scale);
        pane.getChildren().add(selection.getBackground());

        // Set up the opening text
        Text opening = new Text("USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT");
        opening.setTextAlignment(TextAlignment.CENTER);
        opening.setFont(Font.font("Arial", FontWeight.BOLD, 9 * scale));
        opening.setFill(Color.ORANGE);
        opening.setTranslateY(-93 * scale);
        pane.getChildren().add(opening);


        // Set up the crosshair image
        Image crosshair = new Image(new File("assets/crosshair/1.png").toURI().toString());
        selection.setTypeOfCrosshair(new ImageView(crosshair));

        // Set the dimensions of the crosshair image
        selection.getTypeOfCrosshair().setFitWidth(crosshair.getWidth() * scale);
        selection.getTypeOfCrosshair().setFitHeight(crosshair.getHeight() * scale);
        pane.getChildren().add(selection.getTypeOfCrosshair());


        // Set up the foreground image
        Image foregroundImage = new Image(new File("assets/foreground/1.png").toURI().toString());
        selection.setForeground(new ImageView(foregroundImage));
        // Set the dimensions of the foreground image
        selection.getForeground().setFitWidth(foregroundImage.getWidth() * scale);
        selection.getForeground().setFitHeight(foregroundImage.getHeight() * scale);
        pane.getChildren().add(selection.getForeground());


        // Set up the scene with the pane
        scene2 = new Scene(pane);
        window.setScene(scene2);

        scene2.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE){ // Handle ESCAPE arrow key press
                try { // It gets back to the title scene.
                    titleScene(window);
                    window.setScene(scene1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else if(event.getCode() == KeyCode.RIGHT){ // Handle RIGHT arrow key press
                if (selection.getClockforground() == 6){ // Changing the background foreground and store.
                    selection.setClockforground(1);
                    selection.SelectionForBackground(pane, "background", selection.getClockforground());
                    selection.SelectionForForeground(pane, "foreground", selection.getClockforground());
                    selection.SelectionForCrosshair(pane, "crosshair", selection.getClockforcrosshair());
                    selection.Opening(pane, "USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT", 27, -93 * scale);
                }
                else{ // Changing the background foreground and store.
                    selection.setClockforground(selection.getClockforground() + 1);
                    selection.SelectionForBackground(pane, "background", selection.getClockforground());
                    selection.SelectionForForeground(pane, "foreground", selection.getClockforground());
                    selection.SelectionForCrosshair(pane, "crosshair", selection.getClockforcrosshair());
                    selection.Opening(pane, "USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT", 27, -93 * scale);
                }
            }
            else if(event.getCode() == KeyCode.LEFT){ // Handle RIGHT arrow key press
                if (selection.getClockforground() == 1){ // Changing the background foreground and store.
                    selection.setClockforground(6);
                    selection.SelectionForBackground(pane, "background", selection.getClockforground());
                    selection.SelectionForForeground(pane, "foreground", selection.getClockforground());
                    selection.SelectionForCrosshair(pane, "crosshair", selection.getClockforcrosshair());
                    selection.Opening(pane, "USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT", 27, -93 * scale);
                }
                else{ // Changing the background foreground and store.
                    selection.setClockforground(selection.getClockforground() - 1);
                    selection.SelectionForBackground(pane, "background", selection.getClockforground());
                    selection.SelectionForForeground(pane, "foreground", selection.getClockforground());
                    selection.SelectionForCrosshair(pane, "crosshair", selection.getClockforcrosshair());
                    selection.Opening(pane, "USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT", 27, -93 * scale);
                }
            }
            else if(event.getCode() == KeyCode.UP){ // Handle UP arrow key press
                if (selection.getClockforcrosshair() == 7){ // Changing the cursor and store.
                    selection.setClockforcrosshair(1);
                    selection.SelectionForBackground(pane, "background", selection.getClockforground());
                    selection.SelectionForForeground(pane, "foreground", selection.getClockforground());
                    selection.SelectionForCrosshair(pane, "crosshair", selection.getClockforcrosshair());
                    selection.Opening(pane, "USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT", 27, -93 * scale);
                }
                else{ // Changing the cursor and store.
                    selection.setClockforcrosshair(selection.getClockforcrosshair() + 1);
                    selection.SelectionForBackground(pane, "background", selection.getClockforground());
                    selection.SelectionForForeground(pane, "foreground", selection.getClockforground());
                    selection.SelectionForCrosshair(pane, "crosshair", selection.getClockforcrosshair());
                    selection.Opening(pane, "USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT", 27, -93 * scale);
                }
            }
            else if(event.getCode() == KeyCode.DOWN){ // Handle DOWN arrow key press
                if (selection.getClockforcrosshair() == 1){ // Changing the cursor and store.
                    selection.setClockforcrosshair(7);
                    selection.SelectionForBackground(pane, "background", selection.getClockforground());
                    selection.SelectionForForeground(pane, "foreground", selection.getClockforground());
                    selection.SelectionForCrosshair(pane, "crosshair", selection.getClockforcrosshair());
                    selection.Opening(pane, "USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT", 27, -93 * scale);
                }
                else{ // Changing the cursor and store.
                    selection.setClockforcrosshair(selection.getClockforcrosshair() - 1);
                    selection.SelectionForBackground(pane, "background", selection.getClockforground());
                    selection.SelectionForForeground(pane, "foreground", selection.getClockforground());
                    selection.SelectionForCrosshair(pane, "crosshair", selection.getClockforcrosshair());
                    selection.Opening(pane, "USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT", 27, -93 * scale);
                }
            }
            else if(event.getCode() == KeyCode.ENTER){ // Handle ENTER arrow key press
                try {
                    mediaPlayer.stop(); // Stops the title music.
                    String musicFile = "assets/effects/Intro.mp3"; // Giving the intro music.
                    Media media = new Media(Paths.get(musicFile).toUri().toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setVolume(volume);
                    mediaPlayer.play();

                    mediaPlayer.setOnEndOfMedia(() -> { // When intro music finishes it goes to game screen.
                        try {
                            gamingScene(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }
    public void gamingScene(Stage gamingStage){
        StackPane pane = new StackPane();

        // Load duck images
        Image duckImage4 = new Image(new File("assets/duck_red/4.png").toURI().toString());
        ImageView duck4 = new ImageView(duckImage4);
        duck4.setFitWidth(duckImage4.getWidth() * scale);
        duck4.setFitHeight(duckImage4.getHeight() * scale);

        Image duckImage5 = new Image(new File("assets/duck_red/5.png").toURI().toString());
        Image duckImage6 = new Image(new File("assets/duck_red/6.png").toURI().toString());

        // Add background image to the pane
        pane.getChildren().add(selection.getBackground());


        // Set up duck animation timeline
        Timeline timeline2 = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), event -> duck4.setImage(duckImage4));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.5), event -> duck4.setImage(duckImage5));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(1), event -> duck4.setImage(duckImage6));
        timeline2.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.setAutoReverse(true);

        // Add duck image and start animation
        pane.getChildren().add(duck4);
        timeline2.play();

        // Set up duck translation animation
        duck4.setPreserveRatio(true);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(3), duck4);
        transition.setFromX(-(selection.getBackground().getFitWidth()) / 2);
        transition.setToX(selection.getBackground().getFitWidth() / 2);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();


        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3), event -> duck4.setScaleX(-1.0)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(6), event -> duck4.setScaleX(1.0)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Add foreground image and set cursor
        pane.getChildren().add(selection.getForeground());
        selection.setImageOfCrosshair(new Image(new File("assets/crosshair/" + selection.getClockforcrosshair() + ".png" ).toURI().toString()));
        pane.setCursor(new ImageCursor(selection.getImageOfCrosshair()));

        // Create and set the gaming scene
        scene3 = new Scene(pane);
        window.setScene(scene3);


        // Initialize ammo and duck count
        selection.setShots(3);
        selection.setDuckcount(1);
        selection.Opening(pane, "Level 1/6", 7 * scale, -113 * scale);


        // Add opening text
        Text opening = new Text("Ammo Left: " + selection.getShots());
        opening.setFont(Font.font("Arial", FontWeight.BOLD, 7 * scale));
        opening.setFill(Color.ORANGE);
        opening.setTranslateY(-113 * scale);
        opening.setTranslateX(104 * scale);
        pane.getChildren().add(opening);



        scene3.setOnMouseClicked(event -> {
            if(selection.getShots() > 1 & selection.getDuckcount() == 1){
                // Decrease ammo count and update text
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());


                // Play gunshot sound
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

            }
            else if(selection.getShots() == 1 & selection.getDuckcount() == 1){
                // Decrease ammo count and update text
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot and game over sounds
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String musicFile = "assets/effects/GameOver.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Display "GAME OVER" message and instructions
                selection.Opening(pane, "GAME OVER!!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play again\nPress ESC to exit", 16 * scale, 10 * scale);


                // Set up timeline for alternating display of "GAME OVER" message
                Timeline timelineForLosing = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForLosing.setCycleCount(Timeline.INDEFINITE);
                timelineForLosing.play();


                scene3.setOnKeyPressed(event1 -> {
                    // Handle ESC key press to go back to title scene
                    if(event1.getCode() == KeyCode.ESCAPE){
                        try {
                            timelineForLosing.stop();
                            titleScene(window);
                            window.setScene(scene1);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // Handle ENTER key press to go to another level
                    else if(event1.getCode() == KeyCode.ENTER){
                        try {
                            timelineForLosing.stop();
                            mediaPlayer.stop();
                            gamingScene(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });


            }
            else if(selection.getShots() >= 1 & selection.getDuckcount() == 0){
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());
                selection.setShots(0);


                transition.stop();
                timeline2.stop();
                timeline.stop();

                // Play gunshot, duck fall, and level completed sounds
                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String levelcompleted = "assets/effects/LevelCompleted.mp3";
                Media levelpassed = new Media(Paths.get(levelcompleted).toUri().toString());
                mediaPlayer = new MediaPlayer(levelpassed);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Gives the bird of getting shot image
                duck4.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck4.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duck4.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                duck4.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck4);
                transition1.setFromY(0);
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();


                // Display "YOU WIN" message and instructions
                selection.Opening(pane, "YOU WIN!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play next level", 16 * scale, 2 * scale);
                Timeline timelineForWinning = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForWinning.setCycleCount(Timeline.INDEFINITE);
                timelineForWinning.play();

                scene3.setOnKeyPressed(event1 -> {
                    // Handle ENTER key press to go to the next level
                    if(event1.getCode() == KeyCode.ENTER){
                        mediaPlayer.stop();
                        try {
                            timeline.stop();
                            timelineForWinning.stop();
                            gamingScene2(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }

        });


        duck4.setOnMouseClicked(event -> {
            // If amount of ammo is bigger than 0, than it makes the duck count 0.
            if(selection.getShots() > 0){
                selection.setDuckcount(0);
            }
        });

    }

    public void gamingScene2(Stage gamingStage2){
        StackPane pane = new StackPane();

        // Load duck images
        Image duckImage1 = new Image(new File("assets/duck_blue/1.png").toURI().toString());
        ImageView duck1 = new ImageView(duckImage1);
        duck1.setScaleY(duck1.getScaleY() * -1.0);
        duck1.setFitWidth(duckImage1.getWidth() * scale);
        duck1.setFitHeight(duckImage1.getHeight() * scale);
        duck1.setTranslateX(-93 * scale);
        duck1.setTranslateY(83 * scale);

        Image duckImage2 = new Image(new File("assets/duck_blue/2.png").toURI().toString());
        Image duckImage3 = new Image(new File("assets/duck_blue/3.png").toURI().toString());


        // Add background to the pane
        pane.getChildren().add(selection.getBackground());

        // Set up duck animation timeline
        Timeline timeline2 = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), event -> duck1.setImage(duckImage1));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.5), event -> duck1.setImage(duckImage2));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(1), event -> duck1.setImage(duckImage3));
        timeline2.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.setAutoReverse(true);
        pane.getChildren().add(duck1);
        timeline2.play();


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            // Update the position
            duck1.setTranslateX(duck1.getTranslateX() + velocityX);
            duck1.setTranslateY(duck1.getTranslateY() + velocityY);

            // Check if the image reaches the left or right edge
            if (duck1.getTranslateX() >= 116 * scale) {
                // Reverse the velocity to change direction
                velocityX *= -1;

                // Reflect the image horizontally
                duck1.setScaleX(duck1.getScaleX() * -1.0);
            }

            if (duck1.getTranslateX() <= -116 * scale){
                // Reverse the velocity to change direction
                // Reflect the image horizontally
                velocityX *= -1;
                duck1.setScaleX(duck1.getScaleX() * -1.0);
            }

            // Check if the image reaches the top or bottom edge
            if (duck1.getTranslateY() <= -107 * scale) {
                // Reverse the velocity to change direction
                velocityY *= -1;

                // Reflect the image vertically
                duck1.setScaleY(duck1.getScaleY() * -1.0);
            }

            if (duck1.getTranslateY() >= 107 * scale){
                // Reverse the velocity to change direction
                // Reflect the image vertically
                velocityY *= -1;
                duck1.setScaleY(duck1.getScaleY() * -1.0);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Add foreground image and set cursor
        pane.getChildren().add(selection.getForeground());
        selection.setImageOfCrosshair(new Image(new File("assets/crosshair/" + selection.getClockforcrosshair() + ".png" ).toURI().toString()));
        pane.setCursor(new ImageCursor(selection.getImageOfCrosshair()));


        // Create and set the gaming scene
        scene4 = new Scene(pane);
        window.setScene(scene4);


        // Initialize ammo and duck count
        selection.setShots(3);
        selection.setDuckcount(1);

        // Add opening text
        selection.Opening(pane, "Level 2/6", 7 * scale, -113 * scale);
        Text opening = new Text("Ammo Left: " + selection.getShots());
        opening.setFont(Font.font("Arial", FontWeight.BOLD, 7 * scale));
        opening.setFill(Color.ORANGE);
        opening.setTranslateY(-113 * scale);
        opening.setTranslateX(106 * scale);
        pane.getChildren().add(opening);

        scene4.setOnMouseClicked(event -> {
            if(selection.getShots() > 1 & selection.getDuckcount() == 1){
                // Decrease ammo count and update text
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot sound
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

            }
            else if(selection.getShots() == 1 & selection.getDuckcount() == 1){
                // Decrease ammo count and update text
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot and game over sounds
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String musicFile = "assets/effects/GameOver.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Display "GAME OVER" message and instructions
                selection.Opening(pane, "GAME OVER!!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play again\nPress ESC to exit", 16 * scale, 10 * scale);

                // Set up timeline for alternating display of "GAME OVER" message
                Timeline timelineForLosing = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForLosing.setCycleCount(Timeline.INDEFINITE);
                timelineForLosing.play();


                scene4.setOnKeyPressed(event1 -> {
                    // Handle ESC key press to go back to title scene
                    if(event1.getCode() == KeyCode.ESCAPE){
                        try {
                            duck1.setImage(new Image(new File("assets/duck_blue/1.png").toURI().toString()));
                            timeline2.stop();
                            timelineForLosing.stop();
                            timeline.stop();
                            titleScene(window);
                            window.setScene(scene1);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // Handle ENTER key press to restart the game
                    else if(event1.getCode() == KeyCode.ENTER){
                        try {
                            duck1.setImage(new Image(new File("assets/duck_blue/1.png").toURI().toString()));
                            timeline2.stop();
                            timeline.stop();
                            timelineForLosing.stop();
                            mediaPlayer.stop();
                            gamingScene(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });


            }
            else if(selection.getShots() >= 1 & selection.getDuckcount() == 0){
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());
                selection.setShots(0);
                timeline2.stop();

                // Play gunshot, duck fall, and level completed sounds
                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String levelcompleted = "assets/effects/LevelCompleted.mp3";
                Media levelpassed = new Media(Paths.get(levelcompleted).toUri().toString());
                mediaPlayer = new MediaPlayer(levelpassed);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                duck1.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                Timeline timeline3 = new Timeline();
                timeline3.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck1.setImage(new Image(new File("assets/duck_blue/8.png").toURI().toString()));
                }));
                timeline3.play();

                duck1.setImage(new Image(new File("assets/duck_blue/8.png").toURI().toString()));
                duck1.setPreserveRatio(true);
                timeline.stop();
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck1);
                transition1.setFromY(duck1.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();

                // Display "YOU WIN" message and instructions
                selection.Opening(pane, "YOU WIN!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play next level", 16 * scale, 2 * scale);
                Timeline timelineForWinning = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForWinning.setCycleCount(Timeline.INDEFINITE);
                timelineForWinning.play();

                scene4.setOnKeyPressed(event1 -> {
                    // Handle ENTER key press to go to the next level
                    if(event1.getCode() == KeyCode.ENTER){
                        timelineForWinning.stop();
                        mediaPlayer.stop();
                        try {
                            duck1.setImage(new Image(new File("assets/duck_blue/1.png").toURI().toString()));
                            gamingScene3(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }

        });
        duck1.setOnMouseClicked(event -> {
            // If amount of ammo is bigger than 0, than it makes the duck count 0.
            if(selection.getShots() > 0){
                selection.setDuckcount(0);
            }
        });
    }

    public void gamingScene3(Stage gamingStage3){
        StackPane pane = new StackPane();

        // Load duck images
        Image duckImage4 = new Image(new File("assets/duck_red/4.png").toURI().toString());
        ImageView duck4 = new ImageView(duckImage4);
        duck4.setFitWidth(duckImage4.getWidth() * scale);
        duck4.setFitHeight(duckImage4.getHeight() * scale);

        Image duckImage5 = new Image(new File("assets/duck_red/5.png").toURI().toString());
        Image duckImage6 = new Image(new File("assets/duck_red/6.png").toURI().toString());

        Image duckImage4ForBlack = new Image(new File("assets/duck_black/4.png").toURI().toString());
        ImageView duck4Black = new ImageView(duckImage4ForBlack);
        duck4Black.setScaleX(-1.0);
        duck4Black.setFitWidth(duckImage4.getWidth() * scale);
        duck4Black.setFitHeight(duckImage4.getHeight() * scale);
        duck4Black.setTranslateY(-45 * scale);

        Image duckImage5ForBlack = new Image(new File("assets/duck_black/5.png").toURI().toString());
        Image duckImage6ForBlack = new Image(new File("assets/duck_black/6.png").toURI().toString());

        // Add background image to the pane
        pane.getChildren().add(selection.getBackground());

        // Set up duck animation timeline
        Timeline timeline2 = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), event -> duck4.setImage(duckImage4));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.5), event -> duck4.setImage(duckImage5));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(1), event -> duck4.setImage(duckImage6));
        timeline2.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.setAutoReverse(true);
        pane.getChildren().add(duck4);
        timeline2.play();

        // Set up duck movement transition and animation timeline
        duck4.setPreserveRatio(true);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(3), duck4);
        transition.setFromX(-(selection.getBackground().getFitWidth()) / 2);
        transition.setToX(selection.getBackground().getFitWidth() / 2);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3), event -> duck4.setScaleX(-1.0)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(6), event -> duck4.setScaleX(1.0)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Set up black duck animation timeline
        Timeline timeline2ForBlack = new Timeline();
        KeyFrame keyFrame1ForBlack = new KeyFrame(Duration.seconds(0), event -> duck4Black.setImage(duckImage4ForBlack));
        KeyFrame keyFrame2ForBlack = new KeyFrame(Duration.seconds(0.5), event -> duck4Black.setImage(duckImage5ForBlack));
        KeyFrame keyFrame3ForBlack = new KeyFrame(Duration.seconds(1), event -> duck4Black.setImage(duckImage6ForBlack));
        timeline2ForBlack.getKeyFrames().addAll(keyFrame1ForBlack, keyFrame2ForBlack, keyFrame3ForBlack);
        timeline2ForBlack.setCycleCount(Timeline.INDEFINITE);
        timeline2ForBlack.setAutoReverse(true);
        pane.getChildren().add(duck4Black);
        timeline2ForBlack.play();

        // Set up black duck movement transition and animation timeline
        duck4Black.setPreserveRatio(true);
        TranslateTransition transitionForBlack = new TranslateTransition(Duration.seconds(3), duck4Black);
        transitionForBlack.setFromX(selection.getBackground().getFitWidth() / 2);
        transitionForBlack.setToX(-(selection.getBackground().getFitWidth() / 2));
        transitionForBlack.setCycleCount(TranslateTransition.INDEFINITE);
        transitionForBlack.setAutoReverse(true);
        transitionForBlack.play();

        Timeline timelineForBlack= new Timeline();
        timelineForBlack.getKeyFrames().add(new KeyFrame(Duration.seconds(3), event -> duck4Black.setScaleX(1.0)));
        timelineForBlack.getKeyFrames().add(new KeyFrame(Duration.seconds(6), event -> duck4Black.setScaleX(-1.0)));
        timelineForBlack.setCycleCount(Timeline.INDEFINITE);
        timelineForBlack.play();

        // Add foreground image to the pane
        pane.getChildren().add(selection.getForeground());
        // Set up crosshair cursor and add it to the pane
        selection.setImageOfCrosshair(new Image(new File("assets/crosshair/" + selection.getClockforcrosshair() + ".png" ).toURI().toString()));
        pane.setCursor(new ImageCursor(selection.getImageOfCrosshair()));

        // Create and set the scene for level 3
        scene5 = new Scene(pane);
        window.setScene(scene5);

        // Set initial ammo count and duck count for level 3
        selection.setShots(6);
        selection.setDuckcount(2);
        // Display level information
        selection.Opening(pane, "Level 3/6", 7 * scale, -113 * scale);

        // Display ammo count
        Text opening = new Text("Ammo Left: " + selection.getShots());

        // Add the opening text to the pane
        opening.setFont(Font.font("Arial", FontWeight.BOLD, 7 * scale));
        opening.setFill(Color.ORANGE);
        opening.setTranslateY(-113 * scale);
        opening.setTranslateX(104 * scale);
        pane.getChildren().add(opening);

        scene5.setOnMouseClicked(event -> {
            // Check if shots are remaining and two duck is present
            if(selection.getShots() > 1 & selection.getDuckcount() > 0){
                // Decrease ammo count
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot sound effect
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

            }
            // Check if it's the last shot and duck left
            else if(selection.getShots() == 1 & selection.getDuckcount() > 0){
                // Decrease ammo count
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot sound effect
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();


                // Play game over sound effect
                String musicFile = "assets/effects/GameOver.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Display "GAME OVER" message
                selection.Opening(pane, "GAME OVER!!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play again\nPress ESC to exit", 16 * scale, 10 * scale);

                // Set up timeline for flashing "GAME OVER" text
                Timeline timelineForLosing = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForLosing.setCycleCount(Timeline.INDEFINITE);
                timelineForLosing.play();


                scene5.setOnKeyPressed(event1 -> {
                    // Handle ESCAPE key press to return to title scene
                    if(event1.getCode() == KeyCode.ESCAPE){
                        try {
                            timelineForLosing.stop();
                            titleScene(window);
                            window.setScene(scene1);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // Handle ENTER key press to start over
                    else if(event1.getCode() == KeyCode.ENTER){
                        try {
                            timelineForLosing.stop();
                            mediaPlayer.stop();
                            gamingScene(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });


            }
            else if(selection.getShots() >= 0 & selection.getDuckcount() == 0){
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());
                // Reset ammo count to zero

                // Stop duck movement transition and animation timelines
                transition.stop();
                timeline2.stop();
                timeline.stop();

                // Play gunshot sound effect
                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play level completed sound effect
                String levelcompleted = "assets/effects/LevelCompleted.mp3";
                Media levelpassed = new Media(Paths.get(levelcompleted).toUri().toString());
                mediaPlayer = new MediaPlayer(levelpassed);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Display level completion instructions
                selection.Opening(pane, "YOU WIN!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play next level", 16 * scale, 2 * scale);
                Timeline timelineForWinning = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForWinning.setCycleCount(Timeline.INDEFINITE);
                timelineForWinning.play();

                scene5.setOnKeyPressed(event1 -> {
                    // Handle ENTER key press to go to next level
                    if(event1.getCode() == KeyCode.ENTER){
                        mediaPlayer.stop();
                        try {
                            timelineForWinning.stop();
                            gamingScene4(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }

        });
        duck4.setOnMouseClicked(event -> {
            if(selection.getShots() > 0){
                selection.setDuckcount(selection.getDuckcount() - 1);

                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play duck falls sound effect
                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                transition.stop();
                timeline.stop();
                timeline2.stop();

                // Gives the bird of getting shot image
                duck4.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck4.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duck4.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                duck4.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck4);
                transition1.setFromY(0);
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();

            }
        });

        duck4Black.setOnMouseClicked(event -> {
            if(selection.getShots() > 0){
                selection.setDuckcount(selection.getDuckcount() - 1);


                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play duck falls sound effect
                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                transitionForBlack.stop();
                timelineForBlack.stop();
                timeline2ForBlack.stop();

                // Gives the bird of getting shot image
                duck4Black.setImage(new Image(new File("assets/duck_black/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck4Black.setImage(new Image(new File("assets/duck_black/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duck4Black.setImage(new Image(new File("assets/duck_black/8.png").toURI().toString()));
                duck4Black.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck4Black);
                transition1.setFromY(duck4Black.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();
            }
        });
    }

    public void gamingScene4(Stage gamingStage4){
        StackPane pane = new StackPane();

        // Load duck images
        Image duckImage1 = new Image(new File("assets/duck_red/1.png").toURI().toString());
        ImageView duck1 = new ImageView(duckImage1);
        //duck1.setScaleY(duck1.getScaleY() * -1.0);
        duck1.setScaleX(duck1.getScaleX() * -1.0);
        duck1.setFitWidth(duckImage1.getWidth() * scale);
        duck1.setFitHeight(duckImage1.getHeight() * scale);
        duck1.setTranslateX(-93 * scale);
        duck1.setTranslateY(83 * scale);

        Image duckImage2 = new Image(new File("assets/duck_red/2.png").toURI().toString());
        Image duckImage3 = new Image(new File("assets/duck_red/3.png").toURI().toString());

        // Load duck images
        Image duckImage1ForBlack = new Image(new File("assets/duck_black/1.png").toURI().toString());
        ImageView duckForBlack = new ImageView(duckImage1ForBlack);
        duckForBlack.setScaleY(duckForBlack.getScaleY() * -1.0);
        duckForBlack.setFitWidth(duckImage1ForBlack.getWidth() * scale);
        duckForBlack.setFitHeight(duckImage1ForBlack.getHeight() * scale);
        duckForBlack.setTranslateX(93 * scale);
        duckForBlack.setTranslateY(-50 * scale);

        Image duckImage2ForBlack = new Image(new File("assets/duck_black/2.png").toURI().toString());
        Image duckImage3ForBlack = new Image(new File("assets/duck_black/3.png").toURI().toString());

        // Add background to the pane
        pane.getChildren().add(selection.getBackground());

        // Set up duck animation timeline
        Timeline timeline2 = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), event -> duck1.setImage(duckImage1));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.5), event -> duck1.setImage(duckImage2));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(1), event -> duck1.setImage(duckImage3));
        timeline2.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.setAutoReverse(true);
        pane.getChildren().add(duck1);
        timeline2.play();

        // Set up duck animation timeline
        Timeline timeline2ForBlack = new Timeline();
        KeyFrame keyFrame1Black = new KeyFrame(Duration.seconds(0), event -> duckForBlack.setImage(duckImage1ForBlack));
        KeyFrame keyFrame2Black = new KeyFrame(Duration.seconds(0.5), event -> duckForBlack.setImage(duckImage2ForBlack));
        KeyFrame keyFrame3Black = new KeyFrame(Duration.seconds(1), event -> duckForBlack.setImage(duckImage3ForBlack));
        timeline2ForBlack.getKeyFrames().addAll(keyFrame1Black, keyFrame2Black, keyFrame3Black);
        timeline2ForBlack.setCycleCount(Timeline.INDEFINITE);
        timeline2ForBlack.setAutoReverse(true);
        pane.getChildren().add(duckForBlack);
        timeline2ForBlack.play();



        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            // Update the position
            duck1.setTranslateX(duck1.getTranslateX() + velocityX);
            duck1.setTranslateY(duck1.getTranslateY() + velocityY);

            // Check if the image reaches the left or right edge
            if (duck1.getTranslateX() >= 116 * scale) {
                // Reverse the velocity to change direction
                velocityX *= -1;

                // Reflect the image horizontally
                duck1.setScaleX(duck1.getScaleX() * -1.0);
            }

            if (duck1.getTranslateX() <= -116 * scale){
                // Reverse the velocity to change direction
                // Reflect the image horizontally
                velocityX *= -1;
                duck1.setScaleX(duck1.getScaleX() * -1.0);
            }

            // Check if the image reaches the top or bottom edge
            if (duck1.getTranslateY() <= -107 * scale) {
                // Reverse the velocity to change direction
                velocityY *= -1;

                // Reflect the image vertically
                duck1.setScaleY(duck1.getScaleY() * -1.0);
            }

            if (duck1.getTranslateY() >= 107 * scale){
                // Reverse the velocity to change direction
                // Reflect the image vertically
                velocityY *= -1;
                duck1.setScaleY(duck1.getScaleY() * -1.0);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline timelineForBlack = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            // Update the position
            duckForBlack.setTranslateX(duckForBlack.getTranslateX() + velocityXForBlack);
            duckForBlack.setTranslateY(duckForBlack.getTranslateY() + velocityYForBlack);

            // Check if the image reaches the left or right edge
            if (duckForBlack.getTranslateX() >= 116 * scale) {
                // Reverse the velocity to change direction
                velocityXForBlack *= -1;

                // Reflect the image horizontally
                duckForBlack.setScaleX(duckForBlack.getScaleX() * -1.0);
            }

            if (duckForBlack.getTranslateX() <= -116 * scale){
                // Reverse the velocity to change direction
                // Reflect the image horizontally
                velocityXForBlack *= -1;
                duckForBlack.setScaleX(duckForBlack.getScaleX() * -1.0);
            }

            // Check if the image reaches the top or bottom edge
            if (duckForBlack.getTranslateY() <= -107 * scale) {
                // Reverse the velocity to change direction
                velocityYForBlack *= -1;

                // Reflect the image vertically
                duckForBlack.setScaleY(duckForBlack.getScaleY() * -1.0);
            }

            if (duckForBlack.getTranslateY() >= 107 * scale){
                // Reverse the velocity to change direction
                // Reflect the image vertically
                velocityYForBlack *= -1;
                duckForBlack.setScaleY(duckForBlack.getScaleY() * -1.0);
            }
        }));
        timelineForBlack.setCycleCount(Animation.INDEFINITE);
        timelineForBlack.play();


        // Add foreground image and set cursor
        pane.getChildren().add(selection.getForeground());
        selection.setImageOfCrosshair(new Image(new File("assets/crosshair/" + selection.getClockforcrosshair() + ".png" ).toURI().toString()));
        pane.setCursor(new ImageCursor(selection.getImageOfCrosshair()));


        // Create and set the gaming scene
        scene6 = new Scene(pane);
        window.setScene(scene6);


        // Initialize ammo and duck count
        selection.setShots(6);
        selection.setDuckcount(2);

        // Add opening text
        selection.Opening(pane, "Level 4/6", 7 * scale, -113 * scale);
        Text opening = new Text("Ammo Left: " + selection.getShots());
        opening.setFont(Font.font("Arial", FontWeight.BOLD, 7 * scale));
        opening.setFill(Color.ORANGE);
        opening.setTranslateY(-113 * scale);
        opening.setTranslateX(106 * scale);
        pane.getChildren().add(opening);

        scene6.setOnMouseClicked(event -> {
            if(selection.getShots() > 1 & selection.getDuckcount() > 0){
                // Decrease ammo count and update text
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot sound
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

            }
            else if(selection.getShots() == 1 & selection.getDuckcount() > 0){
                // Decrease ammo count and update text
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot and game over sounds
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String musicFile = "assets/effects/GameOver.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Display "GAME OVER" message and instructions
                selection.Opening(pane, "GAME OVER!!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play again\nPress ESC to exit", 16 * scale, 10 * scale);

                // Set up timeline for alternating display of "GAME OVER" message
                Timeline timelineForLosing = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForLosing.setCycleCount(Timeline.INDEFINITE);
                timelineForLosing.play();


                scene6.setOnKeyPressed(event1 -> {
                    // Handle ESC key press to go back to title scene
                    if(event1.getCode() == KeyCode.ESCAPE){
                        try {
                            timelineForLosing.stop();
                            timeline.stop();
                            titleScene(window);
                            window.setScene(scene1);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // Handle ENTER key press to restart the game
                    else if(event1.getCode() == KeyCode.ENTER){
                        try {
                            timeline.stop();
                            timelineForLosing.stop();
                            mediaPlayer.stop();
                            gamingScene(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });


            }
            else if(selection.getShots() >= 0 & selection.getDuckcount() == 0){
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());
                selection.setShots(0);
                timeline2.stop();

                // Play gunshot, duck fall, and level completed sounds
                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String levelcompleted = "assets/effects/LevelCompleted.mp3";
                Media levelpassed = new Media(Paths.get(levelcompleted).toUri().toString());
                mediaPlayer = new MediaPlayer(levelpassed);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                duck1.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                Timeline timeline3 = new Timeline();
                timeline3.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck1.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                }));
                timeline3.play();

                duck1.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                duck1.setPreserveRatio(true);
                timeline.stop();
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck1);
                transition1.setFromY(duck1.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();

                // Display "YOU WIN" message and instructions
                selection.Opening(pane, "YOU WIN!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play next level", 16 * scale, 2 * scale);
                Timeline timelineForWinning = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForWinning.setCycleCount(Timeline.INDEFINITE);
                timelineForWinning.play();

                scene6.setOnKeyPressed(event1 -> {
                    // Handle ENTER key press to go to the next level
                    if(event1.getCode() == KeyCode.ENTER){
                        timelineForWinning.stop();
                        mediaPlayer.stop();
                        try {
                            gamingScene5(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }

        });

        duck1.setOnMouseClicked(event -> {
            if(selection.getShots() > 0){
                selection.setDuckcount(selection.getDuckcount() - 1);

                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play duck falls sound effect
                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                timeline.stop();
                timeline2.stop();

                // Gives the bird of getting shot image
                duck1.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck1.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duck1.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                duck1.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck1);
                transition1.setFromY(duck1.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();

            }
        });

        duckForBlack.setOnMouseClicked(event -> {
            if(selection.getShots() > 0){
                selection.setDuckcount(selection.getDuckcount() - 1);

                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play duck falls sound effect
                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                timelineForBlack.stop();
                timeline2ForBlack.stop();

                // Gives the bird of getting shot image
                duckForBlack.setImage(new Image(new File("assets/duck_black/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duckForBlack.setImage(new Image(new File("assets/duck_black/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duckForBlack.setImage(new Image(new File("assets/duck_black/8.png").toURI().toString()));
                duckForBlack.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duckForBlack);
                transition1.setFromY(duckForBlack.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();
            }
        });

    }

    public void gamingScene5(Stage gamingStage5){
        StackPane pane = new StackPane();

        // Load duck images
        Image duckImage4ForRed = new Image(new File("assets/duck_red/4.png").toURI().toString());
        ImageView duck4Red = new ImageView(duckImage4ForRed);
        duck4Red.setFitWidth(duckImage4ForRed.getWidth() * scale);
        duck4Red.setFitHeight(duckImage4ForRed.getHeight() * scale);

        Image duckImage5ForRed = new Image(new File("assets/duck_red/5.png").toURI().toString());
        Image duckImage6ForRed = new Image(new File("assets/duck_red/6.png").toURI().toString());

        Image duckImage4ForBlack = new Image(new File("assets/duck_black/4.png").toURI().toString());
        ImageView duck4Black = new ImageView(duckImage4ForBlack);
        duck4Black.setScaleX(-1.0);
        duck4Black.setFitWidth(duckImage4ForBlack.getWidth() * scale);
        duck4Black.setFitHeight(duckImage4ForBlack.getHeight() * scale);
        duck4Black.setTranslateY(-45 * scale);

        Image duckImage5ForBlack = new Image(new File("assets/duck_black/5.png").toURI().toString());
        Image duckImage6ForBlack = new Image(new File("assets/duck_black/6.png").toURI().toString());

        // Load duck images
        Image duckImage1 = new Image(new File("assets/duck_blue/1.png").toURI().toString());
        ImageView duck1 = new ImageView(duckImage1);
        duck1.setFitWidth(duckImage1.getWidth() * scale);
        duck1.setFitHeight(duckImage1.getHeight() * scale);
        duck1.setTranslateX(-93 * scale);
        duck1.setTranslateY(83 * scale);

        Image duckImage2 = new Image(new File("assets/duck_blue/2.png").toURI().toString());
        Image duckImage3 = new Image(new File("assets/duck_blue/3.png").toURI().toString());

        // Add background image to the pane
        pane.getChildren().add(selection.getBackground());

        // Set up duck animation timeline
        Timeline timeline2 = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), event -> duck1.setImage(duckImage1));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.5), event -> duck1.setImage(duckImage2));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(1), event -> duck1.setImage(duckImage3));
        timeline2.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.setAutoReverse(true);
        pane.getChildren().add(duck1);
        timeline2.play();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            // Update the position
            duck1.setTranslateX(duck1.getTranslateX() + velocityX);
            duck1.setTranslateY(duck1.getTranslateY() + velocityY);

            // Check if the image reaches the left or right edge
            if (duck1.getTranslateX() >= 116 * scale) {
                // Reverse the velocity to change direction
                velocityX *= -1;

                // Reflect the image horizontally
                duck1.setScaleX(duck1.getScaleX() * -1.0);
            }

            if (duck1.getTranslateX() <= -116 * scale){
                // Reverse the velocity to change direction
                // Reflect the image horizontally
                velocityX *= -1;
                duck1.setScaleX(duck1.getScaleX() * -1.0);
            }

            // Check if the image reaches the top or bottom edge
            if (duck1.getTranslateY() <= -107 * scale) {
                // Reverse the velocity to change direction
                velocityY *= -1;

                // Reflect the image vertically
                duck1.setScaleY(duck1.getScaleY() * -1.0);
            }

            if (duck1.getTranslateY() >= 107 * scale){
                // Reverse the velocity to change direction
                // Reflect the image vertically
                velocityY *= -1;
                duck1.setScaleY(duck1.getScaleY() * -1.0);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        // Set up duck animation timeline
        Timeline timeline2ForRed = new Timeline();
        KeyFrame keyFrame1ForRed = new KeyFrame(Duration.seconds(0), event -> duck4Red.setImage(duckImage4ForRed));
        KeyFrame keyFrame2ForRed = new KeyFrame(Duration.seconds(0.5), event -> duck4Red.setImage(duckImage5ForRed));
        KeyFrame keyFrame3ForRed = new KeyFrame(Duration.seconds(1), event -> duck4Red.setImage(duckImage6ForRed));
        timeline2ForRed.getKeyFrames().addAll(keyFrame1ForRed, keyFrame2ForRed, keyFrame3ForRed);
        timeline2ForRed.setCycleCount(Timeline.INDEFINITE);
        timeline2ForRed.setAutoReverse(true);
        timeline2ForRed.play();

        // Set up duck movement transition and animation timeline
        duck4Red.setPreserveRatio(true);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(3), duck4Red);
        transition.setFromX(-(selection.getBackground().getFitWidth()) / 2);
        transition.setToX(selection.getBackground().getFitWidth() / 2);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();

        Timeline timelineForRed = new Timeline();
        timelineForRed.getKeyFrames().add(new KeyFrame(Duration.seconds(3), event -> duck4Red.setScaleX(-1.0)));
        timelineForRed.getKeyFrames().add(new KeyFrame(Duration.seconds(6), event -> duck4Red.setScaleX(1.0)));
        timelineForRed.setCycleCount(Timeline.INDEFINITE);
        pane.getChildren().add(duck4Red);
        timelineForRed.play();

        // Set up black duck animation timeline
        Timeline timeline2ForBlack = new Timeline();
        KeyFrame keyFrame1ForBlack = new KeyFrame(Duration.seconds(0), event -> duck4Black.setImage(duckImage4ForBlack));
        KeyFrame keyFrame2ForBlack = new KeyFrame(Duration.seconds(0.5), event -> duck4Black.setImage(duckImage5ForBlack));
        KeyFrame keyFrame3ForBlack = new KeyFrame(Duration.seconds(1), event -> duck4Black.setImage(duckImage6ForBlack));
        timeline2ForBlack.getKeyFrames().addAll(keyFrame1ForBlack, keyFrame2ForBlack, keyFrame3ForBlack);
        timeline2ForBlack.setCycleCount(Timeline.INDEFINITE);
        timeline2ForBlack.setAutoReverse(true);
        pane.getChildren().add(duck4Black);
        timeline2ForBlack.play();

        // Set up black duck movement transition and animation timeline
        duck4Black.setPreserveRatio(true);
        TranslateTransition transitionForBlack = new TranslateTransition(Duration.seconds(3), duck4Black);
        transitionForBlack.setFromX(selection.getBackground().getFitWidth() / 2);
        transitionForBlack.setToX(-(selection.getBackground().getFitWidth() / 2));
        transitionForBlack.setCycleCount(TranslateTransition.INDEFINITE);
        transitionForBlack.setAutoReverse(true);
        transitionForBlack.play();

        Timeline timelineForBlack = new Timeline();
        timelineForBlack.getKeyFrames().add(new KeyFrame(Duration.seconds(3), event -> duck4Black.setScaleX(1.0)));
        timelineForBlack.getKeyFrames().add(new KeyFrame(Duration.seconds(6), event -> duck4Black.setScaleX(-1.0)));
        timelineForBlack.setCycleCount(Timeline.INDEFINITE);
        timelineForBlack.play();

        // Add foreground image to the pane
        pane.getChildren().add(selection.getForeground());
        // Set up crosshair cursor and add it to the pane
        selection.setImageOfCrosshair(new Image(new File("assets/crosshair/" + selection.getClockforcrosshair() + ".png" ).toURI().toString()));
        pane.setCursor(new ImageCursor(selection.getImageOfCrosshair()));

        // Create and set the scene for level 3
        scene7 = new Scene(pane);
        window.setScene(scene7);

        // Set initial ammo count and duck count for level 3
        selection.setShots(9);
        selection.setDuckcount(3);
        // Display level information
        selection.Opening(pane, "Level 5/6", 7 * scale, -113 * scale);

        // Display ammo count
        Text opening = new Text("Ammo Left: " + selection.getShots());

        // Add the opening text to the pane
        opening.setFont(Font.font("Arial", FontWeight.BOLD, 7 * scale));
        opening.setFill(Color.ORANGE);
        opening.setTranslateY(-113 * scale);
        opening.setTranslateX(104 * scale);
        pane.getChildren().add(opening);

        scene7.setOnMouseClicked(event -> {
            // Check if shots are remaining and two duck is present
            if(selection.getShots() > 1 & selection.getDuckcount() > 0){
                // Decrease ammo count
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot sound effect
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

            }
            // Check if it's the last shot and duck left
            else if(selection.getShots() == 1 & selection.getDuckcount() > 0){
                // Decrease ammo count
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot sound effect
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();


                // Play game over sound effect
                String musicFile = "assets/effects/GameOver.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Display "GAME OVER" message
                selection.Opening(pane, "GAME OVER!!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play again\nPress ESC to exit", 16 * scale, 10 * scale);

                // Set up timeline for flashing "GAME OVER" text
                Timeline timelineForLosing = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForLosing.setCycleCount(Timeline.INDEFINITE);
                timelineForLosing.play();


                scene7.setOnKeyPressed(event1 -> {
                    // Handle ESCAPE key press to return to title scene
                    if(event1.getCode() == KeyCode.ESCAPE){
                        try {
                            duck1.setImage(new Image(new File("assets/duck_blue/1.png").toURI().toString()));
                            duck4Black.setImage(new Image(new File("assets/duck_black/1.png").toURI().toString()));
                            timelineForLosing.stop();
                            titleScene(window);
                            window.setScene(scene1);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // Handle ENTER key press to start over
                    else if(event1.getCode() == KeyCode.ENTER){
                        try {
                            duck1.setImage(new Image(new File("assets/duck_blue/1.png").toURI().toString()));
                            duck4Black.setImage(new Image(new File("assets/duck_black/1.png").toURI().toString()));
                            timelineForLosing.stop();
                            mediaPlayer.stop();
                            gamingScene(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });


            }
            else if(selection.getShots() >= 0 & selection.getDuckcount() == 0){
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());
                // Reset ammo count to zero

                // Play gunshot sound effect
                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play level completed sound effect
                String levelcompleted = "assets/effects/LevelCompleted.mp3";
                Media levelpassed = new Media(Paths.get(levelcompleted).toUri().toString());
                mediaPlayer = new MediaPlayer(levelpassed);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Display level completion instructions
                selection.Opening(pane, "YOU WIN!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play next level", 16 * scale, 2 * scale);
                Timeline timelineForWinning = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForWinning.setCycleCount(Timeline.INDEFINITE);
                timelineForWinning.play();

                scene7.setOnKeyPressed(event1 -> {
                    // Handle ENTER key press to go to next level
                    if(event1.getCode() == KeyCode.ENTER){
                        mediaPlayer.stop();
                        try {
                            duck1.setImage(new Image(new File("assets/duck_blue/1.png").toURI().toString()));
                            duck4Black.setImage(new Image(new File("assets/duck_black/1.png").toURI().toString()));
                            timelineForWinning.stop();
                            gamingScene6(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }

        });
        duck4Red.setOnMouseClicked(event -> {
            if(selection.getShots() > 0){
                selection.setDuckcount(selection.getDuckcount() - 1);

                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play duck falls sound effect
                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                transition.stop();
                timelineForRed.stop();
                timeline2ForRed.stop();

                // Gives the bird of getting shot image
                duck4Red.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck4Red.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duck4Red.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                duck4Red.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck4Red);
                transition1.setFromY(duck4Red.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();

            }
        });

        duck4Black.setOnMouseClicked(event -> {
            if(selection.getShots() > 0){
                selection.setDuckcount(selection.getDuckcount() - 1);


                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play duck falls sound effect
                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                transitionForBlack.stop();
                timelineForBlack.stop();
                timeline2ForBlack.stop();

                // Gives the bird of getting shot image
                duck4Black.setImage(new Image(new File("assets/duck_black/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck4Black.setImage(new Image(new File("assets/duck_black/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duck4Black.setImage(new Image(new File("assets/duck_black/8.png").toURI().toString()));
                duck4Black.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck4Black);
                transition1.setFromY(duck4Black.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();
            }
        });

        duck1.setOnMouseClicked(event -> {
            if(selection.getShots() > 0){
                selection.setDuckcount(selection.getDuckcount() - 1);

                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play duck falls sound effect
                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                timeline.stop();
                timeline2.stop();

                // Gives the bird of getting shot image
                duck1.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck1.setImage(new Image(new File("assets/duck_blue/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duck1.setImage(new Image(new File("assets/duck_blue/8.png").toURI().toString()));
                duck1.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck1);
                transition1.setFromY(duck1.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();

            }
        });
    }

    public void gamingScene6(Stage gamingStage6){
        StackPane pane = new StackPane();

        // Load duck images
        Image duckImage1 = new Image(new File("assets/duck_blue/1.png").toURI().toString());
        ImageView duck1 = new ImageView(duckImage1);
        duck1.setScaleY(duck1.getScaleY() * -1.0);
        duck1.setFitWidth(duckImage1.getWidth() * scale);
        duck1.setFitHeight(duckImage1.getHeight() * scale);
        duck1.setTranslateX(-93 * scale);
        duck1.setTranslateY(83 * scale);

        Image duckImage2 = new Image(new File("assets/duck_blue/2.png").toURI().toString());
        Image duckImage3 = new Image(new File("assets/duck_blue/3.png").toURI().toString());

        // Load duck images
        Image duckImage1ForBlack = new Image(new File("assets/duck_black/1.png").toURI().toString());
        ImageView duckForBlack = new ImageView(duckImage1ForBlack);
        duckForBlack.setScaleY(duckForBlack.getScaleY() * -1.0);
        duckForBlack.setFitWidth(duckImage1ForBlack.getWidth() * scale);
        duckForBlack.setFitHeight(duckImage1ForBlack.getHeight() * scale);
        duckForBlack.setTranslateX(93 * scale);
        duckForBlack.setTranslateY(-50 * scale);

        Image duckImage2ForBlack = new Image(new File("assets/duck_black/2.png").toURI().toString());
        Image duckImage3ForBlack = new Image(new File("assets/duck_black/3.png").toURI().toString());

        Image duckImage4ForRed = new Image(new File("assets/duck_red/4.png").toURI().toString());
        ImageView duck4Red = new ImageView(duckImage4ForRed);
        duck4Red.setFitWidth(duckImage4ForRed.getWidth() * scale);
        duck4Red.setFitHeight(duckImage4ForRed.getHeight() * scale);

        Image duckImage5ForRed = new Image(new File("assets/duck_red/5.png").toURI().toString());
        Image duckImage6ForRed = new Image(new File("assets/duck_red/6.png").toURI().toString());

        // Add background to the pane
        pane.getChildren().add(selection.getBackground());

        // Set up duck animation timeline
        Timeline timeline2 = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), event -> duck1.setImage(duckImage1));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.5), event -> duck1.setImage(duckImage2));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(1), event -> duck1.setImage(duckImage3));
        timeline2.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.setAutoReverse(true);
        pane.getChildren().add(duck1);
        timeline2.play();

        // Set up duck animation timeline
        Timeline timeline2ForBlack = new Timeline();
        KeyFrame keyFrame1Black = new KeyFrame(Duration.seconds(0), event -> duckForBlack.setImage(duckImage1ForBlack));
        KeyFrame keyFrame2Black = new KeyFrame(Duration.seconds(0.5), event -> duckForBlack.setImage(duckImage2ForBlack));
        KeyFrame keyFrame3Black = new KeyFrame(Duration.seconds(1), event -> duckForBlack.setImage(duckImage3ForBlack));
        timeline2ForBlack.getKeyFrames().addAll(keyFrame1Black, keyFrame2Black, keyFrame3Black);
        timeline2ForBlack.setCycleCount(Timeline.INDEFINITE);
        timeline2ForBlack.setAutoReverse(true);
        pane.getChildren().add(duckForBlack);
        timeline2ForBlack.play();



        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            // Update the position
            duck1.setTranslateX(duck1.getTranslateX() + velocityX);
            duck1.setTranslateY(duck1.getTranslateY() + velocityY);

            // Check if the image reaches the left or right edge
            if (duck1.getTranslateX() >= 116 * scale) {
                // Reverse the velocity to change direction
                velocityX *= -1;

                // Reflect the image horizontally
                duck1.setScaleX(duck1.getScaleX() * -1.0);
            }

            if (duck1.getTranslateX() <= -116 * scale){
                // Reverse the velocity to change direction
                // Reflect the image horizontally
                velocityX *= -1;
                duck1.setScaleX(duck1.getScaleX() * -1.0);
            }

            // Check if the image reaches the top or bottom edge
            if (duck1.getTranslateY() <= -107 * scale) {
                // Reverse the velocity to change direction
                velocityY *= -1;

                // Reflect the image vertically
                duck1.setScaleY(duck1.getScaleY() * -1.0);
            }

            if (duck1.getTranslateY() >= 107 * scale){
                // Reverse the velocity to change direction
                // Reflect the image vertically
                velocityY *= -1;
                duck1.setScaleY(duck1.getScaleY() * -1.0);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline timelineForBlack = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            // Update the position
            duckForBlack.setTranslateX(duckForBlack.getTranslateX() + velocityXForBlack);
            duckForBlack.setTranslateY(duckForBlack.getTranslateY() + velocityYForBlack);

            // Check if the image reaches the left or right edge
            if (duckForBlack.getTranslateX() >= 116 * scale) {
                // Reverse the velocity to change direction
                velocityXForBlack *= -1;

                // Reflect the image horizontally
                duckForBlack.setScaleX(duckForBlack.getScaleX() * -1.0);
            }

            if (duckForBlack.getTranslateX() <= -116 * scale){
                // Reverse the velocity to change direction
                // Reflect the image horizontally
                velocityXForBlack *= -1;
                duckForBlack.setScaleX(duckForBlack.getScaleX() * -1.0);
            }

            // Check if the image reaches the top or bottom edge
            if (duckForBlack.getTranslateY() <= -107 * scale) {
                // Reverse the velocity to change direction
                velocityYForBlack *= -1;

                // Reflect the image vertically
                duckForBlack.setScaleY(duckForBlack.getScaleY() * -1.0);
            }

            if (duckForBlack.getTranslateY() >= 107 * scale){
                // Reverse the velocity to change direction
                // Reflect the image vertically
                velocityYForBlack *= -1;
                duckForBlack.setScaleY(duckForBlack.getScaleY() * -1.0);
            }
        }));
        timelineForBlack.setCycleCount(Animation.INDEFINITE);
        timelineForBlack.play();

        // Set up duck animation timeline
        Timeline timeline2ForRed = new Timeline();
        KeyFrame keyFrame1ForRed = new KeyFrame(Duration.seconds(0), event -> duck4Red.setImage(duckImage4ForRed));
        KeyFrame keyFrame2ForRed = new KeyFrame(Duration.seconds(0.5), event -> duck4Red.setImage(duckImage5ForRed));
        KeyFrame keyFrame3ForRed = new KeyFrame(Duration.seconds(1), event -> duck4Red.setImage(duckImage6ForRed));
        timeline2ForRed.getKeyFrames().addAll(keyFrame1ForRed, keyFrame2ForRed, keyFrame3ForRed);
        timeline2ForRed.setCycleCount(Timeline.INDEFINITE);
        timeline2ForRed.setAutoReverse(true);

        // Add duck image and start animation
        pane.getChildren().add(duck4Red);
        timeline2.play();

        // Set up duck translation animation
        duck4Red.setPreserveRatio(true);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(3), duck4Red);
        transition.setFromX(-(selection.getBackground().getFitWidth()) / 2);
        transition.setToX(selection.getBackground().getFitWidth() / 2);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();


        Timeline timelineForRed = new Timeline();
        timelineForRed.getKeyFrames().add(new KeyFrame(Duration.seconds(3), event -> duck4Red.setScaleX(-1.0)));
        timelineForRed.getKeyFrames().add(new KeyFrame(Duration.seconds(6), event -> duck4Red.setScaleX(1.0)));
        timelineForRed.setCycleCount(Timeline.INDEFINITE);
        timelineForRed.play();


        // Add foreground image and set cursor
        pane.getChildren().add(selection.getForeground());
        selection.setImageOfCrosshair(new Image(new File("assets/crosshair/" + selection.getClockforcrosshair() + ".png" ).toURI().toString()));
        pane.setCursor(new ImageCursor(selection.getImageOfCrosshair()));


        // Create and set the gaming scene
        scene8 = new Scene(pane);
        window.setScene(scene8);


        // Initialize ammo and duck count
        selection.setShots(9);
        selection.setDuckcount(3);

        // Add opening text
        selection.Opening(pane, "Level 6/6", 7 * scale, -113 * scale);
        Text opening = new Text("Ammo Left: " + selection.getShots());
        opening.setFont(Font.font("Arial", FontWeight.BOLD, 7 * scale));
        opening.setFill(Color.ORANGE);
        opening.setTranslateY(-113 * scale);
        opening.setTranslateX(106 * scale);
        pane.getChildren().add(opening);

        scene8.setOnMouseClicked(event -> {
            if(selection.getShots() > 1 & selection.getDuckcount() > 0){
                // Decrease ammo count and update text
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot sound
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

            }
            else if(selection.getShots() == 1 & selection.getDuckcount() > 0){
                // Decrease ammo count and update text
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());

                // Play gunshot and game over sounds
                String shot = "assets/effects/Gunshot.mp3";
                Media gunshot = new Media(Paths.get(shot).toUri().toString());
                mediaPlayer = new MediaPlayer(gunshot);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String musicFile = "assets/effects/GameOver.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Display "GAME OVER" message and instructions
                selection.Opening(pane, "GAME OVER!!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play again\nPress ESC to exit", 16 * scale, 10 * scale);

                // Set up timeline for alternating display of "GAME OVER" message
                Timeline timelineForLosing = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForLosing.setCycleCount(Timeline.INDEFINITE);
                timelineForLosing.play();


                scene8.setOnKeyPressed(event1 -> {
                    // Handle ESC key press to go back to title scene
                    if(event1.getCode() == KeyCode.ESCAPE){
                        try {
                            timelineForLosing.stop();
                            timeline.stop();
                            titleScene(window);
                            window.setScene(scene1);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // Handle ENTER key press to restart the game
                    else if(event1.getCode() == KeyCode.ENTER){
                        try {
                            timeline.stop();
                            timelineForLosing.stop();
                            mediaPlayer.stop();
                            gamingScene(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });


            }
            else if(selection.getShots() >= 0 & selection.getDuckcount() == 0){
                selection.setShots(selection.getShots() - 1);
                opening.setText("Ammo Left: " + selection.getShots());
                selection.setShots(0);
                timeline2.stop();

                // Play gunshot, duck fall, and level completed sounds
                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                String gamecompleted = "assets/effects/GameCompleted.mp3";
                Media levelpassed = new Media(Paths.get(gamecompleted).toUri().toString());
                mediaPlayer = new MediaPlayer(levelpassed);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                duck1.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                Timeline timeline3 = new Timeline();
                timeline3.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck1.setImage(new Image(new File("assets/duck_blue/8.png").toURI().toString()));
                }));
                timeline3.play();

                duck1.setImage(new Image(new File("assets/duck_blue/8.png").toURI().toString()));
                duck1.setPreserveRatio(true);
                timeline.stop();
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck1);
                transition1.setFromY(duck1.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();

                // Display "YOU WIN" message and instructions
                selection.Opening(pane, "You have completed the game!", 16 * scale, -16 * scale);
                selection.Opening(pane, "Press ENTER to play again\nPress ESC to exit", 16 * scale, 10 * scale);
                Timeline timelineForWinning = new Timeline(
                        new KeyFrame(Duration.seconds(0), e -> selection.getText().setVisible(true)),
                        new KeyFrame(Duration.seconds(1), e -> selection.getText().setVisible(false)),
                        new KeyFrame(Duration.seconds(2), e -> selection.getText().setVisible(true))
                );
                timelineForWinning.setCycleCount(Timeline.INDEFINITE);
                timelineForWinning.play();

                scene8.setOnKeyPressed(event1 -> {
                    // Handle ENTER key press to start again
                    if(event1.getCode() == KeyCode.ENTER){
                        timelineForWinning.stop();
                        mediaPlayer.stop();
                        try {
                            gamingScene(window);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else if(event1.getCode() == KeyCode.ESCAPE){
                        timelineForWinning.stop();
                        mediaPlayer.stop();
                        try {
                            titleScene(window);
                            window.setScene(scene1);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

            }

        });

        duck1.setOnMouseClicked(event -> {
            if(selection.getShots() > 0){
                selection.setDuckcount(selection.getDuckcount() - 1);

                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play duck falls sound effect
                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                timeline.stop();
                timeline2.stop();

                // Gives the bird of getting shot image
                duck1.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck1.setImage(new Image(new File("assets/duck_blue/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duck1.setImage(new Image(new File("assets/duck_blue/8.png").toURI().toString()));
                duck1.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck1);
                transition1.setFromY(duck1.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();

            }
        });

        duckForBlack.setOnMouseClicked(event -> {
            if(selection.getShots() > 0){
                selection.setDuckcount(selection.getDuckcount() - 1);

                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play duck falls sound effect
                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                timelineForBlack.stop();
                timeline2ForBlack.stop();

                // Gives the bird of getting shot image
                duckForBlack.setImage(new Image(new File("assets/duck_black/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duckForBlack.setImage(new Image(new File("assets/duck_black/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duckForBlack.setImage(new Image(new File("assets/duck_black/8.png").toURI().toString()));
                duckForBlack.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duckForBlack);
                transition1.setFromY(duckForBlack.getTranslateY());
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();
            }
        });

        duck4Red.setOnMouseClicked(event -> {
            if(selection.getShots() > 0){
                selection.setDuckcount(selection.getDuckcount() - 1);

                String musicFile = "assets/effects/Gunshot.mp3";
                Media media = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                // Play duck falls sound effect
                String duckfalls = "assets/effects/DuckFalls.mp3";
                Media duckdown = new Media(Paths.get(duckfalls).toUri().toString());
                mediaPlayer = new MediaPlayer(duckdown);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();

                transition.stop();
                timelineForRed.stop();
                timeline2ForRed.stop();

                // Gives the bird of getting shot image
                duck4Red.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event1 -> {
                    duck4Red.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                }));
                timeline1.play();

                // Animate the bird of getting fall down image
                duck4Red.setImage(new Image(new File("assets/duck_red/8.png").toURI().toString()));
                duck4Red.setPreserveRatio(true);
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(3), duck4Red);
                transition1.setFromY(0);
                transition1.setToY(500);
                transition1.setCycleCount(1);
                transition1.play();

            }
        });

    }
}
