package com.example.civiccomplaints.repository;

import com.example.civiccomplaints.dto.MetricsDto;
import com.example.civiccomplaints.dto.RecentComplaintDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Repository
public class DashboardRepository {

    private final JdbcTemplate jdbc;

    public DashboardRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public MetricsDto fetchMetrics() {
        long total = jdbc.queryForObject("SELECT COUNT(*) FROM complaints", Long.class);

        long resolved = jdbc.queryForObject(
                "SELECT COUNT(*) FROM complaints WHERE LOWER(COALESCE(status,'')) IN ('resolved','closed')",
                Long.class);

        long inProgress = jdbc.queryForObject(
                "SELECT COUNT(*) FROM complaints WHERE LOWER(COALESCE(status,'')) NOT IN ('resolved','closed') OR status IS NULL",
                Long.class);

        // avg resolution days: requires a resolved_at column; fail gracefully if not present
        Double avgDays = null;
        try {
            String sql = "SELECT AVG(EXTRACT(EPOCH FROM (resolved_at - created_at)) / 86400.0) FROM complaints " +
                    "WHERE LOWER(COALESCE(status,'')) IN ('resolved','closed') AND resolved_at IS NOT NULL";
            avgDays = jdbc.queryForObject(sql, Double.class);
        } catch (DataAccessException ex) {
            // Most likely resolved_at doesn't exist; return null for avgDays
            avgDays = null;
        }

        return new MetricsDto(total, resolved, inProgress, avgDays);
    }

    public List<RecentComplaintDto> fetchRecentComplaints(int limit) {
        final String sql =
                "SELECT c.id, cc.name AS category, '' AS location, c.created_at, c.status " +
                        "FROM complaints c " +
                        "LEFT JOIN complaint_categories cc ON cc.id = c.category_id " +
                        "ORDER BY c.created_at DESC " +
                        "LIMIT ?";

        return jdbc.query(sql, new Object[]{limit}, new RowMapper<RecentComplaintDto>() {
            @Override
            public RecentComplaintDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                String id = rs.getString("id");
                String category = rs.getString("category");
                String location = rs.getString("location");
                java.sql.Timestamp ts = rs.getTimestamp("created_at");
                OffsetDateTime odt = ts == null ? null : ts.toInstant().atOffset(ZoneOffset.UTC);
                String status = rs.getString("status");
                return new RecentComplaintDto(id, category, location, odt, status);
            }
        });
    }
}