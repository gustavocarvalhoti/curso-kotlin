package br.com.alura.alugames.servicos

import br.com.alura.alugames.modelo.InfoJogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpClient as HttpClient1

class ConsumoApi {

    fun buscarJogo(id: String): InfoJogo? {
        val client: HttpClient1 = HttpClient1.newHttpClient()

        // Interpolação de strings
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        val body = response?.body()

        val gson = Gson()
        // Converte para a classe jogo
        val meuInfoJogo = gson.fromJson(body, InfoJogo::class.java)

        return meuInfoJogo
    }


}