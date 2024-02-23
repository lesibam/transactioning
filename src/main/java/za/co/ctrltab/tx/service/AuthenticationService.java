package za.co.ctrltab.tx.service;

import za.co.ctrltab.tx.dto.auth.AuthenticationRequest;
import za.co.ctrltab.tx.dto.auth.AuthenticationResponse;
import za.co.ctrltab.tx.dto.auth.RegistrationRequest;
import za.co.ctrltab.tx.service.exception.TransactioningException;

public interface AuthenticationService {

    AuthenticationResponse register(RegistrationRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request) throws TransactioningException;

}
