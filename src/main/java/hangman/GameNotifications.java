package hangman;

public interface GameNotifications {
    void enterLetter();
    void wordExists();
    void wordNotExists(int mistakes, int maxAttempts);
    void wordToGuessStatus(String word);
    void win();
    void lose();
}
