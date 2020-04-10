package hangman;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.ByteArrayInputStream;

public class StreamLettersTest {

    @Test(expected = NullPointerException.class)
    public void given_null_stream_should_fail() {
        new StreamLetters(null);
    }

    @Test
    public void given_stream_hello_letter_should_return_characters_in_order() {
        // GIVEN
        ByteArrayInputStream bis = new ByteArrayInputStream("h\ne\nl\nl\no".getBytes());
        StreamLetters sut = new StreamLetters(bis);
        // WHEN
        char h = sut.letter();
        char e = sut.letter();
        char l = sut.letter();
        char l2 = sut.letter();
        char o = sut.letter();
        // THEN
        assertThat(h, equalTo('h'));
        assertThat(e, equalTo('e'));
        assertThat(l, equalTo('l'));
        assertThat(l2, equalTo('l'));
        assertThat(o, equalTo('o'));
    }

    @Test(expected = NoMoreLettersException.class)
    public void given_empty_stream_letter_should_fail() {
        // GIVEN
        ByteArrayInputStream bis = new ByteArrayInputStream("".getBytes());
        StreamLetters sut = new StreamLetters(bis);
        // WHEN
        sut.letter();
    }
}
