package za.co.ctrltab.tx.persistence.converters;

import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Objects;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import za.co.ctrltab.tx.dto.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class CollectionStringConverter implements AttributeConverter<Collection<Role>, String> {
    private static final String STRING_SPLITTER = ",";
    @Override
    public String convertToDatabaseColumn(Collection<Role> stringCollection) {
        return Collections.isEmpty(stringCollection) ? stringCollection.stream().map(Role::name)
                .collect(Collectors.joining(STRING_SPLITTER)) : "";
    }

    @Override
    public List<Role> convertToEntityAttribute(String string) {
        return !Objects.isEmpty(string) ? Arrays.stream(string.split(STRING_SPLITTER)).map(Role::valueOf)
                .collect(Collectors.toList()) : new ArrayList<>();
    }
}
