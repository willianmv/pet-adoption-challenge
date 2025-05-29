package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pet {

    private static int idCount = 0;
    private int id;
    private String fullName;
    private PetType type;
    private PetSex sex;
    private String breed;
    private Address address;
    private Double age;
    private Double weight;
    private LocalDateTime createdAt;

    public Pet() {
    }

    public Pet(String fullName, PetType type, PetSex sex, String breed, Address address, Double age, Double weight) {
        this.fullName = fullName;
        this.type = type;
        this.sex = sex;
        this.breed = breed;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.id = ++idCount;
        this.createdAt = LocalDateTime.now();
    }

    public Pet(int id, String fullName, PetType type, PetSex sex, String breed, Address address, Double age, Double weight, LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.type = type;
        this.sex = sex;
        this.breed = breed;
        this.address = address;
        this.age = age;
        this.weight = weight;
        if(id > idCount) idCount = id;
        this.createdAt = createdAt;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public void setAge(Double age) {
        this.age = age;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("\nID: %d\nDATA CADASTRO: %s\nNOME COMPLETO: %s\nTIPO: %s\nSEXO: %s\nRAÇA: %s\nIDADE: %.2f\nPESO: %.2f kg\nENCONTRADO EM: %s\n",
                id, createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                fullName, type.toString(), sex.toString(), breed, age, weight, address);
    }
}
