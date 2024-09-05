package com.games.games_api;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Long id;
    private String name;
    private String company;
    private String developers;
    private String creators;
    private String firstRelease;
    private String latestRelease;
    private String genre;
}
