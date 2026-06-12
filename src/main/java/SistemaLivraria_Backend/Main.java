package SistemaLivraria_Backend;

public class Main {

    public static void main(String[] args) {

        LivroDAO dao = new LivroDAO();

        // Limpar duplicatas ao iniciar
        dao.limparDuplicatas();

        dao.cadastrarLivro(
                "Dom Casmurro",
                "Machado de Assis",
                "Romance",
                49.90);

        dao.cadastrarLivro(
                "1984",
                "George Orwell",
                "Ficção",
                59.90);

        dao.cadastrarLivro(
                "O Pequeno Príncipe",
                "Antoine de Saint-Exupéry",
                "Infantil",
                29.90);

        dao.cadastrarLivro(
                "Harry Potter e a Pedra Filosofal",
                "J.K. Rowling",
                "Fantasia",
                69.90);

        dao.cadastrarLivro(
                "O Hobbit",
                "J.R.R. Tolkien",
                "Fantasia",
                54.90);

        dao.cadastrarLivro(
                "A Revolução dos Bichos",
                "George Orwell",
                "Ficção",
                39.90);

        dao.cadastrarLivro(
                "Memórias Póstumas de Brás Cubas",
                "Machado de Assis",
                "Romance",
                44.90);

        dao.cadastrarLivro(
                "Capitães da Areia",
                "Jorge Amado",
                "Romance",
                42.90);

        dao.cadastrarLivro(
                "O Cortiço",
                "Aluísio Azevedo",
                "Romance",
                35.90);

        dao.cadastrarLivro(
                "Senhora",
                "José de Alencar",
                "Romance",
                32.90);

        dao.cadastrarLivro(
                "Percy Jackson e o Ladrão de Raios",
                "Rick Riordan",
                "Fantasia",
                57.90);

        System.out.println("Livros cadastrados com sucesso!");
        dao.listarLivros();
    }
}