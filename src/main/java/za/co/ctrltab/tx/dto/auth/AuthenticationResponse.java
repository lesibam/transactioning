package za.co.ctrltab.tx.dto.auth;

import lombok.Builder;

public record AuthenticationResponse(String token) {
    @Builder
    public AuthenticationResponse {}
}
