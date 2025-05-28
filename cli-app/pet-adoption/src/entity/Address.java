package entity;

public class Address {

    private String number;
    private String city;
    private String street;

    public Address() {
    }

    public Address(String city, String number, String street) {
        this.city = city;
        this.number = number;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return String.format("\n-CIDADE: %s\n-RUA: %s\n-NÚMERO DA CASA: %s", city, street, number);
    }
}
