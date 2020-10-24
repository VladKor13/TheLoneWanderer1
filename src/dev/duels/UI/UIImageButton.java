package dev.duels.UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;
    private boolean IsEnable = true;


    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(IsEnable) {
            if (hovering) {
                g.drawImage(images[1], (int) x, (int) y, width, height, null);
                if (hovering && was_clicked) g.drawImage(images[2], (int) x, (int) y, width, height, null);
            } else
                g.drawImage(images[0], (int) x, (int) y, width, height, null);
        } else{
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onClick() {

        if(IsEnable) {
            clicker.onClick();
        }

    }

    public void setEnable(boolean IsEnable){
        this.IsEnable = IsEnable;
    }

    public boolean getEnable(){
        return IsEnable;
    }
}
