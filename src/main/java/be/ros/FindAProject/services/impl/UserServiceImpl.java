package be.ros.FindAProject.services.impl;

import be.ros.FindAProject.mappers.impl.UserMapper;
import be.ros.FindAProject.models.dtos.UserDto;
import be.ros.FindAProject.models.entities.Freelance;
import be.ros.FindAProject.models.entities.User;
import be.ros.FindAProject.models.forms.UserRegisterForm;
import be.ros.FindAProject.services.BaseService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import be.ros.FindAProject.repositories.UserRepository;
import be.ros.FindAProject.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, BaseService<UserDto, UserRegisterForm, Long> {

    private final UserRepository repository;
    private final UserMapper mapper;
//    private final FreelanceRepository freelanceRepository;


    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist!"));
    }


    @Override
    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getOneById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));

    }

    @Override
    public UserDto insert(UserRegisterForm form) {
        return this.mapper.toDto(this.repository.save(this.mapper.formToEntity(form)));
    }

    @Override
    public UserDto update(UserRegisterForm form, Long id) {
        return null;
    }

    @Override
    public UserDto deleteById(Long id) {
        UserDto dto = mapper.toDto(repository.findById(id).orElse(null));
        if (dto == null)
            return null;

        repository.deleteById(id);
        return dto;
    }

    public UserDto findByUsername(String username) {

        User u = repository.findByUsername(username).orElse(null);
        if(u==null)
            return null;

        Freelance f;
        if (u instanceof Freelance) {
            f = (Freelance) u;
            return mapper.toDto(f);
//            return freelanceMapper.toDto(f);
        }
        return mapper.toDto(u);
    }
}
