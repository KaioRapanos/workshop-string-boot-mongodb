package com.kaiorapanos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaiorapanos.workshopmongo.DTO.UserDTO;
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
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = repo.findById(obj.getId()).orElse(obj);
		findById(newObj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
