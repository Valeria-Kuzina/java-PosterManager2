package ru.netology.manager;

import ru.netology.domain.FilmItem;
import ru.netology.repository.CartRepository;

public class CartManager {
    private CartRepository repository;

    public CartManager(CartRepository repository) {
        this.repository = repository;
    }

    public void add(FilmItem item) {
        repository.save(item);
    }

    public FilmItem[] getAll() {
        FilmItem[] items = repository.findAll();
        FilmItem[] result = new FilmItem[items.length];
        for (int i = 0; i < result.length; i++) {
            int index = items.length - i - 1;
            result[i] = items[index];
        }
        return result;
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public void removeAll() {
        repository.removeAll();
    }

    public void findById(int id) {
        repository.findById(id);
    }

}