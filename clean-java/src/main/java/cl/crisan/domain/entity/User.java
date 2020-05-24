package cl.crisan.domain.entity;

import lombok.Builder;

@Builder
public class User {

    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;



}
