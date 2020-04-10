package hangman;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public final class WordToGuess {
    private final String word;
    private final boolean[] guessedLetters;

    public WordToGuess(String word){
        if(Objects.isNull(word) || word.isBlank()){
            throw new IllegalArgumentException("Not blank word is required");
        }
        this.word = word;
        this.guessedLetters = new boolean[word.length()];
    }

    public boolean guessed() {
        boolean guessed = true;
        for (boolean current : this.guessedLetters) {
            guessed = guessed && current;
        }
        return guessed;
    }

    public boolean guessLetter(char letter) {
        int index = 0;
        boolean hit = false;
        for (char currentLetter : this.word.toCharArray()) {
            if (currentLetter == letter
                    && !this.guessedLetters[index]) {
                this.guessedLetters[index] = true;
                hit = true;
            }
            index++;
        }
        return hit;
    }

    public String toString() {
        int index = 0;
        StringBuilder secretWord = new StringBuilder();
        for (boolean indexGuessed : this.guessedLetters) {
            if (indexGuessed) {
                secretWord.append(this.word.charAt(index));
            } else {
                secretWord.append("?");
            }
            index++;
        }
        return secretWord.toString();
    }
}
