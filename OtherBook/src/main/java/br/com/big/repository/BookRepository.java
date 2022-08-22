package br.com.big.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.big.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
