package io.javabrains.betterreads.userbooks;

import org.springframework.data.cassandra.core.mapping.*;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import java.time.LocalDate;

@Table(value = "book_by_user_and_bookid")
public class UserBooks {

    @PrimaryKey
    private UserBooksPrimaryKey key;

    @Column("started_date")
    @CassandraType(type = Name.TEXT)
    private String startedDate;

    @Column("completed_date")
    @CassandraType(type = Name.TEXT)
    private String completedDate;

    @Column("reading_status")
    @CassandraType(type = Name.TEXT)
    private String readingStatus;

    @Column("rating")
    @CassandraType(type = Name.INT)
    private int rating;

    public UserBooksPrimaryKey getKey() {
        return key;
    }

    public void setKey(UserBooksPrimaryKey key) {
        this.key = key;
    }

    public String getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(String startedDate) {
        this.startedDate = startedDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public String getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(String readingStatus) {
        this.readingStatus = readingStatus;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
