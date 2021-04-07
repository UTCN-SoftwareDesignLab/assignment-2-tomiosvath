package com.lab4.demo.user;

import com.lab4.demo.user.dto.UserDTO;
import com.lab4.demo.user.dto.UserDetailsImpl;
import com.lab4.demo.user.dto.UserListDTO;
import com.lab4.demo.user.dto.UserMinimalDTO;
import com.lab4.demo.user.mapper.UserMapper;
import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.Role;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }


    /*public UserDetailsImpl create(UserDetailsImpl user) {
        userRepository.save(userMapper.fromUserDetailsImpl(user));
        return null;
    }*/

    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
    }


    public void edit(UserDTO user) {
        User newUser = User.builder()
                .id(user.getId()) //no id for a create operation!!
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .email(user.getEmail())
                .build();

        Set<Role> roles = new HashSet<>();

        if (user.getRole() == null){
            Role defaultRole = roleRepository.findByName(ERole.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Cannot find CUSTOMER role"));
            roles.add(defaultRole);
        }
        else{
            Role role = roleRepository.findByName(ERole.valueOf(user.getRole()))
                    .orElseThrow(() -> new RuntimeException("Cannot find role: " + user.getRole()));
            roles.add(role);
        }

        newUser.setRoles(roles);
        userRepository.save(newUser);
    }

    public void create(UserDTO user) {
        User newUser = User.builder()
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .email(user.getEmail())
                .build();

        Set<Role> roles = new HashSet<>();

        if (user.getRole() == null){
            Role defaultRole = roleRepository.findByName(ERole.CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Cannot find CUSTOMER role"));
            roles.add(defaultRole);
        }
        else{
            Role role = roleRepository.findByName(ERole.valueOf(user.getRole()))
                    .orElseThrow(() -> new RuntimeException("Cannot find role: " + user.getRole()));
            roles.add(role);
        }

        newUser.setRoles(roles);
        userRepository.save(newUser);
    }
}
