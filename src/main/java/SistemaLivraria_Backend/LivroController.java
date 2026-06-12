package SistemaLivraria_Backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "http://localhost:5173")
public class LivroController {

    @GetMapping
    public List<Livro> listarLivros() {

        List<Livro> livros = new ArrayList<>();

        livros.add(new Livro(
                "Dom Casmurro",
                "Machado de Assis",
                "Romance",
                49.90));

        livros.add(new Livro(
                "1984",
                "George Orwell",
                "Ficção",
                59.90));

        livros.add(new Livro(
                "O Pequeno Príncipe",
                "Antoine de Saint-Exupéry",
                "Infantil",
                29.90));

        livros.add(new Livro(
                "Harry Potter e a Pedra Filosofal",
                "J.K. Rowling",
                "Fantasia",
                69.90));

        livros.add(new Livro(
                "O Hobbit",
                "J.R.R. Tolkien",
                "Fantasia",
                54.90));

        livros.add(new Livro(
                "A Revolução dos Bichos",
                "George Orwell",
                "Ficção",
                39.90));

        livros.add(new Livro(
                "Memórias Póstumas de Brás Cubas",
                "Machado de Assis",
                "Romance",
                44.90));

        livros.add(new Livro(
                "Capitães da Areia",
                "Jorge Amado",
                "Romance",
                42.90));

        livros.add(new Livro(
                "O Cortiço",
                "Aluísio Azevedo",
                "Romance",
                35.90));

        livros.add(new Livro(
                "Senhora",
                "José de Alencar",
                "Romance",
                32.90));

        livros.add(new Livro(
                "Percy Jackson e o Ladrão de Raios",
                "Rick Riordan",
                "Fantasia",
                57.90));

        return livros;
    }
}