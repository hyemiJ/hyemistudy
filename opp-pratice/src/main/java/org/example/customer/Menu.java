package org.example.customer;

import java.util.List;

public class Menu {
    private final List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public MenuItem choose(String name) {

        return this.menuItems.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);

    }
}
