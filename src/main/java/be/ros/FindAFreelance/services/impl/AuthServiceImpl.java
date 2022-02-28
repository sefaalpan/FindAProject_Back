package be.ros.FindAFreelance.services.impl;

import be.ros.FindAFreelance.configs.jwt.JwtTokenProvider;
import be.ros.FindAFreelance.exceptions.UsernameExistsException;
import be.ros.FindAFreelance.mappers.impl.FreelanceMapper;
import be.ros.FindAFreelance.mappers.impl.UserMapper;
import be.ros.FindAFreelance.models.dtos.FreelanceDto;
import be.ros.FindAFreelance.models.dtos.UserDto;
import be.ros.FindAFreelance.models.entities.Discriminator;
import be.ros.FindAFreelance.models.entities.Freelance;
import be.ros.FindAFreelance.models.entities.User;
import be.ros.FindAFreelance.models.forms.UserLoginForm;
import be.ros.FindAFreelance.models.forms.UserRegisterForm;
import be.ros.FindAFreelance.repositories.UserRepository;
import be.ros.FindAFreelance.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final FreelanceMapper freelanceMapper;
    private final UserMapper userMapper;
    private final UserServiceImpl userService;


    public AuthServiceImpl(UserRepository repository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, FreelanceMapper freelanceMapper, UserMapper userMapper, UserServiceImpl userService) {
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.freelanceMapper = freelanceMapper;
        this.userMapper = userMapper;
        this.userService = userService;
    }

//    public AuthServiceImpl(UserRepository repository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, FreelanceMapper freelanceMapper, UserMapper userMapper) {
//        this.repository = repository;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.authenticationManager = authenticationManager;
//        this.freelanceMapper = freelanceMapper;
//        this.userMapper = userMapper;
//    }

    @Override
    public UserDto login(UserLoginForm form) {
        try {
            User user = repository.findByUsername(form.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User does not exist!"));

            if (user instanceof Freelance) {
                Freelance freelance = (Freelance) user;
                this.authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
                FreelanceDto dto = this.freelanceMapper.toDto(freelance);
                dto.setToken(jwtTokenProvider.createToken(freelance));
                return dto;
            }
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
            UserDto dto = this.userMapper.toDto(user);
            dto.setToken(jwtTokenProvider.createToken(user));

            return dto;
        } catch (Exception e) {
            e.getMessage();
            throw new IllegalArgumentException("incorrect credentials !");
        }
    }

    @Override
    public UserDto register(UserRegisterForm form) {
        if (form == null) return null;

        if(this.userService.findByUsername(form.getUsername()) != null)
            throw new UsernameExistsException();

        if (form.getDiscriminator().equals(Discriminator.FREELANCE)) {
            Freelance freelance = freelanceMapper.formToEntity(form);
            FreelanceDto dto = freelanceMapper.toDto(repository.save(freelance));
            dto.setToken(jwtTokenProvider.createToken(freelance));
            return dto;
        }

        User user = userMapper.formToEntity(form);
        UserDto dto = userMapper.toDto(repository.save(user));
        dto.setToken(jwtTokenProvider.createToken(user));
        return dto;
    }
}
