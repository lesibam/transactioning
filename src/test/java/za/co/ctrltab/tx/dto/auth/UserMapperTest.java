package za.co.ctrltab.tx.dto.auth;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import za.co.ctrltab.tx.dto.Role;
import za.co.ctrltab.tx.persistence.entity.AppUser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {


    private final UserMapper mapper = new UserMapper();
    @Test
    void map() {
        UserDetails mapped = mapper.map(AppUser.builder()
                        .locked(false)
                .password("pwd").username("uawen")
                .roles(List.of(Role.USER)).build());
        assertNotNull(mapped);
    }

    @Test
    void testTimezone() {
        LocalDateTime nyDateTime = LocalDateTime.now(ZoneId.of("America/New_York"));
        LocalDateTime londonDateTime = LocalDateTime.now(ZoneId.of("Europe/London"));

        ZonedDateTime nyZonedDateTime = nyDateTime.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime londonZonedDateTime = londonDateTime.atZone(ZoneId.of("Europe/London"));

        DateTimeFormatter formatterWithOffset = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z Z");
        System.out.println("New York Time: " + nyZonedDateTime.format(formatterWithOffset));
        System.out.println("London Time: " + londonZonedDateTime.format(formatterWithOffset));
    }
}
