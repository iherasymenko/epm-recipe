package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.user.User;
import com.epm.recipe.persistence.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Objects;
import java.util.Optional;

public class JdbcUserRepository implements UserRepository {

    private final NamedParameterJdbcOperations jdbcOperations;
    private final RowMapper<User> rowMapper = (rs, __) -> new User(rs.getLong("user_ID"), rs.getString("user_email"), rs.getString("user_password"));

    public JdbcUserRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = Objects.requireNonNull(jdbcOperations);
    }

    @Override
    public Optional<User> getByEmail(String email){
        try {
            SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("email", email);
            return Optional.ofNullable(jdbcOperations.queryForObject("SELECT * FROM users WHERE user_email = :email", parameterSource, rowMapper));
        } catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public User create(String email, String password){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("email", email)
                .addValue("password", password);
        jdbcOperations.update("INSERT INTO users(user_email, user_password) VALUES (:email, :password)", parameterSource, keyHolder);
        return new User((Long) keyHolder.getKey(), email, password);
    }

}
