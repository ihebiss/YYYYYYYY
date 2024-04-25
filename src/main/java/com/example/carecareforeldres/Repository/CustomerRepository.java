package com.example.carecareforeldres.Repository;

import com.example.carecareforeldres.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
