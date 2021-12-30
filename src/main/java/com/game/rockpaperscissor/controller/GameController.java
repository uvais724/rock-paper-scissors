package com.game.rockpaperscissor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.rockpaperscissor.model.GameResponse;
import com.game.rockpaperscissor.service.impl.GameService;

@RequestMapping("v1")
@RestController
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping("/start")
	public ResponseEntity<String> generateToken() {
		return gameService.saveToken();
	}
	
	@GetMapping("/{token}/{move}")
	public ResponseEntity<?> playMove(@PathVariable("token") String token, @PathVariable("move") String move) {
		return gameService.calculateScore(token, move);
	}
}
