package hangman;

import java.io.Closeable;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

public final class StreamGameNotifications implements GameNotifications, Closeable {

    private final PrintStream output;

    public StreamGameNotifications(OutputStream output){
        this.output = new PrintStream(Objects.requireNonNull(output));
    }

    @Override
    public void enterLetter() {
        output.print("Guess a letter: ");
    }

    @Override
    public void wordExists() {
        output.print("Hit!\n");
    }

    @Override
    public void wordNotExists(int mistakes, int maxAttempts) {
        output.printf(
                "Missed, mistake #%d out of %d\n",
                mistakes, maxAttempts
        );
    }

    @Override
    public void wordToGuessStatus(String word) {
        output.append("The word: ");
        output.append(word);
        output.append("\n\n");
    }

    @Override
    public void win() {
        output.print("You won!\n");
    }

    @Override
    public void lose() {
        output.print("You lost.\n");
    }

    @Override
    public void close() {
        output.close();
    }
}
