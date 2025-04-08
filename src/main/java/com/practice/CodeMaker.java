package com.practice;

import java.util.Random;

public class CodeMaker {

    private static char[] secretCode ;

    public void generateCode(){
        secretCode = new char[4];
        Random rand = new Random();
        for(int i=0;i<secretCode.length;i++) {
            char c = GameCoordinator.getAvailableCode().get(rand.nextInt(GameCoordinator.getAvailableCode().size()));
            secretCode[i] = c;
        }
    }

    public Hints[] evaluateGuesses(char[] guessCode){
        Hints[] hints = new Hints[4];
        for(int i=0;i<guessCode.length;i++){

            if(guessCode[i] == secretCode[i]){
                hints[i] = Hints.RED;
                guessCode[i] = '-';
                secretCode[i] = '-';
            } else if(checkIfExists(guessCode[i])){
                hints[i] = Hints.WHITE;
            } else{
                hints[i] = Hints.EMPTY;
            }
        }
        return hints;
    }

    private boolean checkIfExists(char guessChar) {
        for(int i=0; i < secretCode.length ; i++){
            if(secretCode[i] !='-' && guessChar == secretCode[i]){
                secretCode[i] = '-';
                return true;
            }
        }
        return false;
    }
}
