package com.alt.readingisgood.customer;


import com.alt.readingisgood.auth.LoginRequest;
import com.alt.readingisgood.auth.SignUpRequest;
import com.alt.readingisgood.order.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("customer")
@Api(value = "Customer controller")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return customerService.authenticateUser(loginRequest);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody SignUpRequest signUpRequest) {
        return customerService.registerCustomer(signUpRequest);
    }

    @GetMapping("/getCustomerOrder")
    public Page<Order> getCustomerOrdersWithPagination(@RequestParam String customerId, @RequestParam Integer pageSize,
                                                        @RequestParam Integer page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return customerService.getCustomerOrdersWithPagination(customerId,pageable);

    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }


}
