package kata.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
    SpyListener listener;
    Mouse mouse;

    @BeforeEach
    void setUp() {
        listener = new SpyListener();
        mouse = new Mouse();
        mouse.subscribe(listener);
    }

    @Test
    void notify_click_with_press_and_release_sequence_with_left_button() {
        mouse.pressLeftButton(0);
        mouse.releaseLeftButton(100);

        assertThat(listener.handleMouseEventHasBeenCalledWithClick()).isTrue();
    }
    @Test
    void notify_double_click_when_time_window_between_last_realease_and_following_press_on_left_button_is_short() {
        mouse.pressLeftButton(0);
        mouse.releaseLeftButton(100);
        mouse.pressLeftButton(599);
        mouse.releaseLeftButton(601);

        assertThat(listener.handleMouseEventHasBeenCalledWithDoubleClick()).isTrue();
    }
    @Test
    void not_allow_to_release_left_button_before_it_is_pressed() {
        assertThatThrownBy(() -> {
            mouse.pressLeftButton(100);
            mouse.releaseLeftButton(99);
        }).isInstanceOf(MouseStateException.class).withFailMessage(
                "The left button can not be released before it is pressed"
        );
    }
    @Test
    void notify_triple_click_when_multiple_click_occurred_in_short_time() {
        mouse.pressLeftButton(0);
        mouse.releaseLeftButton(100);
        mouse.pressLeftButton(200);
        mouse.releaseLeftButton(300);
        mouse.pressLeftButton(599);
        mouse.releaseLeftButton(601);

        assertThat(listener.handleMouseEventHasBeenCalledWithTripleClick()).isTrue();
    }

    @Test
    void notify_drag_when_mouse_is_moving_while_left_button_is_pressed() {
        mouse.pressLeftButton(0);
        mouse.move(new MousePointerCoordinates(0, 0), new MousePointerCoordinates(1, 1), 100);

        assertThat(listener.handleMouseEventHasBeenCalledWithDrag()).isTrue();
    }

    @Test
    void not_notify_drag_if_it_changes_its_position_but_left_button_is_not_pressed() {
        mouse.move(new MousePointerCoordinates(0, 0), new MousePointerCoordinates(1, 1), 100);

        assertThat(listener.handleMouseEventHasBeenCalledWithDrag()).isFalse();
    }

    @Test
    void notify_drop_if_left_button_is_release_after_a_drag() {
        mouse.pressLeftButton(0);
        mouse.move(new MousePointerCoordinates(0, 0), new MousePointerCoordinates(1, 1), 100);
        mouse.releaseLeftButton(200);

        assertThat(listener.handleMouseEventHasBeenCalledWithDrop()).isTrue();
    }

    @Test
    void not_enter_in_drag_mode_if_its_coordinates_does_not_change() {
        mouse.pressLeftButton(0);
        mouse.move(new MousePointerCoordinates(0, 0), new MousePointerCoordinates(0, 0), 100);
        mouse.releaseLeftButton(200);

        assertThat(listener.handleMouseEventHasBeenCalledWithDrag()).isFalse();
        assertThat(listener.handleMouseEventHasBeenCalledWithDrop()).isFalse();
    }
}