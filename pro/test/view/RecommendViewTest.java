package view;

import app.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class RecommendViewTest {
    public JButton getButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        SearchView sv = (SearchView) jp2.getComponent(2);

        JPanel buttons = (JPanel) sv.getComponent(2);

        return (JButton) buttons.getComponent(2);
    }
    @org.junit.Test
    public void testRecommendButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Recommend"));
    }
    public JButton getButton2() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        RecommendView sv = (RecommendView) jp2.getComponent(4);

        JPanel buttons = (JPanel) sv.getComponent(1);

        return (JButton) buttons.getComponent(0);
    }
    @org.junit.Test
    public void testRecommendMatch() throws IOException {
        Main.main(null);
        JButton button = getButton();
        button.doClick();
        JButton button2 = getButton2();
        assert(button2.getText().equals("Cancel"));
    }
}
