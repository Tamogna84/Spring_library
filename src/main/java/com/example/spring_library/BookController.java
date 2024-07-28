package com.example.spring_library;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class BookController
{
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String getAllBooks(Model model)
    {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "create-book";
    }
    @PostMapping
    public String createBook(Book book) {
        System.out.println(book);
        bookRepository.save(book);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model)
    {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        model.addAttribute("book", book);
        return "edit-book";
    }
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, Book updatedBook)
    {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        bookRepository.save(book);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        bookRepository.delete(book);
        return "redirect:/";
    }

    /////добавил show и info
    @GetMapping("/show/{id}")
    public String showInfoForm (@PathVariable Long id, Model model)
    {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        model.addAttribute("book", book);
        return "show-book";
    }
    @PostMapping("/info/{id}")
    public String infoBook(@PathVariable Long id, Book infoBook)
    {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        book.setTitle(infoBook.getTitle());
        book.setAuthor(infoBook.getAuthor());
        return "redirect:/";
    }

}
