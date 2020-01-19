package com.files.service;

import com.files.model.Address;
import com.files.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void save(Address address){
        addressRepository.save(address);
    }

    public Address findById(int id){
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public void update(Address files){
        addressRepository.save(files);
    }

    public void delete(int id){
        addressRepository.deleteById(id);
    }


}
