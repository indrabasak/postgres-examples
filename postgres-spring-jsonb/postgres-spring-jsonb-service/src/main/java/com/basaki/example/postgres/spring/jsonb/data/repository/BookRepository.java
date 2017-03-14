package com.basaki.example.postgres.spring.jsonb.data.repository;

import com.basaki.example.postgres.spring.jsonb.data.entity.BookEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@code BookRepository} exposes all CRUD operations on a data of type
 * {@code Book}.
 * <p/>
 *
 * @author Indra Basak
 * @since 3/8/17
 */
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

}
