package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.FilmItem;
import ru.netology.repository.CartRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartManagerNonEmpty {
    @Mock
    private CartRepository repository;
    @InjectMocks
    private CartManager manager;
    private FilmItem one = new FilmItem(1, 1, "One Flew Over the Cuckoo's Nest (1975)", 1);
    private FilmItem two = new FilmItem(2, 2, "Two Women (1960)", 1);
    private FilmItem three = new FilmItem(3, 3, "Three Men and a Baby (1987)", 1);
    private FilmItem four = new FilmItem(4, 4, "Four Rooms (1995)", 1);
    private FilmItem five = new FilmItem(5, 5, "The Fifth Element (1997)", 1);
    private FilmItem six = new FilmItem(6, 6, "6 Souls (2010)", 1);
    private FilmItem seven = new FilmItem(7, 7, "Seven Years in Tibet (1997)", 1);
    private FilmItem eight = new FilmItem(8, 8, "8 Mile (2002)", 1);
    private FilmItem nine = new FilmItem(9, 9, "Nine Months (1995)", 1);
    private FilmItem ten = new FilmItem(10, 10, "10 Items or Less (2006)", 1);
    private FilmItem eleven = new FilmItem(11, 11, "The 11th Hour (2007)", 1);
    private FilmItem twelve = new FilmItem(12, 12, "12 Years a Slave (2013)", 1);

    @BeforeEach
    public void setUp() {
        manager.add(one);
        manager.add(two);
        manager.add(three);
        manager.add(four);
        manager.add(five);
        manager.add(six);
        manager.add(seven);
        manager.add(eight);
        manager.add(nine);
        manager.add(ten);
        manager.add(eleven);
        manager.add(twelve);
    }

/*    @Test
    void add() {
        FilmItem thirteen = new FilmItem(13, 13, "13", 1);
        manager.add(thirteen);

        FilmItem[] actual = manager.getNumberOfFilms(1);
        FilmItem[] expected = new FilmItem[]{thirteen};

        assertArrayEquals(expected, actual);

    }*/

    @Test
    public void shouldRemoveIfExists() {
        int idToRemove = 1;
        // настройка заглушки
        FilmItem[] returned = new FilmItem[]{two, three};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeById(idToRemove);

        manager.removeById(idToRemove);
        FilmItem[] expected = new FilmItem[]{three, two};
        FilmItem[] actual = manager.getAll();
        assertArrayEquals(expected, actual);

        // удостоверяемся, что заглушка была вызвана с нужным значением
        // но это уже проверка "внутренней" реализации
        verify(repository).removeById(idToRemove);
    }

    @Test
    public void shouldNotRemoveIfNotExists() {
        int idToRemove = 4;
        FilmItem[] returned = new FilmItem[]{one, two, three};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeById(idToRemove);

        manager.removeById(idToRemove);
        FilmItem[] expected = new FilmItem[]{three, two, one};
        FilmItem[] actual = manager.getAll();

        assertArrayEquals(expected, actual);
        // удостоверяемся, что заглушка была вызвана с нужным значением
        // но это уже проверка "внутренней" реализации
        verify(repository).removeById(idToRemove);
    }

    @Test
    public void shouldAddMovie() {
        FilmItem thirteen = new FilmItem(13, 13, "13", 1);
        FilmItem[] returned = new FilmItem[]{one, two, three, four, five, six,
                seven, eight, nine, ten, eleven, twelve, thirteen};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).save(thirteen);

        manager.add(thirteen);
        FilmItem[] expected = new FilmItem[]{thirteen, twelve, eleven, ten, nine,
                eight, seven, six, five, four, three, two, one};
        FilmItem[] actual = manager.getAll();

        assertArrayEquals(expected, actual);
        // удостоверяемся, что заглушка была вызвана с нужным значением
        // но это уже проверка "внутренней" реализации
        verify(repository).save(thirteen);
    }

    @Test
    public void shouldDeleteAll() {
        FilmItem[] returned = new FilmItem[0];
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeAll();

        manager.removeAll();
        FilmItem[] expected = new FilmItem[0];
        FilmItem[] actual = manager.getAll();

        assertArrayEquals(expected, actual);
        // удостоверяемся, что заглушка была вызвана с нужным значением
        // но это уже проверка "внутренней" реализации
        verify(repository).removeAll();
    }

    @Test
    public void shouldFindByID() {
        int id = 4;
        FilmItem[] returned = new FilmItem[]{five};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).findById(id);

        manager.findById(id);
        FilmItem[] expected = new FilmItem[]{five};
        FilmItem[] actual = manager.getAll();

        assertArrayEquals(expected, actual);
        // удостоверяемся, что заглушка была вызвана с нужным значением
        // но это уже проверка "внутренней" реализации
        verify(repository).findById(id);
    }
}