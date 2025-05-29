package service;

import entity.Address;
import entity.Pet;
import entity.PetSex;
import entity.PetType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PetFileService {

    private final String saveDirectoryName;

    public PetFileService(String saveDirectoryName) {
        this.saveDirectoryName = saveDirectoryName;
    }

    public void savePetInFile(Pet pet) throws IOException {

        String fileName = crateFileName(pet);
        File directory = new File(saveDirectoryName);
        File file = new File(directory, fileName);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            int linha = 1;
            writer.write(linha++ +" - "+ pet.getFullName());
            writer.newLine();

            writer.write(linha++ +" - "+ pet.getType().toString());
            writer.newLine();

            writer.write(linha++ +" - "+ pet.getSex().toString());
            writer.newLine();

            writer.write(linha++ +" - "+ pet.getAddress().toString());
            writer.newLine();

            writer.write(linha++ +" - "+ String.valueOf(pet.getAge()));
            writer.newLine();

            writer.write(linha++ +" - "+ String.valueOf(pet.getWeight()));
            writer.newLine();

            writer.write(linha++ +" - "+ pet.getBreed());
            writer.newLine();

            writer.flush();
        }
    }

    public List<Pet> loadPetsFromDirectory(){
        List<Pet> pets = new ArrayList<>();

        try{
            Files.list(Paths.get(saveDirectoryName))
                    .filter(file -> file.getFileName().toString().endsWith(".txt"))
                    .forEach(file -> {

                        try(BufferedReader reader = Files.newBufferedReader(file)){
                            List<String> lines = reader.lines().toList();

                            if(lines.size() >= 7){
                                String fullName = lines.getFirst().split(" - ")[1].trim();
                                PetType type = PetType.fromPortuguese(lines.get(1).split(" - ")[1].trim());
                                PetSex sex = PetSex.fromPortuguese(lines.get(2).split(" - ")[1].trim());
                                String fullAddress = lines.get(3).split(" - ")[1].trim();
                                double age = Double.parseDouble(lines.get(4).split(" - ")[1].trim());
                                double weight = Double.parseDouble(lines.get(5).split(" - ")[1].trim());
                                String breed = lines.get(6).split(" - ")[1].trim();

                                Address address = new Address();
                                address.setFullAddress(fullAddress);

                                Pet pet = new Pet(fullName, type, sex, breed, address, age, weight);
                                pets.add(pet);
                            }


                        } catch (Exception ex){
                            System.out.println("ERRO AO LER ARQUIVO: "+ ex.getMessage());
                        }
                    });
        } catch (IOException ex) {
            System.out.println("ERRO AO ACESSAR PASTA:"+ ex.getMessage());
        }
        return pets;
    }

    private String crateFileName(Pet pet){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataPart = pet.getCreatedAt().format(dtf);
        String namePart = pet.getFullName().toUpperCase().replaceAll("\\s+", "");
        return dataPart + "-" + namePart + ".txt";
    }
}
