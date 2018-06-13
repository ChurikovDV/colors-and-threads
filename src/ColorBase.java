import java.util.concurrent.TimeUnit;

public class ColorBase implements Runnable {
    private int modFps;
    private int colorValue;
    private String colorName;

    public ColorBase(String colorName, int modFps) {
        this.colorName = colorName;
        this.modFps = modFps;
        colorValue = (int) (Math.random() * 255);
    }

    public int getColorValue() {
        return colorValue;
    }

    public String getColorName() {
        return colorName;
    }


    @Override
    public void run() {
        byte step = 1;
        while (!AppVsBackground.isStopped) {
            int tempColor = colorValue + step;

            if (tempColor > 255) {
                tempColor = 255;
                step *= -1;
            }
            if (tempColor < 0) {
                tempColor = 0;
                step *= -1;
            }
            colorValue = tempColor;
            try {
                TimeUnit.MILLISECONDS.sleep(AppVsBackground.fps - modFps);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
