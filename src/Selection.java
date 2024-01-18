import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;

/**
 * The Selection class represents the selection of various elements in the Duck Hunt game.
 * It provides methods to set and get different properties of the selection, such as background,
 * crosshair, foreground, duck type, shots, duck count, and text.
 */


public class Selection {  // CLASSIM
    private int clockforground = 1;
    private int clockforcrosshair = 1;
    private ImageView background;
    private ImageView typeOfCrosshair;
    private ImageView foreground;
    private ImageView ducktype;
    private Image imageOfCrosshair;
    private int shots;
    private int duckcount;
    private Text text;

    // All those variables are setting and returning for methods of duck hunt class.
    public Text getText() {
        return text;
    }
    public void setText(Text text) {
        this.text = text;
    }
    public int getDuckcount() {
        return duckcount;
    }

    public void setDuckcount(int duckcount) {
        this.duckcount = duckcount;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public ImageView getDucktype() {
        return ducktype;
    }

    public void setDucktype(ImageView ducktype) {
        this.ducktype = ducktype;
    }
    public Image getImageOfCrosshair() {
        return imageOfCrosshair;
    }

    public void setImageOfCrosshair(Image imageOfCrosshair) {
        this.imageOfCrosshair = imageOfCrosshair;
    }

    public int getClockforground() {
        return clockforground;
    }

    public void setClockforground(int clockforground) {
        this.clockforground = clockforground;
    }

    public int getClockforcrosshair() {
        return clockforcrosshair;
    }

    public void setClockforcrosshair(int clockforcrosshair) {
        this.clockforcrosshair = clockforcrosshair;
    }

    public ImageView getBackground() {
        return background;
    }

    public void setBackground(ImageView background) {
        this.background = background;
    }

    public ImageView getTypeOfCrosshair() {
        return typeOfCrosshair;
    }

    public void setTypeOfCrosshair(ImageView typeOfCrosshair) {
        this.typeOfCrosshair = typeOfCrosshair;
    }

    public ImageView getForeground() {
        return foreground;
    }

    public void setForeground(ImageView foreground) {
        this.foreground = foreground;
    }


    /**
     * Sets the background image of the selection in the specified stack pane.
     *
     * @param pane   the stack pane to set the background image on
     * @param text   the text representing the background image file path
     * @param number the number representing the specific background image
     */
    public void SelectionForBackground(StackPane pane, String text, int number){
        Image newBackground = new Image(new File("assets/" + text + "/" + number + ".png").toURI().toString());
        setBackground(new ImageView(newBackground));
        getBackground().setFitHeight(newBackground.getHeight() * DuckHunt.scale);
        getBackground().setFitWidth(newBackground.getWidth() * DuckHunt.scale);
        pane.getChildren().add(getBackground());

    }


    /**
     * Sets the foreground image of the selection in the specified stack pane.
     *
     * @param pane   the stack pane to set the foreground image on
     * @param text   the text representing the foreground image file path
     * @param number the number representing the specific foreground image
     */

    public void SelectionForForeground(StackPane pane, String text, int number){
        Image newForeground = new Image(new File("assets/" + text + "/" + number + ".png").toURI().toString());
        setForeground(new ImageView(newForeground));
        getForeground().setFitHeight(newForeground.getHeight() * DuckHunt.scale);
        getForeground().setFitWidth(newForeground.getWidth() * DuckHunt.scale);
        pane.getChildren().add(getForeground());
    }

    /**
     * Sets the crosshair image of the selection in the specified stack pane.
     *
     * @param pane   the stack pane to set the crosshair image on
     * @param text   the text representing the crosshair image file path
     * @param number the number representing the specific crosshair image
     */

    public void SelectionForCrosshair(StackPane pane, String text, int number){
        Image crosshair = new Image(new File("assets/" + text + "/" + number + ".png").toURI().toString());
        setTypeOfCrosshair(new ImageView(crosshair));
        getTypeOfCrosshair().setFitWidth(crosshair.getWidth() * DuckHunt.scale);
        getTypeOfCrosshair().setFitHeight(crosshair.getHeight() * DuckHunt.scale);
        pane.getChildren().add(getTypeOfCrosshair());
    }

    /**
     * Adds an opening text element to the specified stack pane.
     *
     * @param pane     the stack pane to add the opening text element to
     * @param words    the text content of the opening
     * @param size     the font size of the opening text
     * @param location the vertical location of the opening text
     */

    public void Opening(StackPane pane, String words, double size, double location){
        Text opening = new Text(words);
        opening.setTextAlignment(TextAlignment.CENTER);
        opening.setFont(Font.font("Arial", FontWeight.BOLD, size));
        opening.setFill(Color.ORANGE);
        opening.setTranslateY(location);
        setText(opening);
        pane.getChildren().add(opening);
    }

    /**
     * Sets the duck image of the selection with the specified number.
     *
     * @param number the number representing the specific duck image
     */

    public void DuckSelection(int number){
        Image duckImage = new Image(new File("assets/duck_red/" + number + ".png").toURI().toString());
        setDucktype(new ImageView(duckImage));
        getDucktype().setFitWidth(duckImage.getWidth() * DuckHunt.scale);
        getDucktype().setFitHeight(duckImage.getHeight() * DuckHunt.scale);
    }

}
