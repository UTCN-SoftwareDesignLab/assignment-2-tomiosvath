package com.lab4.demo.user;

import com.lab4.demo.frontoffice.model.dto.BookDTO;
import com.lab4.demo.user.dto.UserDTO;
import com.lab4.demo.user.dto.UserDetailsImpl;
import com.lab4.demo.user.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public void create(@RequestBody UserDTO user) {
        userService.create(user);
    }

    @PatchMapping
    public void edit(@RequestBody UserDTO user){
        userService.edit(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
