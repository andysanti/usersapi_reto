package com.example.prueba.api.service;

import com.example.prueba.api.dto.TelefonoDto;
import com.example.prueba.api.dto.UserDto;
import com.example.prueba.api.entity.Telefono;
import com.example.prueba.api.entity.Usuario;
import com.example.prueba.api.exception.NotUniqueEmailException;
import com.example.prueba.api.repository.TelefonoRepository;
import com.example.prueba.api.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository  usuarioRepository;

    private final TelefonoRepository telefonoRepository;

    @Autowired
    ConfigPatterns configPatterns;

    public List<TelefonoDto> getAllPhonesById(Integer id){
        List<TelefonoDto> list = new ArrayList<>();
         telefonoRepository.findById(id).forEach(
                telefono -> list.add(convertToTelefonoDao(telefono))
        );
        return list;

    }


    public List<UserDto> getAll(){

        List<UserDto> list = new ArrayList<>();
         usuarioRepository.findAll().forEach(
                usuario -> {
                    UserDto userDto= UserDto.toDto(usuario);
                    userDto.setPhones(getAllPhonesById(usuario.getId()));
                    list.add(userDto);
                }
        );
        return list;
    }

    public UserDto saveUser(UserDto userDto) throws NotUniqueEmailException {

        //validar pattern
        validator(userDto);

        //validar si existe email

        List<Usuario> exits =usuarioRepository.findByEmail(userDto.getEmail());
        if(!exits.isEmpty()){
            throw new NotUniqueEmailException("el email ya esta registrado");
        }

        //convertir a entity
        Usuario usuario = toUserSave(userDto);

        //creacion de token
        String token=getJWTToken(usuario.getName());
        usuario.setToken(token);
        usuario= usuarioRepository.save(usuario);
        insertTelefono(userDto,usuario);
        UserDto userInserted = UserDto.toDto(usuario);

        userInserted.setPhones(getAllPhonesById(usuario.getId()));
        userInserted.setMensaje("registro exitoso");

        return userInserted;

    }

    private void validator(UserDto userDto) throws NotUniqueEmailException {

        String regexEmail = configPatterns.getEmailPattern();
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(userDto.getEmail());
        if(!matcher.matches()){
            throw new NotUniqueEmailException("el email no tiene formato aaa@domain.cl");
        }

        String regexPassword = configPatterns.getPasswordPattern();

        pattern = Pattern.compile(regexPassword);

        matcher = pattern.matcher(userDto.getPassword());
        if(!matcher.matches()){
            throw new NotUniqueEmailException("el password debe tener al inicio una mayuscula,cualquier minuscula y dos numeros al final");
        }
    }

    private void insertTelefono(UserDto userDto,Usuario usuario){
        List<Telefono> telefonos;
        if(userDto.getPhones()!=null) {
            if (!userDto.getPhones().isEmpty()) {
                telefonos = userDto.getPhones().stream()
                        .map(telefonoDto -> getTelefono(telefonoDto, usuario))
                        .collect(Collectors.toList());

                telefonoRepository.saveAll(telefonos);
            }
        }

    }

    private Usuario toUserSave(UserDto userDto){
        Usuario usuario = new Usuario();

        usuario.setName(userDto.getName());
        usuario.setEmail(userDto.getEmail());
        usuario.setPassword(userDto.getPassword());
        usuario.setCreated(LocalDateTime.now());
        usuario.setActive(true);
        usuario.setToken("");
        usuario.setModified(LocalDateTime.now());
        usuario.setLast_login(LocalDateTime.now());

        return usuario;
    }

    private Telefono getTelefono(TelefonoDto telefonoDao,Usuario usuario){
        Telefono telefono = new Telefono();
        telefono.setCityCode(telefonoDao.getCitycode());
        telefono.setCountryCode(telefonoDao.getCountrycode());
        telefono.setNumero(telefonoDao.getNumber());
        telefono.setId(usuario.getId());

        return telefono;
    }

    private  TelefonoDto convertToTelefonoDao(Telefono telefono){
        TelefonoDto telefonoDto = new TelefonoDto();
        telefonoDto.setCitycode(telefono.getCityCode());
        telefonoDto.setCountrycode(telefono.getCountryCode());
        telefonoDto.setNumber(telefono.getNumero());

        return telefonoDto;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey123456ABCDEFmySecretKey123456ABCDEFmySecretKey123456ABCDEF";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256,
                        secretKey.getBytes()).compact();
//Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return "Bearer " + token;
    }

}
