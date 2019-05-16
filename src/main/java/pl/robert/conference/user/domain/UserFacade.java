package pl.robert.conference.user.domain;

import com.vaadin.ui.Notification;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.robert.conference.user.domain.dto.CreateUserDto;
import pl.robert.conference.user.domain.dto.UserDto;

import javax.transaction.Transactional;

@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFacade {

    UserValidator validator;
    UserService service;

    public void create(CreateUserDto dto) {
        validator.checkInputData(dto);
        service.create(dto);
    }

    public UserDto read(Long id) {
        return service.read(id);
    }

    public void update(Long id, String email) {
        service.update(id, email);
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public Page<UserDto> readAll(Pageable pageable) {
        return service.readAll(pageable);
    }
}
