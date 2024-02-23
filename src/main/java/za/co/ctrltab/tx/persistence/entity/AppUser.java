package za.co.ctrltab.tx.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.ctrltab.tx.dto.Role;
import za.co.ctrltab.tx.persistence.converters.CollectionStringConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    private boolean disabled = false;

    @Builder.Default
    @Column(nullable = false)
    private boolean locked = false;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime createdTimestamp = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime passwordExpiryTimestamp = LocalDateTime.now().plusMonths(3);

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();

    @Convert(converter = CollectionStringConverter.class)
    @Column(name = "roles", nullable = false)
    @Builder.Default
    private List<Role> roles = new ArrayList<>();

}
