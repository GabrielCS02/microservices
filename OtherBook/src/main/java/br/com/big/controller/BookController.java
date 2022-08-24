package br.com.big.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.big.model.Book;
import br.com.big.proxy.CambioProxy;
import br.com.big.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired
	private Environment environment;

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;
	
	@Operation(summary = "Find a specific book by your ID")
	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(
			@PathVariable("id") Long id,
			@PathVariable("id") String currency
			) {
	
		var book = repository.getById(id);
		if(book == null) throw new RuntimeException("Book not found");
		
		

		
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		
		
		var port = environment.getProperty("local.server.port");
		book.setEnvironment("Book port: " + port + "Cambio Port: " + cambio.getEnvironment());
		book.setPrice(cambio.getConvertedValue());
		return book;
	}
}
