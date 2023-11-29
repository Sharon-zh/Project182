package data_access;

import entity.CommonUserFactory;
import entity.UserFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FileUserDataAccessObjectTest {

    @Before
    public void setUp(){
        UserFactory uf = new CommonUserFactory();
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("/Users/ltz/IdeaProjects/Project182/user.csv", uf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fudao.save(uf.create("user1", "password1", LocalDateTime.now()));
        fudao.save(uf.create("user2", "password2", LocalDateTime.now()));
    }

    private ArrayList<String> readLines() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/ltz/IdeaProjects/Project182/user.csv"));
        ArrayList<String> lines = new ArrayList<String>();
        reader.readLine();
        String line = reader.readLine();
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }
        return lines;
    }
    @Test
    public void existsByName() throws IOException {
        UserFactory uf = new CommonUserFactory();
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("/Users/ltz/IdeaProjects/Project182/user.csv", uf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> lines = readLines();
        assertTrue(fudao.existsByName("user1"));
        assertTrue(fudao.existsByName("user2"));
    }

    @Test
    public void save() throws IOException {
        ArrayList<String> lines = readLines();
        assertTrue(lines.get(0).contains("user1, password1"));
        assertTrue(lines.get(1).contains("user2, password2"));
    }

    @Test
    public void loadFavouriteRecipes() {
        UserFactory uf = new CommonUserFactory();
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("/Users/ltz/IdeaProjects/Project182/user.csv", uf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fudao.saveRecipe("user1", "Strawberries Romanoff");
        fudao.saveRecipe("user2", "Blini Pancakes");
        fudao.saveRecipe("user1", "Chicken Marengo");
        fudao.saveRecipe("user2", "Broccoli & Stilton soup");
        System.out.println(fudao.loadFavouriteRecipes("user1"));
        System.out.println(fudao.loadFavouriteRecipes("user2"));
        assertEquals("Strawberries Romanoff", fudao.loadFavouriteRecipes("user1").get(1));
        assertEquals("Chicken Marengo", fudao.loadFavouriteRecipes("user1").get(2));
        assertEquals("Blini Pancakes", fudao.loadFavouriteRecipes("user2").get(1));
        assertEquals("Broccoli & Stilton soup", fudao.loadFavouriteRecipes("user2").get(2));
    }

    @Test
    public void removeRecipe() {
        UserFactory uf = new CommonUserFactory();
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("/Users/ltz/IdeaProjects/Project182/user.csv", uf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fudao.saveRecipe("user1", "Strawberries Romanoff");
        assertEquals("Strawberries Romanoff", fudao.loadFavouriteRecipes("user1").get(1));
        fudao.removeRecipe("user1", "Strawberries Romanoff");
        assertFalse(fudao.loadFavouriteRecipes("user1").contains("Strawberries Romanoff"));
    }


    @Test
    public void saveRecipe() {
        UserFactory uf = new CommonUserFactory();
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("/Users/ltz/IdeaProjects/Project182/user.csv", uf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fudao.saveRecipe("user1", "Strawberries Romanoff");
        assertEquals("Strawberries Romanoff", fudao.loadFavouriteRecipes("user1").get(1));
    }

    @Test
    public void get() {
        UserFactory uf = new CommonUserFactory();
        FileUserDataAccessObject fudao;
        try {
            fudao = new FileUserDataAccessObject("/Users/ltz/IdeaProjects/Project182/user.csv", uf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals("user1", fudao.get("user1").getName());
    }
}