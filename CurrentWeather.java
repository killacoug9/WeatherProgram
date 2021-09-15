import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.TimeZone;

public class CurrentWeather extends Weather {

    public CurrentWeather(Location location) throws IOException, InterruptedException {
        super(location, "weather"); // gets the current weather
    }

    @Override
    public void display() {
        System.out.println("\nRight now the temperature in " + getLocation().getCity() +" is " + getData().getObject("main").getValue("temp") + " celsius, but it feels like " + getData().getObject("main").getValue("feels_like") + " celsius");
        Scanner input = new Scanner(System.in);

        while (true){
            System.out.println();
            System.out.println("Do you what to know more about the weather? Select the number you want to know more about.");
            System.out.println("\t1 - details");
            System.out.println("\t2 - wind");
            System.out.println("\t3 - snow");
            System.out.println("\t4 - clouds");
            System.out.println("\t5 - sunrise/sunset");
            System.out.println("\t6 - exit");

            int numChosen = input.nextInt();
            if (numChosen == 1){
                System.out.println("The minimum temperature will be " + getData().getObject("main").getValue("temp_min") + " celsius");
                System.out.println("The maximum temperature will be " + getData().getObject("main").getValue("temp_max") + " celsius");
                System.out.println("The pressure is " + getData().getObject("main").getValue("pressure") + " hPa");
                System.out.println("The humidity is " + getData().getObject("main").getValue("humidity") + " percent");
            } else if (numChosen == 2) {
                if (getData().getObject("wind").getValue("speed") == null) {
                    System.out.println("Sorry, there is no data regarding the wind in " + getLocation().getCity() + ".");
                } else {
                    System.out.println("The wind speed is " + getData().getObject("wind").getValue("speed") + " kph");
                    System.out.println("The wind degree/direction is " + getData().getObject("wind").getValue("deg") + "  degrees from the north");
                }
            } else if (numChosen == 3) {
                if (getData().getObject("snow").getValue("snow.3h") == null){
                    System.out.println("Sorry, there is no snow in " + getLocation().getCity() + ".");
                } else{
                    System.out.println("The snow volume for the past 3 hours is " + getData().getObject("snow").getValue("3h") + "cm^3");
                }
            } else if (numChosen == 4) {
                System.out.println("It is " + getData().getObject("clouds").getValue("all") + " percent cloudy");
            } else if (numChosen == 5) {
                Date sunrise = new Date(((Long) getData().getObject("sys").getValue("sunrise"))*1000);
                Date sunset = new Date(((Long) getData().getObject("sys").getValue("sunset"))*1000);
                DateFormat format = new SimpleDateFormat("h:mm a");
                format.setTimeZone(TimeZone.getTimeZone("PST"));
                System.out.println("The sunrise is at " + format.format(sunrise) + " PST, and the sunset is at " + format.format(sunset) + " PST");
            } else if (numChosen == 6){
                break;
            } else{
                System.out.println("Please select a number between 1 and 6.");
            }
        }
    }
}