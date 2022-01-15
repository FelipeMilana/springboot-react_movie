package com.cursos.dsmovie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.dsmovie.entities.dto.ScoreDTO;
import com.cursos.dsmovie.services.ScoreService;

@RestController
@RequestMapping(value = "/scores")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	@PutMapping
	public ResponseEntity<Void> saveScore(@RequestBody ScoreDTO dto) {
		scoreService.saveScore(dto);
		return ResponseEntity.noContent().build();
	}
}
