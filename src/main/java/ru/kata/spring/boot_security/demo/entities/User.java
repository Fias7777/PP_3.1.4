package ru.kata.spring.boot_security.demo.entities;


import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;



@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    @NotEmpty(message = "Enter field name")
    @Size(min = 2, max = 30, message = "Name should be between 2 to 30")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "Enter field lastName")
    @Size(min = 2, max = 20, message = "Username should be min 2, max 20")
    private String lastName;

    @Column(name="age")
    @NotNull
    @Min(value = 10, message = "min 10")
    @Max(value = 110, message = "max 110")
    private int age;

    @Column(name="username", unique = true)
    @NotNull
    @Size(min = 2, max = 20, message = "Username should be min 2, max 20")
    private String username;

    @Column(name="password")
    @NotNull
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Role> roles;


    public User() {

    }

    public User(String name, String lastName, int age, String username, String password, Set<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getRolesToString() {
        List<Role> list = new ArrayList<>(getRoles());
        if (list.isEmpty()) {
            return "No Roles";
        } else {
            StringBuilder str = new StringBuilder(list.get(0).toString());
            if (list.size() == 2) {
                str.append(" ").append(list.get(1).toString());
            }
            return String.valueOf(str);
        }
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}