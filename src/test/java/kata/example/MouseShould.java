package kata.example;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class MouseShould {
    @Test
    void do_a_single_click(){
        //when
        SpyListener listener = new SpyListener();
        Mouse mouse = new Mouse();
        mouse.subscribe(listener);

        //act
        mouse.pressLeftButton(0);
        mouse.releaseLeftButton(100);

        //assert
        assertThat(listener.handleMouseEventHasBeenCalledWithClickEvent()).isTrue();
    }

    @Test
    void do_a_double_click(){
        //when
        SpyListener listener = new SpyListener();
        Mouse mouse = new Mouse();
        mouse.subscribe(listener);

        //act
        mouse.pressLeftButton(0);
        mouse.releaseLeftButton(100);
        mouse.pressLeftButton(599);
        mouse.releaseLeftButton(601);

        //assert
        assertThat(listener.handleMouseEventHasBeenCalledWithDoubleClickEvent()).isTrue();
    }
}
