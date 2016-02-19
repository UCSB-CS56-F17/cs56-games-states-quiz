package edu.ucsb.cs56.projects.games.states_quiz;

import javax.swing.*;

/**
 * Created by neidler on 2/26/15.
 */
public class CapitalQuestionManager extends QuestionManager {
    int guesses = 0;
    
    public CapitalQuestionManager(GamePanel parent) {
        super(parent);
    }

    @Override
    public void askNextQuestion() {
        if(!randStateIndexes.isEmpty()) {
            gamePanel.setQuestionTextArea("Click on: " + states.get(currentQuestion).getCapital() + "\n");
            mapPanel.setAnswer(mapPanel.stateButtons[currentQuestion]);
        }
        else {
            gamePanel.setQuestionTextArea("You're finished! Yay!");
        }
    }

    @Override
    public void receiveAnswer(JButton answerButton) {
        if (answerButton == mapPanel.stateButtons[currentQuestion]) {
            gamePanel.setQuestionTextArea("Congrats! ");
            gamePanel.setAnswerTextArea(states.get(currentQuestion).getCapital());

            correctStates.add(states.get(currentQuestion));

            randStateIndexes.remove(randIndex);
            randIndex = (int) (Math.random() * (randStateIndexes.size()-1));
            currentQuestion = randStateIndexes.get(randIndex);

	    gamePanel.setHintButtonVisible(false);

	    if (guesses==0){
                currentScore++;
            }
            else{
                this.guesses = 0;
            }
        } else {
            this.guesses++;
	    if (guesses == 3)
		gamePanel.setHintButtonVisible(true);
	    
            gamePanel.setQuestionTextArea("Nope! ");
        }

        gamePanel.setQuestionTextArea("Your current score is: " + currentScore + "\n");
        this.askNextQuestion();
    }
}
