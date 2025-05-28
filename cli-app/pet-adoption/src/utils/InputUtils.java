package utils;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readIntValue(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.printf("--ENTRADA INVÁLIDA ['%s']. INSIRA UM VALOR DO TIPO INTEIRO.\n", input);
                System.out.println("--EXEMPLO: (1 - 15 - 665)");
            }
        }
    }

    public static double readDoubleValue(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                System.out.printf("--ENTRADA INVÁLIDA ['%s']. INSIRA UM VALOR DO TIPO DOUBLE.\n", input);
                System.out.println("--EXEMPLO: (5.5 - 1.0 - 487.59)");
            }
        }
    }

    public static String readNotEmptyString(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.printf("--ENTRADA INVÁLIDA ['%s']. ENTRE COM UMA STRING NÃO VAZIA\n", input);
        }
    }

    //passar uma interface funcional de supplier pq n recebe argumentos e executa uma função q retorna algo
    //passar uma interface funcional de function pq recebe um valor e retorna um valor
    public static <T> T readAndValidatedValue(Supplier<T> inputReader, Function<T, T> validator) {
        while (true) {
            try {
                T input = inputReader.get();
                return validator.apply(input);
            } catch (RuntimeException ex) {
                System.out.println("--ERRO: " + ex.getMessage());
            }
        }
    }

    //aqui tem que receber uma function que recebe um tipo e retorna outro pra transformar string validada em enum
    public static <T, R> R readAndTransformValue(Supplier<T> inputReader, Function<T, R> transformer) {
        while (true) {
            try {
                T input = inputReader.get();
                return transformer.apply(input);
            } catch (RuntimeException ex) {
                System.out.println("--ERRO: " + ex.getMessage());
            }

        }
    }
}
