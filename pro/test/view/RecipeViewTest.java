package view;

import app.Main;
import app.SearchUseCaseFactory;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import service.search.interface_adapter.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

public class RecipeViewTest {

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

        return (JButton) buttons.getComponent(0);
    }
    @org.junit.Test
    public void testSignupButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Search"));
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

        SearchResultView sv = (SearchResultView) jp2.getComponent(3);

        JPanel buttons = (JPanel) sv.getComponent(1);

        return (JButton) buttons.getComponent(0);
    }
    @org.junit.Test
    public void testSearchResultMatch() throws IOException {
        Main.main(null);
        JButton button = getButton();
        button.doClick();
        JButton button2 = getButton2();
        assert(button2.getText().equals("Corba"));
    }
    public JButton getButton3() {
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

        RecipeView sv = (RecipeView) jp2.getComponent(6);

        //JPanel buttons = (JPanel) sv.getComponent(17);

        return (JButton) sv.getComponent(17);
    }
    @org.junit.Test
    public void testRecipeButtonMatch() throws IOException {
        Main.main(null);
        JButton button = getButton();
        button.doClick();
        JButton button2 = getButton2();
        button2.doClick();
        JButton button3 = getButton3();
        System.out.println(button3.getText());
        assert(button3.getText().equals("like"));
    }
}
