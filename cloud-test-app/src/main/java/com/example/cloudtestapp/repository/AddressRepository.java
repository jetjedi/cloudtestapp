package com.example.cloudtestapp.repository;

import com.example.cloudtestapp.model.Address;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/*
  I keep this repository tiny. I only need a way
  to fetch addresses for a specific user and the basic CRUD that CrudRepository gives me.
*/
public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findByUserId(Long userId);
}
