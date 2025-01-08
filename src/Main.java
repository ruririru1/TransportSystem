import java.util.*;

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

    @Override
    public String toString() {
        return "Bus [Model=" + getModel() + ", Registration Number=" + getRegistrationNumber() + ", Capacity=" + capacity + "]";
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
}

public class Main {
    public static void main(String[] args) {
        TransportService service = new TransportService();

        Transport bus1 = new Bus("Mercedes", "AB1234", 50);
        Transport bus2 = new Bus("Volvo", "CD5678", 40);

        service.addTransport(bus1);
        service.addTransport(bus2);

        System.out.println("All Transports:");
        service.displayAllTransports();

        System.out.println("\nFinding transport with registration number 'CD5678':");
        System.out.println(service.findTransportByRegistrationNumber("CD5678"));
        System.out.println("\nFinding transport with registration number 'CD1223':");
        System.out.println(service.findTransportByRegistrationNumber("CD1223"));
    }
}
