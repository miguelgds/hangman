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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public final class MainTest {

    @Test
    public void fails_after_many_wrong_attempts() throws Exception {
        // GIVEN
        try(final ByteArrayInputStream input = new ByteArrayInputStream("u\n".getBytes());
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            final StreamGameNotifications notifications = new StreamGameNotifications(output);) {
            final Letters letters = new StreamLetters(input);
            Hangman hangman = new Hangman(letters, notifications);
            // WHEN
            new Main(hangman).exec("mathematics", 1);
            // THEN
            assertThat(output.toString(), containsString("You lost"));
        }
    }

    @Test
    public void succed_after_guess_the_word() throws Exception {
        // GIVEN
        try(final ByteArrayInputStream input = new ByteArrayInputStream("m\na\nt\nh\ne\ni\nc\ns\n".getBytes());
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            final StreamGameNotifications notifications = new StreamGameNotifications(output);) {
            final Letters letters = new StreamLetters(input);
            Hangman hangman = new Hangman(letters, notifications);
            // WHEN
            new Main(hangman).exec("mathematics", 1);
            // THEN
            assertThat(output.toString(), containsString("You won"));
        }
    }

}
