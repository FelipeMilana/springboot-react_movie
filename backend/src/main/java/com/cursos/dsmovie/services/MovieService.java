package com.cursos.dsmovie.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursos.dsmovie.entities.Movie;
import com.cursos.dsmovie.entities.dto.MovieDTO;
import com.cursos.dsmovie.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepo;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable page) {
		return movieRepo.findAll(page).map(x -> new MovieDTO(x));
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> movie = movieRepo.findById(id);
		return new MovieDTO(movie.get());
	}
}
