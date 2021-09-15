import org.json.simple.JSONValue;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public abstract class Weather {
    private Location location;
    private final String API_KEY = "3d17b1c61439d291f3e3d9d02ddaeaa1";
    private RecursiveJSON data;

    public Weather(Location location, String type) throws IOException, InterruptedException {
        this.location = location;
        SimpleAPI api = new SimpleAPI("https://api.openweathermap.org/data/2.5/" + type + "?q=" + URLEncoder.encode(location.toString(), StandardCharsets.UTF_8.toString()) + "&appid=" + API_KEY + "&units=metric");
        this.data = new RecursiveJSON(JSONValue.parse(api.getResponse().body()));
    }

    public Location getLocation() {
        return location;
    }

    public RecursiveJSON getData() {
        return this.data;
    };

    public abstract void display();

    public void safeDisplay() {
        if (data.getValue("cod").toString().equals("200")) {
            display();
        } else {
            System.out.println("Warning! The Weather API has returned a " + data.getValue("cod") + " status code.");
            System.out.println("Status Message: " + data.getValue("message"));
        }
    }
}
