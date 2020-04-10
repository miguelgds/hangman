package hangman;

import java.util.Objects;

public class Hangman {
    private final Letters letters;
    private final GameNotifications notifications;

    public Hangman(final Letters letters, final GameNotifications notifications) {
        this.letters = Objects.requireNonNull(letters);
        this.notifications = Objects.requireNonNull(notifications);
    }

    public void play(String word, int maxAttempts) {
        WordToGuess wordToGuess = new WordToGuess(word);
        if(maxAttempts <= 0) {
            throw new IllegalArgumentException("maxAttempts must be greather than 0");
        }

        int mistakes = 0;
        while (mistakes < maxAttempts && !wordToGuess.guessed()) {
            notifications.enterLetter();
            if (wordToGuess.guessLetter(this.letters.letter())) {
                notifications.wordExists();
            } else {
                notifications.wordNotExists(++mistakes, maxAttempts);
            }
            notifications.wordToGuessStatus(wordToGuess.toString());
        }
        if (wordToGuess.guessed()) {
            notifications.win();
        } else {
            notifications.lose();
        }
    }
}
