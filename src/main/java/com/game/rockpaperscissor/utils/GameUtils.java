package com.game.rockpaperscissor.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.rockpaperscissor.model.Score;
import com.game.rockpaperscissor.repository.ScoreRepository;
import com.game.rockpaperscissor.repository.TokenRepository;

@Component
public class GameUtils {

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	public boolean checkTokenIsValid(String token) {
		return tokenRepository.findByToken(token) != null ? true: false;
	}
	
	public Score updateScoreByMove(String move) {
		Score newScore = new Score();
		List<Score> scores = scoreRepository.findSecondLastRecord();
		
		String previousMove = null;
		int previousScore = 0;
		
		if(scores.size() > 0) {
			previousMove = scores.get(0).getMove();
			previousScore = scores.get(0).getScore();
		} else {
			newScore.setMove(move);
			newScore.setScore(0);
			scoreRepository.save(newScore);
			return newScore;
		}
		
		
		newScore.setMove(move);
		newScore.setScore(previousScore + calculateTotalScore(move, previousMove));
		scoreRepository.save(newScore);
		
		return newScore;
	}
	
	public int calculateTotalScore(String currentMove, String previousMove) {
		if(currentMove.equalsIgnoreCase("ROCK") && previousMove.equalsIgnoreCase("SCISSORS")
				|| currentMove.equalsIgnoreCase("PAPER") && previousMove.equalsIgnoreCase("ROCK")
				|| currentMove.equalsIgnoreCase("SCISSORS") && previousMove.equalsIgnoreCase("PAPER")) {
			return 1;
		}
		
		return 0;
	}
}
