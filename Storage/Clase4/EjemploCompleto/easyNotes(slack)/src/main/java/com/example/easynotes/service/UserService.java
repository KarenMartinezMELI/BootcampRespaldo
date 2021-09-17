package com.example.easynotes.service;

import com.example.easynotes.dto.UserRequestDTO;
import com.example.easynotes.dto.UserResponseDTO;
import com.example.easynotes.dto.UserResponseWithCantNotesDTO;
import com.example.easynotes.dto.UserResponseWithNotesDTO;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.model.User;
import com.example.easynotes.repository.UserRepository;
import com.example.easynotes.utils.ListMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;


@Service
public class UserService implements IUserService {

    UserRepository userRepository;

    ModelMapper modelMapper;

    ListMapper listMapper;

    @Autowired
    UserService(UserRepository userRepository,
                ModelMapper modelMapper,
                ListMapper listMapper) {
        this.userRepository = userRepository;
        this.listMapper = listMapper;

        //Converter used to retrieve cant of user's notes
        Converter<Set<Note>, Integer> cantNotesUserConverter = new AbstractConverter<Set<Note>, Integer>() {
            @Override
            protected Integer convert(Set<Note> notes) {
                return notes.size();
            }
        };
        //Load converter to modelMapper used when we want convert from User to UserResponseWithCantNotesDTO
        modelMapper.typeMap(User.class, UserResponseWithCantNotesDTO.class).addMappings( (mapper) ->
            mapper.using(cantNotesUserConverter)
                    .map(User::getAuthorNotes, UserResponseWithCantNotesDTO::setCantNotes)
        );

        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> listUsers = userRepository.findAll();
        return listMapper.mapList(listUsers, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseWithNotesDTO> getAllUsersWithNotes() {
        List<User> listUsers = userRepository.findAll();
        return listMapper.mapList(listUsers, UserResponseWithNotesDTO.class);
    }

    @Override
    public List<UserResponseWithCantNotesDTO> getAllUsersWithCantNotes() {
        List<User> listUsers = userRepository.findAll();
        return listMapper.mapList(listUsers, UserResponseWithCantNotesDTO.class);
    }

    @Override
    public UserResponseDTO createUSer(UserRequestDTO userRequestDTO) {
        User user = modelMapper.map(userRequestDTO, User.class);
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseWithNotesDTO getUserWithNotesById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return modelMapper.map(user, UserResponseWithNotesDTO.class);
    }
    @Override
    public UserResponseWithCantNotesDTO getUserWithCantNotesById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return modelMapper.map(user, UserResponseWithCantNotesDTO.class);
    }


    @Override
    public UserResponseDTO updateUser(Long userId,
                                      UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());

        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

    }
}
