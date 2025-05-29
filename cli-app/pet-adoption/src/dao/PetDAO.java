package dao;

import entity.Address;
import entity.Pet;
import entity.PetSex;
import entity.PetType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PetDAO implements IFileDAO<Pet> {

    @Override
    public void saveFile(Pet element, String directoryPath) {
        File directory = new File(directoryPath);
        if(!directory.exists()) directory.mkdirs();

        String fileName = crateFileName(element);
        File file = new File(directory, fileName);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            int linha = 1;
            writer.write("ID - " + element.getId());writer.newLine();
            writer.write("DATA CADASTRO - " + element.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            writer.newLine();
            writer.write(linha++ +" - "+ element.getFullName());writer.newLine();
            writer.write(linha++ +" - "+ element.getType().toString());writer.newLine();
            writer.write(linha++ +" - "+ element.getSex().toString());writer.newLine();
            writer.write(linha++ +" - "+ element.getAddress().toString());writer.newLine();
            writer.write(linha++ +" - "+ element.getAge());writer.newLine();
            writer.write(linha++ +" - "+ element.getWeight());writer.newLine();
            writer.write(linha +" - "+ element.getBreed());writer.newLine();
            writer.flush();

        } catch (IOException ex){
            System.out.println("ERRO AO SALVAR "+ element.getFullName() +" : " +ex.getMessage());
        }
    }

    @Override
    public List<Pet> loadFromFile(String filePath) {
        List<Pet> pets = new ArrayList<>();
        try(Stream<Path>files = Files.list(Paths.get(filePath))){
            files
                .filter(file -> file.getFileName().toString().endsWith(".txt"))
                .forEach(file -> {

                    try(BufferedReader reader = Files.newBufferedReader(file)){
                        List<String> lines = reader.lines().toList();

                        if(lines.size() >= 7){
                            Pet pet = parsePetFromFileLines(lines);
                            pets.add(pet);
                        }

                    } catch (Exception ex){
                        System.out.println("ERRO AO LER ARQUIVO: "+ ex.getMessage());
                    }});

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

    private Pet parsePetFromFileLines(List<String> lines){
        int id = Integer.parseInt(lines.getFirst().split(" - ")[1].trim());
        LocalDateTime createdAt = LocalDateTime.parse(lines.get(1).split(" - ")[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String fullName = lines.get(2).split(" - ")[1].trim();
        PetType type = PetType.fromPortuguese(lines.get(3).split(" - ")[1].trim());
        PetSex sex = PetSex.fromPortuguese(lines.get(4).split(" - ")[1].trim());
        String fullAddress = lines.get(5).split(" - ")[1].trim();
        double age = Double.parseDouble(lines.get(6).split(" - ")[1].trim());
        double weight = Double.parseDouble(lines.get(7).split(" - ")[1].trim());
        String breed = lines.get(8).split(" - ")[1].trim();

        Address address = new Address();
        address.setFullAddress(fullAddress);

        return new Pet(id, fullName, type, sex, breed, address, age, weight, createdAt);
    }

}
