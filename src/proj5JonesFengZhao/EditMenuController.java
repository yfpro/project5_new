/*
File: EditMenuController.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */

package proj5JonesFengZhao;

import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

/**
 * This class handles the Edit menu, as a helper to the main Controller.
 * This includes the individual handler methods for the MenuItems as
 * well as logic for determining deactivating the buttons when
 * appropriate.
 *
 * @author Yi Feng
 * @author Iris Lian
 * @author Chris Marcello
 * @author Evan Savillo
 * @author Matt Jones
 */
public class EditMenuController {
    private TabPane tabPane;

    private MenuItem undoMenuItem;
    private MenuItem redoMenuItem;
    private MenuItem cutMenuItem;
    private MenuItem copyMenuItem;
    private MenuItem pasteMenuItem;
    private
    MenuItem selectAllMenuItem;

    /**
     * Handles the Undo button action.
     * Undo the actions in the text area.
     */
    void handleUndoMenuItemAction() {
        this.getCurrentCodeArea().undo();
    }

    /**
     * Handles the Redo button action.
     * Redo the actions in the text area.
     */
    void handleRedoMenuItemAction() {
        this.getCurrentCodeArea().redo();
    }

    /**
     * Handles the Cut button action.
     * Cuts the selected text.
     */
    void handleCutMenuItemAction() {
        this.getCurrentCodeArea().cut();
    }

    /**
     * Handles the Copy button action.
     * Copies the selected text.
     */
    void handleCopyMenuItemAction() {
        this.getCurrentCodeArea().copy();
    }

    /**
     * Handles the Paste button action.
     * Pastes the copied/cut text.
     */
    void handlePasteMenuItemAction() {
        this.getCurrentCodeArea().paste();
    }

    /**
     * Handles the SelectAll button action.
     * Selects all texts in the text area.
     */
    void handleSelectAllMenuItemAction() {
        this.getCurrentCodeArea().selectAll();
    }

    /**
     * Simple helper method which returns the currently viewed tab
     *
     * @return currently viewed tab
     */
    private Tab getCurrentTab() {
        return this.tabPane.getSelectionModel().getSelectedItem();
    }

    /**
     * Simple helper method which returns the code area  within the currently viewed tab
     *
     * @return current viewed code area
     */
    private CodeArea getCurrentCodeArea() {
        Tab selectedTab = this.getCurrentTab();
        VirtualizedScrollPane vsp = (VirtualizedScrollPane) selectedTab.getContent();
        return (CodeArea) vsp.getContent();
    }

    /**
     * Simple helper method
     *
     * @return true if there aren't currently any tabs open, else false
     */
    private boolean isTabless() {
        return this.tabPane.getTabs().isEmpty();
    }

    /**
     * Simple helper method that gets the FXML objects from the
     * main controller for use by other methods in the class.
     */
    void receiveFXMLElements(Object[] list) {
        tabPane = (TabPane) list[0];
        undoMenuItem = (MenuItem) list[4];
        redoMenuItem = (MenuItem) list[5];
        cutMenuItem = (MenuItem) list[6];
        copyMenuItem = (MenuItem) list[7];
        pasteMenuItem = (MenuItem) list[8];
        selectAllMenuItem = (MenuItem) list[9];
    }
}
