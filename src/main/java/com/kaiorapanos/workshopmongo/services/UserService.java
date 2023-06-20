package com.kaiorapanos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaiorapanos.workshopmongo.domain.User;
import com.kaiorapanos.workshopmongo.repository.UserRepository;
import com.kaiorapanos.workshopmongo.services.excepition.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public Optional<User> findById(String id) {
		User user = this.repo.findById(id).orElse(null);
		if(user == null) {
			throw new ObjectNotFoundException("Object not found!");
		}
		return Optional.ofNullable(user);
	}

}
