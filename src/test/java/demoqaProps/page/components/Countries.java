package demoqaProps.page.components;

public enum Countries {
    HARYANA ("Haryana"),
    NCR ("NCR"),
    UTTAR_PRADESH ("Uttar Pradesh"),
    RAJASTHAN ("Rajasthan");

    private String title;

    Countries(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
