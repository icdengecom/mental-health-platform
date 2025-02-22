package com.icdenge.common.utils.validation.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_REGEX = "^(?=.{1,64}@.{1,255}$)(?!.*\\.\\.)([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private int maxLength;
    private String[] allowedDomains;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        this.maxLength = constraintAnnotation.maxLength();
        this.allowedDomains = constraintAnnotation.allowedDomains();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isBlank()) {
            return false; // Null ya da boş email geçersiz
        }

        if (email.length() > maxLength) {
            return false; // Uzunluk sınırını aşarsa geçersiz
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return false; // Regex kontrolü
        }

        if (allowedDomains.length > 0) {
            String domain = email.substring(email.lastIndexOf("@") + 1);
            for (String allowed : allowedDomains) {
                if (domain.equalsIgnoreCase(allowed)) {
                    return true;
                }
            }
            return false; // İzin verilen domainlerden biri değilse geçersiz
        }

        return true;
    }
}
