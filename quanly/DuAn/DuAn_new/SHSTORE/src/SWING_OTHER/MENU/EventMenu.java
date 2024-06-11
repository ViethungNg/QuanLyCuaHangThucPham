package SWING_OTHER.MENU;

import java.awt.Component;

public interface EventMenu {

    public void mainMenuSelected(MainForm mainForm, int index, MenuItem menuItem);
//da
    public void subMenuSelected(MainForm mainForm, int index, int subMenuIndex, Component menuItem);
}
