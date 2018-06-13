import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;

public class AppVsBackground extends Applet implements Runnable {
    public static boolean isStopped = false;
    public static int fps = 60;
    Thread app;
    Thread input;

    ArrayList<ColorBase> colors = new ArrayList<>();
    ArrayList<Thread> colorThreads = new ArrayList<>();

    @Override
    public void init() {
        app = new Thread(this);
        input = new Thread(new UInput());

        colors.add(new ColorBase("redColor", 30));
        colors.add(new ColorBase("greenColor", 15));
        colors.add(new ColorBase("blueColor", 0));

        for (ColorBase color : colors) {
            colorThreads.add(new Thread(color));
        }

        app.start();
    }

    private int getColor(char colorName) {
        switch (colorName) {
            case 'r':
                return colors.get(0).getColorValue();
            case 'g':
                return colors.get(1).getColorValue();
            case 'b':
                return  colors.get(2).getColorValue();
            default:
                return -1;
        }
    }

    @Override
    public void run() {
        for (Thread thread : colorThreads) {
            thread.start();
        }
        input.start();
        try {
            while (!isStopped) {
                setBackground(
                    new Color(
                        getColor('r'),
                        getColor('g'),
                        getColor('b')
                    )
                );
                repaint();
                app.sleep(fps - 30);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}

