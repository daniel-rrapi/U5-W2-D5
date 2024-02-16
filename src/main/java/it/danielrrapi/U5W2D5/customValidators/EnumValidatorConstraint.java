package it.danielrrapi.U5W2D5.customValidators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidatorConstraint implements ConstraintValidator<EnumValidator, String> {

    List<String> valueList = null;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return valueList.contains(value.toUpperCase());
    }

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        valueList = new ArrayList<String>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();

        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();

        for (@SuppressWarnings("rawtypes") Enum enumVal : enumValArr) {
            valueList.add(enumVal.toString().toUpperCase());
        }
    }
}