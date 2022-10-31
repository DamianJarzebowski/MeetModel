package DJ.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private Long id;

    private String name;

    private String lastName;

    private int age;

    private String description;

    private String email;



}
