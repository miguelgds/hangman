package hangman;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WordToGuessTest {

    @Test(expected = IllegalArgumentException.class)
    public void given_null_word_should_fail() {
        new WordToGuess(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_empty_word_should_fail() {
        new WordToGuess("");
    }

    @Test
    public void given_word_guessed_should_return_false() {
        // GIVEN
        WordToGuess sut = new WordToGuess("hola");
        // WHEN
        boolean guessed = sut.guessed();
        // THEN
        assertThat(guessed, is(false));
    }

    @Test
    public void given_word_when_guess_all_letters_guessed_should_return_true() {
        // GIVEN
        WordToGuess sut = new WordToGuess("hola");
        // WHEN
        sut.guessLetter('h');
        sut.guessLetter('o');
        sut.guessLetter('l');
        sut.guessLetter('a');
        boolean guessed = sut.guessed();
        // THEN
        assertThat(guessed, is(true));
    }

    @Test
    public void given_word_guessLetter_that_exists_should_return_true() {
        // GIVEN
        WordToGuess sut = new WordToGuess("hola");
        // WHEN
        boolean guessed = sut.guessLetter('h');
        // THEN
        assertThat(guessed, is(true));
        assertThat(sut.toString(), equalTo("h???"));
    }

    @Test
    public void given_word_guessLetter_that_not_exists_should_return_false() {
        // GIVEN
        WordToGuess sut = new WordToGuess("hola");
        // WHEN
        boolean guessed = sut.guessLetter('x');
        // THEN
        assertThat(guessed, is(false));
        assertThat(sut.toString(), equalTo("????"));
    }

}
