package com.cursos.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursos.dsmovie.entities.Movie;
import com.cursos.dsmovie.entities.Score;
import com.cursos.dsmovie.entities.User;
import com.cursos.dsmovie.entities.dto.ScoreDTO;
import com.cursos.dsmovie.repositories.MovieRepository;
import com.cursos.dsmovie.repositories.ScoreRepository;
import com.cursos.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {

	@Autowired
	private MovieRepository movieRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ScoreRepository scoreRepo;
	
	@Transactional
	public void saveScore(ScoreDTO dto) {
		
		User user = userRepo.findByEmail(dto.getEmail());
		
		if(user == null) {
			user = new User(null, dto.getEmail());
			user  = userRepo.saveAndFlush(user);
		}
		
		Movie movie = movieRepo.findById(dto.getMovieId()).get();
		
		Score score = new Score(movie, user, dto.getScore());
		score = scoreRepo.saveAndFlush(score);
		
		double sum = 0.0;
		for (Score s: movie.getScores()) {
			sum = sum + s.getValue();
		}
		
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movieRepo.save(movie);
	}
	
	
}
