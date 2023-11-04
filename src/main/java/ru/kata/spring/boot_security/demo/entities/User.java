package ru.kata.spring.boot_security.demo.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iD;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Enter field name")
    @Size(min = 2, max = 30, message = "Name should be between 2 to 30")
    private String name;

    @Column(name = "lastName", nullable = false)
    @NotEmpty(message = "Enter field lastName")
    @Size(min = 2, max = 30, message = "Name should be between 2 to 30")
    private String lastName;

    @Column(name = "age", nullable = false)
    @NotNull(message = "Enter field age")
    @Min(value = 10, message = "min 10")
    @Max(value = 110, message = "max 110")
    private Integer age;

    @Column(name = "password", nullable = false)
    @NotNull(message = "Enter password")
    private String password;

    @Column(name = "login", nullable = false, unique = true)
    @NotNull(message = "Enter login")
    @Size(min = 2, max = 20, message = "Login should be min 2, max 20")
    private String login;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public User() {
    }

    public User(String name, String lastName, Integer age, String password, String login, List<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.login = login;
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getID() {
        return iD;
    }

    public void setID(Long iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public String getStringRoles() {
        return this.roles.stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(age, user.age) && Objects.equals(iD, user.iD) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && Objects.equals(login, user.login) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, name, lastName, age, password, login, roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
