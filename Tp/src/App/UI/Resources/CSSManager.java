package App.UI.Resources;

import javafx.scene.Parent;

public class CSSManager {
    private CSSManager() {}

    public static void setCSS(Parent parent, String name) {
        parent.getStylesheets().add(resources.getResourceFilename("CSS/"+name));
    }
}
