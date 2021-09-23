package com.alt.readingisgood.customer;

import com.alt.readingisgood.audit.Auditable;
import com.alt.readingisgood.order.Order;
import com.alt.readingisgood.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document(collection = "customer")
@NoArgsConstructor
public class Customer extends Auditable {

    @Id
    private String id ;

    private String name;

    private String username;

    @JsonIgnore
    private String password;

    @DBRef
    private List<Order> orders;

    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    public Customer(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
