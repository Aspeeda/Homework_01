package data.menu;

public enum HeaderMenuData {
    EDUCATION("Обучение"),
    INFO("Информация");

    private String name;

    HeaderMenuData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}