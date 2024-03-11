package com.zubaray.userapi.controller;

import com.zubaray.userapi.dto.ResponseDto;
import com.zubaray.userapi.entity.UserApi;
import com.zubaray.userapi.exception.UserApiNotFoundException;
import com.zubaray.userapi.service.UserApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Users API")
public class UserApiController {

    private UserApiService userApiService;

    public UserApiController(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    @Operation(summary = "Get a user by id", description = "Returns a user as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved",
                    content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserApi.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Not found - The product was not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class))
                    })
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserApiById(
            @PathVariable
            @Parameter(name = "id", description = "User id", example = "1")
                    Long id) {
        try {
            return ResponseEntity.ok(userApiService.getUserApiById(id));
        } catch(UserApiNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDto.builder().message("Usuario no encontrado").build());
        }
    }

    @Operation(summary = "Get all users", description = "Returns a list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserApi.class))
                    })
    })
    @GetMapping("/all")
    public ResponseEntity<List<UserApi>> getAllUserApi() {
        return ResponseEntity.ok(userApiService.getAllUserApi());
    }

    @PostMapping("/create")
    public ResponseEntity<UserApi> createUserApi(@Valid @RequestBody UserApi userApi) {
        UserApi userSaved = userApiService.createUserApi(userApi);
        return ResponseEntity.created(getLocation(userSaved)).body(userApi);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> putUserApi(@PathVariable Long id, @Valid @RequestBody UserApi userApi) {
        try {
            UserApi userSaved = userApiService.putUserApi(id, userApi);
            return ResponseEntity.created(getLocation(userSaved)).body(userSaved);
        } catch(UserApiNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDto.builder().message("Usuario no encontrado").build());
        }
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> updateUserApi(@PathVariable Long id, @RequestBody UserApi userApi) {
        try {
            UserApi userSaved = userApiService.updateUserApi(id, userApi);
            return ResponseEntity.created(getLocation(userSaved)).body(userSaved);
        } catch(UserApiNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDto.builder().message("Usuario no encontrado").build());
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserApi(@PathVariable Long id) {
        userApiService.deleteUserApi(id);
    }

    private URI getLocation(UserApi userApi) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id/{id}")
                .buildAndExpand(userApi.getId())
                .toUri();
    }
}
