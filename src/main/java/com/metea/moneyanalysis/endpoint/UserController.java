package com.metea.moneyanalysis.endpoint;

import com.metea.moneyanalysis.domain.User;
import com.metea.moneyanalysis.dto.UserReadDTO;
import com.metea.moneyanalysis.dto.UserWriteDTO;
import com.metea.moneyanalysis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id User", response = UserReadDTO.class)
    public ResponseEntity<UserReadDTO> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "Create User", response = User.class)
    public ResponseEntity<UserReadDTO> createUser(@Valid @RequestBody UserWriteDTO user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete User", response = Boolean.class)
    public ResponseEntity<Boolean> deleteUser(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
