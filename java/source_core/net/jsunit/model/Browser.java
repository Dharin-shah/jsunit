package net.jsunit.model;

import net.jsunit.XmlRenderable;
import net.jsunit.utility.StringUtility;
import org.jdom.Element;

import java.util.List;

public class Browser implements XmlRenderable {

    public static final String DEFAULT_SYSTEM_BROWSER = "default";

    private String startCommand;
    private String killCommand;
    private String fullFileName;
    private int id;
    private String displayName;
    private BrowserType type;

    public Browser(String fullFileName, int id) {
        this.fullFileName = fullFileName;
        this.id = id;
        List<String> list = StringUtility.listFromSemiColonDelimitedString(fullFileName);
        this.startCommand = list.size() >= 1 ? list.get(0) : null;
        this.killCommand = list.size() >= 2 ? list.get(1) : null;
        this.type = BrowserType.resolve(startCommand);
        if (list.size() >= 3)
            this.displayName = list.get(2);
        else if (type != BrowserType.UNKNOWN)
            this.displayName = type.getDisplayName();
        else
            this.displayName = startCommand;
    }

    public String getStartCommand() {
        return startCommand;
    }

    public String getKillCommand() {
        return killCommand;
    }

    public int getId() {
        return id;
    }

    public boolean hasId(int anId) {
        return id == anId;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Browser browser = (Browser) o;

        if (id != browser.id) return false;
        return !(startCommand != null ? !startCommand.equals(browser.startCommand) : browser.startCommand != null);

    }

    public int hashCode() {
        int result;
        result = (startCommand != null ? startCommand.hashCode() : 0);
        result = 29 * result + id;
        return result;
    }

    public boolean isDefault() {
        return startCommand.equals(DEFAULT_SYSTEM_BROWSER);
    }

    public String getDisplayName() {
        return displayName;
    }

    public BrowserType getType() {
        return type;
    }

    public String getFullFileName() {
        return fullFileName;
    }

    public boolean conflictsWith(Browser another) {
        return getType().conflictsWith(another.getType());
    }

    public String getLogoPath() {
        return getType().getLogoPath();
    }

    public Element asXml() {
        Element browserElement = new Element("browser");
        Element fullFileNameElement = new Element("fullFileName");
        fullFileNameElement.setText(getFullFileName());
        browserElement.addContent(fullFileNameElement);
        Element displayNameElement = new Element("displayName");
        displayNameElement.setText(getDisplayName());
        browserElement.addContent(displayNameElement);
        Element logoElement = new Element("logoPath");
        logoElement.setText(getLogoPath());
        browserElement.addContent(logoElement);
        return browserElement;
    }
}
