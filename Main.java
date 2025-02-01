import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;

class Transport {
    private String model;
    private String registrationNumber;

    public Transport(String model, String registrationNumber) {
        this.model = model;
        this.registrationNumber = registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "Transport [Model=" + model + ", Registration Number=" + registrationNumber + "]";
    }
}

class Bus extends Transport {
    private int capacity;
    private List<Passenger> passengers = new ArrayList<>();

    public Bus(String model, String registrationNumber, int capacity) {
        super(model, registrationNumber);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public boolean addPassenger(Passenger passenger) {
        if (passengers.size() < capacity) {
            passengers.add(passenger);
            return true;
        }
        return false; // Bus is full
    }

    @Override
    public String toString() {
        return "Bus [Model=" + getModel() + ", Registration Number=" + getRegistrationNumber() +
                ", Capacity=" + capacity + ", Passengers=" + passengers.size() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bus bus = (Bus) obj;
        return getRegistrationNumber().equals(bus.getRegistrationNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistrationNumber());
    }
}

class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Passenger [Name=" + name + ", Age=" + age + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Passenger passenger = (Passenger) obj;
        return name.equals(passenger.name) && age == passenger.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

class TransportService {
    private List<Transport> transports = new ArrayList<>();

    public void addTransport(Transport transport) {
        transports.add(transport);
    }

    public void displayAllTransports() {
        for (Transport transport : transports) {
            System.out.println(transport);
        }
    }

    public Transport findTransportByRegistrationNumber(String registrationNumber) {
        for (Transport transport : transports) {
            if (transport.getRegistrationNumber().equals(registrationNumber)) {
                return transport;
            }
        }
        return null;
    }

    public void sortBusesByCapacity() {
        transports.sort((t1, t2) -> {
            if (t1 instanceof Bus && t2 instanceof Bus) {
                return Integer.compare(((Bus) t1).getCapacity(), ((Bus) t2).getCapacity());
            }
            return 0;
        });
    }
}

public class Main {
    public static void main(String[] args) {
        TransportService service = new TransportService();

        Bus bus1 = new Bus("Mercedes", "AB1234", 40);
        Bus bus2 = new Bus("Volvo", "CD5678", 35);

        service.addTransport(bus1);
        service.addTransport(bus2);

        Passenger passenger1 = new Passenger("Alice", 30);
        Passenger passenger2 = new Passenger("Bob", 25);

        System.out.println("Adding passengers to buses:");
        if (bus1.addPassenger(passenger1)) {
            System.out.println("Added " + passenger1 + " to bus " + bus1.getRegistrationNumber());
        } else {
            System.out.println("Bus " + bus1.getRegistrationNumber() + " is full.");
        }

        if (bus2.addPassenger(passenger2)) {
            System.out.println("Added " + passenger2 + " to bus " + bus2.getRegistrationNumber());
        } else {
            System.out.println("Bus " + bus2.getRegistrationNumber() + " is full.");
        }

        service.sortBusesByCapacity();

        System.out.println("\nAll Transports (Sorted by Bus Capacity):");
        service.displayAllTransports();

        System.out.println("\nFinding transport with registration number 'CD5678':");
        System.out.println(service.findTransportByRegistrationNumber("CD5678"));
    }
}

