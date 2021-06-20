package in.nareshit.raghu.springsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nareshit.raghu.springsocial.exception.ResourceNotFoundException;
import in.nareshit.raghu.springsocial.model.User;
import in.nareshit.raghu.springsocial.repository.UserRepository;
import in.nareshit.raghu.springsocial.security.CurrentUser;
import in.nareshit.raghu.springsocial.security.UserPrincipal;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
