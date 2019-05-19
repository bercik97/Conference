package pl.robert.app.user.domain;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import pl.robert.app.shared.GlobalAuthorizationEntryPoint;
import pl.robert.app.user.domain.dto.CreateUserDto;
import pl.robert.app.user.domain.query.UserQueryDto;

@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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

    public long findIdByName(String name) {
        validator.checkInputName(name);
        return service.findIdByName(name);
    }

    public void update(Long id, String email) {
        validator.checkInputEmail(email);
        service.update(id, email);
    }

    public void login(String name) {
        validator.checkInputName(name);
        authorizationService.login(name);
    }

    public void logout() {
        authorizationService.logout();
    }
}
