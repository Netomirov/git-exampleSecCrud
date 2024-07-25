package com.example.example4test.entity;


import com.example.example4test.validator.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@UserInfo
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 30)
    private String name;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;


    private Role role = Role.USER;
}
