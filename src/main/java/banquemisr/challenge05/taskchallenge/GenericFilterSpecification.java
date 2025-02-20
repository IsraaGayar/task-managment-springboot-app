package banquemisr.challenge05.taskchallenge;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

public class GenericFilterSpecification<T> {

    public static <T> Specification<T> filterBy(String filterField, String filterValue) {
        return (root, query, criteriaBuilder) -> {
            if (filterValue == null || filterValue.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filter, return all entities
            }

            try {
                Path<?> fieldPath = root.get(filterField);
                Class<?> fieldType = fieldPath.getJavaType();

                if (String.class.isAssignableFrom(fieldType)) {
                    return criteriaBuilder.like(
                            criteriaBuilder.lower((Expression<String>) fieldPath),
                            "%" + filterValue.toLowerCase() + "%");
                } else if (Number.class.isAssignableFrom(fieldType)) {
                    try {
                        Number number = parseNumber(filterValue, (Class<? extends Number>) fieldType);
                        return criteriaBuilder.equal(fieldPath, number);
                    } catch (NumberFormatException ex) {
                        return criteriaBuilder.disjunction(); // Invalid number, don't filter
                    }
                } else if (Boolean.class.isAssignableFrom(fieldType)) {
                    try {
                        Boolean booleanValue = Boolean.parseBoolean(filterValue);
                        return criteriaBuilder.equal(fieldPath, booleanValue);
                    } catch (IllegalArgumentException ex) {
                        return criteriaBuilder.disjunction(); // Invalid boolean, don't filter
                    }
                } else if (Enum.class.isAssignableFrom(fieldType)) {
                    try {
                        // Correct Enum Handling:
                        @SuppressWarnings("unchecked")  // Safe cast because we checked the type
                        Class<Enum<?>> enumClass = (Class<Enum<?>>) fieldType;

                        // More robust enum value matching (handles variations in case):
                        for (Enum<?> enumValue : enumClass.getEnumConstants()) {
                            if (enumValue.name().equalsIgnoreCase(filterValue)) {
                                return criteriaBuilder.equal(fieldPath, enumValue);
                            }
                        }
                        return criteriaBuilder.disjunction(); // No matching enum value
                    } catch (ClassCastException ex) {
                        return criteriaBuilder.disjunction(); // Handle potential casting issues
                    }

                }
                // Add more type handling as needed (e.g., Date)

            } catch (IllegalArgumentException ex) {
                return criteriaBuilder.disjunction(); // Invalid field name, don't filter
            }

            return criteriaBuilder.conjunction(); // Default: no filter if type is not handled
        };
    }

    // Helper method for more robust number parsing:
    @SuppressWarnings("unchecked")
    private static <N extends Number> N parseNumber(String value, Class<N> targetType) {
        if (Integer.class.equals(targetType)) {
            return (N) Integer.valueOf(value);
        } else if (Long.class.equals(targetType)) {
            return (N) Long.valueOf(value);
        } else if (Double.class.equals(targetType)) {
            return (N) Double.valueOf(value);
        } // Add other number types as needed (Float, BigDecimal, etc.)
        throw new NumberFormatException("Unsupported number type: " + targetType.getSimpleName());
    }
}
