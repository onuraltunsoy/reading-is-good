package com.alt.readingisgood.customer;

import com.alt.readingisgood.auth.JwtAuthenticationResponse;
import com.alt.readingisgood.auth.LoginRequest;
import com.alt.readingisgood.auth.SignUpRequest;
import com.alt.readingisgood.order.Order;
import com.alt.readingisgood.order.OrderRepository;
import com.alt.readingisgood.payload.ApiResponse;
import com.alt.readingisgood.role.Role;
import com.alt.readingisgood.role.RoleName;
import com.alt.readingisgood.role.RoleRepository;
import com.alt.readingisgood.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private  final CustomerRepository customerRepository;
    private  final OrderRepository orderRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    public ResponseEntity<?> registerCustomer(SignUpRequest signUpRequest) {

        if (customerRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Bu kullanıcı adı zaten kullanılıyor."),
                    HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getPassword());

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        Role customerRole = roleRepository.findByName(RoleName.USER).orElseThrow(RuntimeException::new);

        customer.setRoles(Collections.singleton(customerRole));

        Customer result = customerRepository.save(customer);

        return ResponseEntity.ok(new ApiResponse(true, "Customer "+result.getId()+" registered successfully"));
    }



    public Optional<Customer> findById(String id){
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Page<Order> getCustomerOrdersWithPagination(String customerId, Pageable pageable) {
        return orderRepository.getOrderByCustomerId(customerId,pageable);
    }
}
