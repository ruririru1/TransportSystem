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

