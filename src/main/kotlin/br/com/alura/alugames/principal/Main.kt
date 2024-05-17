package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*

// Equivalente ao PSVM
fun main() {
    secondExample()
}

private fun firstExample() {
    val jogo01 = Jogo(
        "Batman",
        "Capa do Batman",
        "Descricao do jogo do Batman"
    )
    println(jogo01)

    // Tem como definir a ordem de insert dos parametros
    val jogo02 = Jogo(
        capa = "Capa do Superman",
        titulo = "Superman",
        descricao = "Descricao do jogo do Superman"
    )
    println(jogo02)
}

private fun secondExample() {
    /* Modelo antigo
    try {
        // Converte para a classe jogo
        val meuInfoJogo = gson.fromJson(body, br.com.alura.alugames.modelo.InfoJogo::class.java)
        val meuJogo = br.com.alura.alugames.modelo.Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
        println(meuJogo)
    } catch (ex: NullPointerException) {
        println("br.com.alura.alugames.modelo.Jogo não encontrado.")
    } catch (ex: JsonSyntaxException) {
        println("br.com.alura.alugames.modelo.Jogo não encontrado, digite novamente.")
        thirdExample()
    }
    */

    val leitura = Scanner(System.`in`)
    //val gamer = Gamer.criarGamer(leitura)
    val gamer = Gamer.criarGamerMock()

    do {
        buscarJogoUseCase(leitura, gamer)
        println("Pesquisar novamente? S/N")
        val buscarOutroJogo = leitura.nextLine()
    } while (buscarOutroJogo.equals("s", true))

    println("\nJogos encontrados:")
    println(gamer.jogosBuscados)

    println("\nOrdenar por titulo:")
    gamer.jogosBuscados.sortBy { it?.titulo }
    gamer.jogosBuscados.forEach { println(it?.titulo) }

    println("\nJogos Filtrados:")
    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }
    jogosFiltrados.forEach { println(it?.titulo) }

    // Remover item da lista de acordo com a posição
    // gamer.jogosBuscados.removeAt(1)
}

private fun buscarJogoUseCase(leitura: Scanner, gamer: Gamer) {

    val api = ConsumoApi()

    println("Digite o código do jogo:")
    val busca = leitura.nextLine()

    // Fala que pode er null e inicializa
    var meuJogo: Jogo? = null

    // Modelo novo - Ele atribui e retorna para o resultado
    val resultado = runCatching {
        val response = api.buscarJogo(busca)
        val title = response!!.info.title
        meuJogo = Jogo(title, response.info.thumb)
        meuJogo?.descricao = "Descricao do Jogo $title"
    }

    // Se deu erro
    resultado.onFailure {
        println("Jogo não encontrado, digite novamente.")
        buscarJogoUseCase(leitura, gamer)
    }

    // Se deu certo
    resultado.onSuccess {
        println("Jogo encontrado:")
        // Adicionando na lista
        gamer.jogosBuscados.add(meuJogo)
    }

}