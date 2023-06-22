package com.kaiorapanos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaiorapanos.workshopmongo.domain.Post;
import com.kaiorapanos.workshopmongo.repository.PostRepository;
import com.kaiorapanos.workshopmongo.services.excepition.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Optional<Post> findById(String id) {
		Post user = this.repo.findById(id).orElse(null);
		if(user == null) {
			throw new ObjectNotFoundException("Object not found!");
		}
		return Optional.ofNullable(user);
	}
	
	public List<Post> findByTitle (String text){
		return repo.searchTitle(text);
	}
}
