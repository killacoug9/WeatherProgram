import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Location {
    private String city;
    private String country;
    private String state;

    private final String API_KEY = "AIzaSyCBjS1bzTuhJpISCYaHORipCkH4IY_NjoQ";

    public Location() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a city.");
        String search = input.nextLine();
        System.out.println("\nSearching Google Maps for \"" + search + "\"");
        String query = URLEncoder.encode(search, StandardCharsets.UTF_8.toString());
        SimpleAPI api = new SimpleAPI("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + query + "&types=(cities)&key=" + API_KEY);
        JSONObject body = (JSONObject) JSONValue.parse(api.getResponse().body());
        ArrayList<String> options = new ArrayList<>();
        for (Object prediction : (JSONArray) body.get("predictions")) {
            options.add((String) ((JSONObject) prediction).get("description")); // Tacoma, WA, USA or London, UK
        }
        System.out.println(options.size() + " possible " + (options.size() == 1 ? "city" : "cities") + " found!");
        System.out.println("\nWhich city are you referring to?");
        for (int i = 0; i < options.size(); i++) {
            int parts = options.get(i).split(",").length;
            if (parts == 3 || parts == 2) {
                System.out.println("    " + (i + 1) + " - " + options.get(i));
            }
        }
        int choice = input.nextInt();
        while (choice < 1 || choice > options.size()) {
            System.out.println("Please enter a value from 1 to " + options.size());
            choice = input.nextInt();
        }
        String[] parts = options.get(choice - 1).split(",");
        city = parts[0].strip();
        if (parts.length == 3) {
            state = parts[1].strip();
            country = parts[2].strip();
        } else if (parts.length == 2) {
            country = parts[1].strip();
        }
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String toString() {
        if (state != null) {
            return city + "," + state + "," + country;
        } else {
            return city + "," + country;
        }
    }
}
