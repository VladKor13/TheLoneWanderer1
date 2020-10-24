package dev.duels.display;

import dev.duels.gfx.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URL;

public class Display {
    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public static Font myFont0 = new Font("Monospaced", Font.BOLD
            | Font.ITALIC, 18);
    public static Font myFont1 = new Font("Monospaced", Font.BOLD
            | Font.ITALIC, 26);
    public static Font myFont2 = new Font("Monospaced",Font.BOLD
            | Font.ITALIC, 34);
    public static Color Black_Col = new Color(0, 0, 0);
    public static Color Red_Col = new Color(201, 0, 10);
    public static Color Blue_Col = new Color(39, 154, 0);



    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();

    }


    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        URL url = ImageLoader.class.getResource("/textures/Helmet.png/");
        ImageIcon img = new ImageIcon(url);
        frame.setIconImage(img.getImage());

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();// Enable Canvas VISIBLE
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
