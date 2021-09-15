import java.io.IOException;
import java.util.Scanner;

public class ForecastedWeather extends Weather {

    public ForecastedWeather(Location location) throws IOException, InterruptedException {
        super(location, "forecast");
    }

    @Override
    public void display() {
        Scanner input = new Scanner(System.in);
        int dayCount = 1;
        for (int i = 0; dayCount <= 4 && i < getData().getArray("list").size(); i += 8) {
            System.out.println("Day " + dayCount + " - " + getData().getArray("list").get(i).getValue("dt_txt"));
            System.out.println("\t" + getData().getArray("list").get(i).getArray("weather").get(0).getValue("main"));
            System.out.println("\t" + getData().getArray("list").get(i).getObject("main").getValue("temp") + " C");
            System.out.println();
            dayCount++;
        }
        System.out.println("Which day would you like to look at?");
        int dayChoice = input.nextInt();
        dayChoice = (dayChoice - 1) * 8;
        System.out.println("\nWhat intervals do you want?");
        System.out.println("\t1 - Every 3 hours\n\t2 - Every 6 hours\n\t3 - Every 9 hours\n\t4 - Every 12 hours");
        int interval = input.nextInt();
        String theDay = String.valueOf(getData().getArray("list").get(dayChoice).getValue("dt_txt"));
        theDay = theDay.substring(0, 10);
        for (int i = 0; i < getData().getArray("list").size(); i += interval) {
            String testDay = String.valueOf(getData().getArray("list").get(i).getValue("dt_txt"));
            testDay = testDay.substring(0, 10);
            if (testDay.equals(theDay)) {
                System.out.println();
                System.out.println(getData().getArray("list").get(i).getValue("dt_txt"));
                System.out.println("The temperature is " + getData().getArray("list").get(i).getObject("main").getValue("temp") + "C");
                System.out.println("But it feels like " + getData().getArray("list").get(i).getObject("main").getValue("feels_like") + "C");
                System.out.println("The weather will be " + getData().getArray("list").get(i).getArray("weather").get(0).getValue("description"));
                System.out.println("There is a " + (Double.parseDouble(getData().getArray("list").get(i).getValue("pop").toString()) * 100) + "% chance of rain");
                System.out.println("The humidity will be " + getData().getArray("list").get(i).getObject("main").getValue("humidity") + "%");
            }
        }
    }
}