package hangman;

import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public final class StreamLetters implements Letters {
    private final Iterator<String> letters;

    public StreamLetters(InputStream stream) {
        this.letters = new Scanner(Objects.requireNonNull(stream));
    }

    public char letter() {
        try{
            return letters.next().charAt(0);
        } catch (NoSuchElementException e){
            throw new NoMoreLettersException();
        }
    }
}
