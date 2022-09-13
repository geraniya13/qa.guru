package demoqa.page.components;

public enum Countries {
    COUNTRY1 ("Haryana"),
    COUNTRY2 ("NCR"),
    COUNTRY3 ("Uttar Pradesh"),
    COUNTRY4 ("Rajasthan");

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
