package io.github.italodgsilva.presentation.resource

import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameInput
import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameOutput
import io.github.italodgsilva.application.usecase.game.getgameinfobyname.GetGameInfoByNameUseCase
import io.github.italodgsilva.presentation.mapper.GameResourceMapper
import io.github.italodgsilva.presentation.response.game.GetGameInfoByNameResponse
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/games")
class GameResource (
    private val useCase: GetGameInfoByNameUseCase
) {

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun getGameInfoByName(
        @PathParam("name") name: String
    ): GetGameInfoByNameResponse {
        val input: GetGameInfoByNameInput = GameResourceMapper.toInput(name)
        val output: GetGameInfoByNameOutput = useCase.execute(input)
        return GameResourceMapper.toResponse(output)
    }
}