package view;

import app.Main;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

public class SignupViewTest {
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

        SignupView sv = (SignupView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(4);

        return (JButton) buttons.getComponent(0); // this should be the Cancel button
    }
    @org.junit.Test
    public void testSignupButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Sign up"));
    }

}
