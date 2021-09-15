import java.util.Scanner;

public class client {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Java Weather Tool");
        System.out.println("This project was created by Henry Stelle, Kyle Hawkins, Liam Loughridge, and Sean Smith.");

        Scanner input = new Scanner(System.in);

        System.out.println("\nWould you like to use metric (m) or imperial (i) units?");
        String units = input.next();
        if (units.equalsIgnoreCase("m")) {
            System.out.println("Great!");
        } else {
            System.out.println("Well, to bad. We are using metric. It is better.");
        }

        while (true) {
            System.out.println("\nWhere would you like to look up the weather?");
            Location location = new Location(); // implemented by Henry

            System.out.println("Would you like to find the current weather (1) or forecast (2) for " + location.getCity() + "?");
            int choice = input.nextInt();
            while (choice != 1 && choice != 2) {
                System.out.println("Please enter 1 for the current weather or 2 for the forecast.");
                choice = input.nextInt();
            }

            Weather report;
            if (choice == 1) { // current weather
                report = new CurrentWeather(location); // implemented by Kyle
            } else { // forecasted weather
                report = new ForecastedWeather(location); // implemented by Kyle
            }
            report.safeDisplay(); // implemented by Liam

            System.out.println("\nWould you like to check the weather for a different location? [y/n]");
            String again = input.next();
            if (!again.equalsIgnoreCase("y")) {
                break;
            }
        }
        System.out.println("\nHave a nice day!");
    }
}
