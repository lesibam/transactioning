package za.co.ctrltab.tx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.ctrltab.tx.dto.auth.AuthenticationRequest;
import za.co.ctrltab.tx.dto.auth.AuthenticationResponse;
import za.co.ctrltab.tx.dto.auth.RegistrationRequest;
import za.co.ctrltab.tx.service.AuthenticationService;
import za.co.ctrltab.tx.service.exception.TransactioningException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/${app.version}/authentication",
        consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody final RegistrationRequest request) {
        return ResponseEntity.ofNullable(authenticationService.register(request));
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody final AuthenticationRequest request) throws TransactioningException {
        return ResponseEntity.ofNullable(authenticationService.authenticate(request));
    }

}
