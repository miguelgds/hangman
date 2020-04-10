package hangman;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import java.io.ByteArrayInputStream;

public class HangmanTest {
    private final Letters letters;
    private final GameNotifications notifications;

    public HangmanTest() {
        this.letters = Mockito.mock(Letters.class);
        this.notifications = Mockito.mock(GameNotifications.class);
    }

    @Test(expected = NullPointerException.class)
    public void given_null_letters_hangman_should_fail() {
        new Hangman(null, this.notifications);
    }

    @Test(expected = NullPointerException.class)
    public void given_null_notifications_hangman_should_fail() {
        new Hangman(this.letters, null);
    }

    @Test
    public void given_exact_letters_than_word_should_call_to_win() {
        // GIVEN
        Letters letters = new StreamLetters(new ByteArrayInputStream("h\no\nl\na\n".getBytes()));
        Hangman sut = new Hangman(letters, this.notifications);
        // WHEN
        sut.play("hola", 1);
        // THEN
        Mockito.verify(this.notifications, Mockito.times(4)).wordExists();
        Mockito.verify(this.notifications).win();
    }

    @Test
    public void given_one_incorrect_letter_with_one_attempt_should_call_to_lose() {
        // GIVEN
        Letters letters = new StreamLetters(new ByteArrayInputStream("h\no\nl\nu\n".getBytes()));
        Hangman sut = new Hangman(letters, this.notifications);
        // WHEN
        sut.play("hola", 1);
        // THEN
        Mockito.verify(this.notifications, Mockito.times(3)).wordExists();
        Mockito.verify(this.notifications).wordNotExists(Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(this.notifications).lose();
    }

    @Test
    public void given_needed_letters_and_one_incorrect_letter_with_two_attempts_should_call_to_win() {
        // GIVEN
        Letters letters = new StreamLetters(new ByteArrayInputStream("h\nu\no\nl\na\n".getBytes()));
        Hangman sut = new Hangman(letters, this.notifications);
        // WHEN
        sut.play("hola", 2);
        // THEN
        Mockito.verify(this.notifications, Mockito.times(4)).wordExists();
        Mockito.verify(this.notifications).wordNotExists(Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(this.notifications).win();
    }
}
