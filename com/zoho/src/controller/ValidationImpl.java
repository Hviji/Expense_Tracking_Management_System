package src.controller;

public class ValidationImpl implements Validation {

    public boolean validateName(String name) {
        return name.matches("^[a-zA-Z ]+$");
    }

    public boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }

    public boolean validateMobileNumber(String number) {
        String phoneRegex = "^\\+[1-9]{1,3} ?[9876]\\d{9}$";
        return number.matches(phoneRegex);
    }

    public boolean validateUsername(String username) {
        String usernameRegex = "^[a-zA-Z][a-zA-Z0-9]*$";
        return username.matches(usernameRegex);
    }

    public boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordRegex);
    }
}
