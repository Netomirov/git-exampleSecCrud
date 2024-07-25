package com.example.example4test.validator;

import com.example.example4test.entity.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.hasText;

@Component
public class UserInfoValidation implements ConstraintValidator<UserInfo, User>{
    @Override
    public boolean isValid(User value, ConstraintValidatorContext constraintValidatorContext) {
        return hasText(value.getName());
    }
}
