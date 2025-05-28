package utils;

import java.util.Scanner;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readIntValue(String message){
        while (true){
            System.out.println(message);
            String input = scanner.nextLine();
            try{
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.printf("--ENTRADA INVÁLIDA ['%s']. INSIRA UM VALOR DO TIPO INTEIRO.\n", input);
                System.out.println("--EXEMPLO: (1 - 15 - 665)");
            }
        }
    }

    public static double readDoubleValue(String message){
        while (true){
            System.out.println(message);
            String input = scanner.nextLine();
            try{
                return Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                System.out.printf("--ENTRADA INVÁLIDA ['%s']. INSIRA UM VALOR DO TIPO DOUBLE.\n", input);
                System.out.println("--EXEMPLO: (5.5 - 1.0 - 487.59)");
            }
        }
    }

    public  static String readNotEmptyString(String message){
        while (true){
            System.out.println(message);
            String input = scanner.nextLine().trim();
            if(!input.isEmpty()) return input;
            System.out.printf("--ENTRADA INVÁLIDA ['%s']. ENTRE COM UMA STRING NÃO VAZIA\n", input);
        }
    }

}
