/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 */
package hangman;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class Main {

    private final Letters letters;
    private final OutputStream output;
    private final int maxAttempts;
    private final WordToGuess wordToGuess;

    public Main(final Letters letters, final OutputStream out, final int maxAttempts, WordToGuess wordToGuess) {
        this.letters = letters;
        this.output = out;
        this.maxAttempts = maxAttempts;
        this.wordToGuess = Objects.requireNonNull(wordToGuess);
    }

    public static void main(final String... args) {
        new Main(new StreamLetters(System.in), System.out, 5, new WordToGuess(args[0])).exec();
    }

    public void exec() {
        int mistakes = 0;
        try (final PrintStream out = new PrintStream(this.output)) {
            while (mistakes < this.maxAttempts
                    && !wordToGuess.guessed()) {
                out.print("Guess a letter: ");
                boolean hit = wordToGuess.guessLetter(this.letters.letter());
                if (hit) {
                    out.print("Hit!\n");
                } else {
                    out.printf(
                        "Missed, mistake #%d out of %d\n",
                        mistakes + 1, this.maxAttempts
                    );
                    ++mistakes;
                }
                out.append("The word: ");
                out.append(wordToGuess.toString());
                out.append("\n\n");
            }
            if (wordToGuess.guessed()) {
                out.print("You won!\n");
            } else {
                out.print("You lost.\n");
            }
        }
    }

}
