package afti.proto.circuitcourt;

public class Shop {
    private String name, city, street, postal_code, contacts, phone, description;
    private double longitude, latitude;
    private int num;

    public Shop(String name, String city, String street, String postal_code, String description, String contacts, String phone, double longitude, double latitude, int num) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.postal_code = postal_code;
        this.description = description;
        this.contacts = contacts;
        this.phone = phone;
        this.longitude = longitude;
        this.latitude = latitude;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }

    public String getPhone() {
        return phone;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getNum() {
        return num;
    }
}
