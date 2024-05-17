package br.com.alura.alugames.modelo

import java.util.*
import kotlin.random.Random

// Construtor Primario (Campos obrigaórios)
data class Gamer(var nome: String, var email: String) {

    // Não obrigatorio, iniciado como null
    var dataNascimento: String? = null

    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()) {
                criarIdInterno()
            }
        }

    var idInterno: String? = null
        get             // Ativa o get padrão
        private set     // Ativa o set privado

    val jogosBuscados = mutableListOf<Jogo?>()

    // Criando outro construtor - Secundario
    constructor(
        nome: String,
        email: String,
        dataNascimento: String,
        usuario: String
    ) : this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }

    //Executado antes de construir o objeto
    init {
        this.email = validarEmail()
        if (nome.isNullOrBlank()) {
            throw IllegalArgumentException("Nome inválido: $nome")
        }
    }

    override fun toString(): String {
        return "\nGamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        // Numero de 4 digitos
        val tag = String.format("%04d", numero)

        idInterno = "$usuario#$tag"
    }

    fun validarEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Email inválido: $email")
        }
    }

    // Metodo Statico, não precisa instanciar
    companion object {
        fun criarGamer(leitura: Scanner): Gamer {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()
                println("Cadastro completo cadastrado com sucesso!")
                return Gamer(nome, email, nascimento, usuario)
            } else {
                println("Cadastro simples cadastrado com sucesso!")
                return Gamer(nome, email)
            }
        }

        fun criarGamerMock(): Gamer {
            println("Gamer Mock criado.")
            return Gamer("Gus", "gus@terra.com")
        }
    }


}