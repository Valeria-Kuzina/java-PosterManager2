package ru.netology.domain;

import lombok.* ;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilmItem {
    private int id;
    private int FIlmId;
    private String FilmName;
    private int count;

}
