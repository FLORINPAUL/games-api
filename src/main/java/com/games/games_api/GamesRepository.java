package com.games.games_api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GamesRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GamesRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void init() {
        // Optionally, initialize the table if it does not exist
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Games (" +
                "id SERIAL PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "company TEXT NOT NULL," +
                "developers TEXT," +
                "creators TEXT," +
                "firstRelease TEXT," +
                "latestRelease TEXT," +
                "genre TEXT)");
    }

    private RowMapper<Game> rowMapper = new RowMapper<Game>() {
        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Game(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("company"),
                    rs.getString("developers"),
                    rs.getString("creators"),
                    rs.getString("firstRelease"),
                    rs.getString("latestRelease"),
                    rs.getString("genre")
            );
        }
    };


    public int Add(Game game) {
        return jdbcTemplate.update(
                "INSERT INTO Games (name, company, developers, creators, firstRelease, latestRelease, genre) VALUES (?, ?, ?, ?, ?, ?, ?)",
                game.getName(), game.getCompany(), game.getDevelopers(), game.getCreators(), game.getFirstRelease(), game.getLatestRelease(), game.getGenre());
    }

    public Game GetById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM Games WHERE id = ?",
                new Object[]{id},
                rowMapper);
    }

    public List<Game> GetAll() {
        return jdbcTemplate.query("SELECT * FROM Games", rowMapper);
    }

    public int Update(Game game) {
        return jdbcTemplate.update(
                "UPDATE Games SET name = ?, company = ?, developers = ?, creators = ?, firstRelease = ?, latestRelease = ?, genre = ? WHERE id = ?",
                game.getName(), game.getCompany(), game.getDevelopers(), game.getCreators(), game.getFirstRelease(), game.getLatestRelease(), game.getGenre(), game.getId());
    }

    public int DeleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM Games WHERE id = ?", id);
    }
}
