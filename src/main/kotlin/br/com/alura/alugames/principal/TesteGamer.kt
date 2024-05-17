import br.com.alura.alugames.modelo.Gamer

fun main() {

    val gamer1 = Gamer("Gustavo", "gustavocarvalho.ti@gmail.com")
    // Scope functions:
    // ALtera e depois imprime
    gamer1.let {
        it.dataNascimento = "28/10/1988"
        it.usuario = "gustaveraxd"
    }.also {
        println(gamer1)
    }

    val gamer2 = Gamer("Dudaaaa", "duda@gmail.com", "12/01/2015", "dudakids")
    println(gamer2)
    val nome = gamer2.nome
    val idade = gamer2.dataNascimento?.transformarEmIdade()
    println("O gamer $nome tem $idade ano(s).")


}