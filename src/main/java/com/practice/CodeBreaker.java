package com.practice;

import java.util.*;

public class CodeBreaker {

    private Hints[] hints;
    private char[] prevCode;
    private List<Character> confirmedPositions; // From RED
    private Set<Character> mustInclude; // From WHITE
    private Set<Character> exclude; // From EMPTY
    private Random rand;

    public CodeBreaker() {
        this.hints = new Hints[0]; // Initialize to avoid null
        this.prevCode = null;
        this.confirmedPositions = new ArrayList<>(List.of(null, null, null, null));
        this.mustInclude = new HashSet<>();
        this.exclude = new HashSet<>();
        this.rand = new Random();
    }


    public char[] play() {
        char[] code = new char[4];

        // First guess: random code
        if (hints.length == 0 || prevCode == null) {
            prevCode = generateCode();
            return prevCode;
        }

        // Build next guess based on feedback
        int confimedCount = 0,  whiteCount = 0;
        for (int i = 0; i < 4; i++) {
            if (hints[i].equals(Hints.RED)) {
                code[i] = prevCode[i]; // Keep confirmed letter
                confimedCount++;
            } else if(hints[i].equals(Hints.WHITE)){
                code[i] = 0; // Placeholder for now
                mustInclude.add(prevCode[i]);
                whiteCount++;
            } else{
                code[i] = 0;
                exclude.add(prevCode[i]);
            }
        }

        for(int i=0; i<4; i++){
            if(code[i] == 0){

            }
        }

        if(confimedCount == code.length){
            System.out.println("win");
            System.exit(0);
        }



        return code;
    }

    private char[] generateCode() {
        char[] guessCode = new char[4];
        for (int i = 0; i < guessCode.length; i++) {
            guessCode[i] = randomChar();
        }
        return guessCode;
    }

    private char randomChar() {
        List<Character> available = GameCoordinator.getAvailableCode();
        return available.get(rand.nextInt(available.size()));
    }


    public void setHints(Hints[] hints) {
        this.hints = hints;
        updateKnowledge();
    }

    private void updateKnowledge() {
        if (hints == null || prevCode == null) return;

        for (int i = 0; i < 4; i++) {
            if (hints[i].equals(Hints.RED)) {
                confirmedPositions.set(i, prevCode[i]);
            } else if (hints[i].equals(Hints.WHITE)) {
                mustInclude.add(prevCode[i]);
            } else if (hints[i].equals(Hints.EMPTY)) {
                exclude.add(prevCode[i]);
            }
        }
    }
}
