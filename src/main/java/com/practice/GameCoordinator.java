package com.practice;

import java.util.Arrays;
import java.util.List;

public class GameCoordinator {

    private final List<Character> availableCode = List.of('A','B','C','W','Y','Z');
    private final CodeMaker codeMaker;
    private final CodeBreaker codeBreaker;
    private final int maxAttempts;
    private int attempts;

    public GameCoordinator(CodeMaker codeMaker, CodeBreaker codeBreaker,int maxAttempts) {
        this.codeMaker = codeMaker;
        this.codeBreaker = codeBreaker;
        this.maxAttempts = maxAttempts;
        attempts = maxAttempts;
    }

    public static List<Character> getAvailableCode(){
        return availableCode;
    }

    public void startGame(){
        System.out.println("Initialising code");
        codeMaker.generateCode();

        while(attempts >0){
            System.out.println("CodeBreaker's turn");
            char[] guessedCode = codeBreaker.play();

            Hints[] result = codeMaker.evaluateGuesses(guessedCode);

            if(Arrays.stream(result).allMatch(hints -> hints.compareTo(Hints.RED) == 0)){
                System.out.println("Game Won");
                return;
            }

            attempts--;
        }

        System.out.println("Game Lost /n exit");
    }
}
