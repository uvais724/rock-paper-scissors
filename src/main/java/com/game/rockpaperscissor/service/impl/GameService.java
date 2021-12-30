package com.game.rockpaperscissor.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.game.rockpaperscissor.model.GameResponse;
import com.game.rockpaperscissor.model.Score;
import com.game.rockpaperscissor.model.Token;
import com.game.rockpaperscissor.repository.TokenRepository;
import com.game.rockpaperscissor.utils.GameUtils;

@Service
public class GameService {

	@Autowired
	GameUtils utils;
	
	@Autowired
	TokenRepository tokenRepository;
	
	public ResponseEntity<String> saveToken() {
		String token = UUID.randomUUID().toString();
		
		Token tokenModel = new Token();
		tokenModel.setToken(token);
		tokenRepository.save(tokenModel);
		
		return ResponseEntity.ok(token);
	}
	
	public ResponseEntity<?> calculateScore(String token, String move) {
		GameResponse response = new GameResponse();
		
		if(!utils.checkTokenIsValid(token)) {
			return ResponseEntity.badRequest().build();
		}
		
		Score score = utils.updateScoreByMove(move);
		response.setMove(score.getMove());
		response.setTotalScore(score.getScore());
		
		if(response.getTotalScore() == 3) {
			return ResponseEntity.ok("Game Won!");
		}
		
		return ResponseEntity.ok(response);
	}
	
}
