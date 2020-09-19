package ru.netology.repository;

import ru.netology.domain.FilmItem;

public class CartRepository {
    private FilmItem[] items = new FilmItem[0];

    public FilmItem[] findAll() {
        return items;
    }

    public void save(FilmItem item) {
        int length = items.length + 1;
        FilmItem[] tmp = new FilmItem[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public FilmItem findById(int id) {
        FilmItem[] tmp = new FilmItem[1];
        return items[id];
    }

    public void removeById(int id) {
        int length = items.length - 1;
        FilmItem[] tmp = new FilmItem[length];
        int index = 0;
        for (FilmItem item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }

    public void removeAll() {
        FilmItem[] tmp = new FilmItem[0];
        items = tmp;
    }
}
