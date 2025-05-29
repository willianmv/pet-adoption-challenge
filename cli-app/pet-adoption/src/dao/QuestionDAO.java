package dao;

import entity.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO implements IFileDAO<Question> {

    @Override
    public void saveFile(Question element, String directoryPath){}

    @Override
    public List<Question> loadFromFile(String filePath) {
        List<Question> questions = new ArrayList<>();

        try(BufferedReader bf = new BufferedReader(new FileReader(filePath))){
            String linha;

            while ((linha = bf.readLine()) != null){
                String[] parts = linha.split("-", 2);
                if(parts.length == 2){
                    int number = Integer.parseInt(parts[0].trim());
                    String text = parts[1].trim();
                    questions.add(new Question(number, text));
                }
            }

        } catch (Exception ex) {
            System.out.println("--ERRO: "+ ex);
        }
        return questions;
    }

}
