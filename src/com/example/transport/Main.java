package com.example.transport;

import java.util.ArrayList;
import java.util.List;

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