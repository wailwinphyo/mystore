package com.codex.mystore.models.user;

import com.codex.mystore.models.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    private String address;
    private String phoneNumber;

    private String password;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            )
    )
    private Collection<Role> roles;


    public User(UserDto userDto) {
        this.name = userDto.getName();
        this.address = userDto.getAddress();
        this.phoneNumber = userDto.getPhNumber();
    }

    public User(Long id) {
        this.id = id;
    }
}
