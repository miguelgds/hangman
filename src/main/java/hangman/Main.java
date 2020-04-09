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
    private final String wordToGuess;

    public Main(final Letters letters, final OutputStream out, final int maxAttempts, String wordToGuess) {
        this.letters = letters;
        this.output = out;
        this.maxAttempts = maxAttempts;
        this.wordToGuess = Objects.requireNonNull(wordToGuess);
    }

    public static void main(final String... args) {
        new Main(new StreamLetters(System.in), System.out, 5, args[0]).exec();
    }

    public void exec() {
        boolean[] visible = new boolean[wordToGuess.length()];
        int mistakes = 0;
        try (final PrintStream out = new PrintStream(this.output)) {
            boolean done = true;
            while (mistakes < this.maxAttempts) {
                done = true;
                for (int i = 0; i < wordToGuess.length(); ++i) {
                    if (!visible[i]) {
                        done = false;
                    }
                }
                if (done) {
                    break;
                }
                out.print("Guess a letter: ");
                String letter = this.letters.letter();
                boolean hit = false;
                for (int i = 0; i < wordToGuess.length(); ++i) {
                    if (String.valueOf(wordToGuess.charAt(i)).equals(letter)
                            && !visible[i]) {
                        visible[i] = true;
                        hit = true;
                    }
                }
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
                for (int i = 0; i < wordToGuess.length(); ++i) {
                    if (visible[i]) {
                        out.print(wordToGuess.charAt(i));
                    } else {
                        out.print("?");
                    }
                }
                out.append("\n\n");
            }
            if (done) {
                out.print("You won!\n");
            } else {
                out.print("You lost.\n");
            }
        }
    }

}
