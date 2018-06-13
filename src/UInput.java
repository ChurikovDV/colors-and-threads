import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UInput implements Runnable {
    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (!AppVsBackground.isStopped) {
                if (reader.readLine().equalsIgnoreCase("stop")) {
                    AppVsBackground.isStopped = true;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
