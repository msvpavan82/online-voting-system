package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class VotingRepository {
    private JdbcTemplate jdbcTemplate;
    private List<User> users;

    @Autowired
    public VotingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        String sql = "select * from users";
        users = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));

                return user;
            }
        });
    }

    public boolean getVoteStatus(int userId) {
        String sql = "select status from vote_status where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Boolean.class);
    }

    public int updateUserVoteStatus(int userId) {
        String sql = "update vote_status set status=true where id=?";
        return jdbcTemplate.update(sql, userId);
    }

    public int updateVoteForDestination(int destinationId) {
        String sql = "update votes_info set votes=votes+1 where id=?";
        return jdbcTemplate.update(sql, destinationId);
    }
}
