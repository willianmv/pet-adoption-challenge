package service;

import entity.FormQuestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FormQuestionService {

    private final String filePath;

    public FormQuestionService(String filePath) {
        this.filePath = filePath;
    }

    public List<FormQuestion> loadFormQuestions(){
        List<FormQuestion> formQuestions = new ArrayList<>();

        try(BufferedReader bf = new BufferedReader(new FileReader(filePath))){
            String linha;

            while ((linha = bf.readLine()) != null){
                String[] parts = linha.split("-", 2);
                if(parts.length == 2){
                    int number = Integer.parseInt(parts[0].trim());
                    String text = parts[1].trim();
                    formQuestions.add(new FormQuestion(number, text));
                }
            }

        } catch (Exception ex) {
            System.out.println("--ERRO: "+ ex);
        }
        return formQuestions;
    }
}
