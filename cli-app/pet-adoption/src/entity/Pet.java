package entity;

public class Pet {

    private String name;
    private String lastname;
    private PetType type;
    private PetSex sex;
    private String breed;
    private Address address;
    private Double age;
    private Double weight;

    public Pet() {
    }

    public Pet(String name, String lastname, PetType type, PetSex sex, String breed, Address address, Double age, Double weight) {
        this.name = name;
        this.lastname = lastname;
        this.type = type;
        this.sex = sex;
        this.breed = breed;
        this.address = address;
        this.age = age;
        this.weight = weight;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetSex getSex() {
        return sex;
    }

    public void setSex(PetSex sex) {
        this.sex = sex;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("NOME COMPLETO: %s\nTIPO: %s\nSEXO: %s\nIDADE: %.2f\nPESO: %.2f\nENCONTRADO EM: %s\n",
                name+" "+lastname, type.toString(), sex.toString(), age, weight, address);
    }
}
