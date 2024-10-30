package src.controller;

public interface Validation {
    boolean validateName(String name);
    boolean validateEmail(String email);
    boolean validateMobileNumber(String number);
    boolean validateUsername(String username);
    boolean validatePassword(String password);
}
