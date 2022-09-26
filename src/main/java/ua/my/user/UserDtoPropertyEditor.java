package ua.my.user;

import java.beans.PropertyEditorSupport;
import java.util.Objects;
import java.util.UUID;

public class UserDtoPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (Objects.isNull(text) || text.isBlank()) {
            setValue(null);
            return;
        }
        final String[] split = text.split(",");
        UserDto dto = new UserDto();
        dto.setId(UUID.fromString(split[0].trim()));
        dto.setUsername(split[1].trim());
        setValue(dto);
    }
}
