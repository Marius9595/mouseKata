package kata.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class MouseShould {

    Mouse mouse;
    SpyListener listener;
    @BeforeEach
    void setUp(){
        this.listener = new SpyListener();
        this.mouse = new Mouse();
        this.mouse.subscribe(this.listener);
    }
    @Test
    void do_a_single_click(){
        //act
        mouse.pressLeftButton(0);
        mouse.releaseLeftButton(100);

        //assert
        assertThat(listener.handleMouseEventHasBeenCalledWithClickEvent()).isTrue();
    }

    @Test
    void do_a_double_click(){
        //act
        mouse.pressLeftButton(0);
        mouse.releaseLeftButton(100);
        mouse.pressLeftButton(599);
        mouse.releaseLeftButton(601);

        //assert
        assertThat(listener.handleMouseEventHasBeenCalledWithDoubleClickEvent()).isTrue();
    }

    @Test
    void do_a_drag(){
        //act
        mouse.pressLeftButton(0);
        mouse.move(
                new MousePointerCoordinates(0,0),
                new MousePointerCoordinates(0,1),
                100
        );
        //assert
        assertThat(listener.handleMouseEventHasBeenCalledWithDragEvent()).isTrue();
    }

    @Test
    void do_a_drag_even_the_final_position_is_the_same(){
        //act
        mouse.pressLeftButton(0);
        mouse.move(
                new MousePointerCoordinates(0,0),
                new MousePointerCoordinates(0,0),
                100
        );
        //assert
        assertThat(listener.handleMouseEventHasBeenCalledWithClickEvent()).isTrue();
    }
}
