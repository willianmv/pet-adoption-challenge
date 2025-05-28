package service;

import entity.Pet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

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

    private String crateFileName(Pet pet){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataPart = pet.getCreatedAt().format(dtf);
        String namePart = pet.getFullName().toUpperCase().replaceAll("\\s+", "");
        return dataPart + "-" + namePart + ".txt";
    }
}
