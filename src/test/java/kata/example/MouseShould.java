package kata.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;




// TODO:
// SINGLE CLICK
//     click
//     no click,
//     several clicks after a long time,
//     multiples clicks without release?
// DOUBLE CLICK
//     clicks, click + move + click != double click
// TRIPLE CLICK
//     click + move + click != double click
// DRAG
//     move without press
// DROP
//     no move


public class MouseShould {
    @Test
    void notify_click_with_press_and_release_sequence_with_left_button() {
        SpyListener listener = new SpyListener();
        Mouse mouse = new Mouse();
        mouse.subscribe(listener);

        mouse.pressLeftButton(0);
        mouse.releaseLeftButton(100);

        assertThat(listener.handleMouseEventHasBeenCalledWithClick()).isTrue();
    }
}