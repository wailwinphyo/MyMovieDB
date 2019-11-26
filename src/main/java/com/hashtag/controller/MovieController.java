package com.hashtag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hashtag.dao.MovieRepository;
import com.hashtag.entity.Movie;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

	@Autowired
	MovieRepository movieRepo;

	@GetMapping
	public Iterable<?> movies(@RequestParam(name = "_page", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "_limit", defaultValue = "10") Integer pageSize) {
		Pageable p = PageRequest.of(pageNum - 1, pageSize);
		return movieRepo.findAll(p);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") Integer id) {
		try {
			Movie m = movieRepo.findById(id).get();
			return ResponseEntity.ok(m);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

//	for Admin Panel

	@PostMapping
	public ResponseEntity<?> saveNewMovie(@RequestBody Movie m) {
		movieRepo.save(m);
		return ResponseEntity.ok(m);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") Integer id, @RequestBody Movie m) {
		m.setId(id);
		movieRepo.save(m);
		return ResponseEntity.ok(m);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") Integer id) {
		try {
			Movie m = movieRepo.findById(id).get();
			movieRepo.deleteById(id);
			return ResponseEntity.ok(m);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

}
