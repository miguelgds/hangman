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

import java.util.Objects;

public class Main {

    private final Hangman hangman;

    public Main(Hangman hangman) {
        this.hangman = Objects.requireNonNull(hangman);
    }

    public static void main(final String... args) {
        try(final StreamGameNotifications notifications = new StreamGameNotifications(System.out);) {
            new Main(
                new Hangman(new StreamLetters(System.in), notifications)
            ).exec(args[0], Integer.parseInt(args[1]));
        }
    }

    public void exec(String word, int maxAttempts) {
        this.hangman.play(word, maxAttempts);
    }
}
