package ViewScoresTest;

import app.Main;
import data_access.file_accessors.UserScoresDataAccessObject;
import entity.DifficultyLevel;
import entity.user.User;
import interface_adapter.view_scores.ViewScoresController;
import org.junit.Assert;
import use_case.view_scores.ViewScoresInputBoundary;
import use_case.view_scores.ViewScoresInteractor;
import view.HomePageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ViewScoresTests {
    static String message = "";
    static boolean popUpDiscovered = false;

    /**
     * adds ten consecutive scores to the UserScores CSV file.
     */
    public void addTenDifferentScores(UserScoresDataAccessObject userData, User user) {
        userData.saveScore(10.0f, user);
        userData.saveScore(20.0f, user);
        userData.saveScore(30.0f, user);
        userData.saveScore(40.0f, user);
        userData.saveScore(50.0f, user);
        userData.saveScore(60.0f, user);
        userData.saveScore(70.0f, user);
        userData.saveScore(10.0f, user);
        userData.saveScore(20.0f, user);
        userData.saveScore(30.0f, user);
    }

    public void addOneMoreScore(UserScoresDataAccessObject userData, User user) {
        userData.saveScore(15.0f, user);
    }

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
        HomePageView sv = (HomePageView) jp2.getComponent(0);
        JPanel buttons = (JPanel) sv.getComponent(4);
        return (JButton) buttons.getComponent(2); // this should be the clear button
    }

    /**
     * Test that the earliest score is replaced when an eleventh score is added
     * @throws IOException
     */
    @org.junit.Test
    public void addEleventhScore() throws IOException {
        UserScoresDataAccessObject userData = new UserScoresDataAccessObject("./users.csv");
        User user = new User(1, "Billy", new float[DifficultyLevel.values().length][]);
        ArrayList<Float> expectedScores = new ArrayList<>(
                Arrays.asList(20.0f, 30.0f, 40.0f, 50.0f, 60.0f, 70.f, 10.0f, 20.0f, 30.0f, 15.0f));
        addTenDifferentScores(userData, user);
        addOneMoreScore(userData, user);
        Assert.assertEquals(expectedScores, userData.getLastTenScores());
    }

}
