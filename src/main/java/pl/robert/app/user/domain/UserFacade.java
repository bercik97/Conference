package pl.robert.app.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;

import pl.robert.app.user.domain.dto.CreateUserDto;
import pl.robert.app.user.domain.query.UserQueryDto;
import pl.robert.app.shared.GlobalAuthorizationEntryPoint;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserFacade {

    UserValidator validator;
    UserService service;
    UserAuthorizationService authorizationService;

    public void create(CreateUserDto dto) {
        validator.checkInputDto(dto);
        service.create(dto);
    }

    public UserQueryDto read() {
        return service.read(GlobalAuthorizationEntryPoint.name);
    }

    public void update(String name, String email) {
        validator.checkInputEmail(email);
        service.update(name, email);
    }

    public void login(String name) {
        validator.checkInputName(name);
        authorizationService.login(name);
    }

    public void logout() {
        authorizationService.logout();
    }
}
