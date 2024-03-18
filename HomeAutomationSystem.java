import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Device interface
interface SmartDevice {
    void turnOn();

    void turnOff();
}

// Light class implementing SmartDevice interface
class Light implements SmartDevice {
    private boolean isOn;

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Light is ON");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Light is OFF");
    }
}

// Thermostat class implementing SmartDevice interface
class Thermostat implements SmartDevice {
    private int temperature;

    @Override
    public void turnOn() {
        System.out.println("Thermostat is ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Thermostat is OFF");
    }

    void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Temperature set to " + temperature + " degrees");
    }
}

// HomeAutomationSystem class
public class HomeAutomationSystem {
    private Map<String, SmartDevice> devices;

    public HomeAutomationSystem() {
        devices = new HashMap<>();
    }

    public void addDevice(String name, SmartDevice device) {
        devices.put(name, device);
    }

    public void controlDevice(String deviceName, String action) {
        SmartDevice device = devices.get(deviceName);
        if (device != null) {
            switch (action.toLowerCase()) {
                case "on":
                    device.turnOn();
                    break;
                case "off":
                    device.turnOff();
                    break;
                default:
                    System.out.println("Invalid action");
            }
        } else {
            System.out.println("Device not found");
        }
    }

    public static void main(String[] args) {
        HomeAutomationSystem system = new HomeAutomationSystem();

        // Adding devices
        Light livingRoomLight = new Light();
        system.addDevice("Living Room Light", livingRoomLight);

        Thermostat mainThermostat = new Thermostat();
        system.addDevice("Main Thermostat", mainThermostat);

        // User interaction
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter device name and action (e.g., 'Living Room Light on'): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length == 3) {
                String deviceName = parts[0] + " " + parts[1];
                String action = parts[2];
                system.controlDevice(deviceName, action);
            } else {
                System.out.println("Invalid input");
            }
        }

        scanner.close();
    }
}
