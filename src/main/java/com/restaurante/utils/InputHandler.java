package com.restaurante.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputHandler {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");

    public static int getIntInput(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }

    public static String getEmailInput(Scanner sc) {
        while (true) {
            String email = sc.nextLine().trim();
            if (EMAIL_PATTERN.matcher(email).matches()) {
                return email;
            }
            System.out.print("Email inválido. Digite novamente: ");
        }
    }

    public static LocalDate getDateInput(Scanner sc) {
        while (true) {
            try {
                return LocalDate.parse(sc.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.print("Data inválida (use AAAA-MM-DD): ");
            }
        }
    }

    public static String getTimeInput(Scanner sc) {
        while (true) {
            String time = sc.nextLine().trim();
            if (TIME_PATTERN.matcher(time).matches()) {
                return time;
            }
            System.out.print("Horário inválido (use HH:mm): ");
        }
    }

    public static String getNonEmptyInput(Scanner sc, String fieldName) {
        while (true) {
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.print(fieldName + " não pode ser vazio. Digite novamente: ");
        }
    }
}