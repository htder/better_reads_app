package io.javabrains.betterreads.user;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Slice;

import java.awt.print.Pageable;

public interface BooksByUserRepository extends CassandraRepository<BooksByUser, String> {

    Slice<BooksByUser> findAllBy(String id, Pageable pageable);

}
