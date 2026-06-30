package io.github.italodgsilva.integration.presentation.resource

import io.github.serpro69.kfaker.games.GamesFaker
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

@QuarkusTest
class GameResourceTest {

    @Test
    fun `must return game information`() {

        val gamesFaker = GamesFaker()
        val name = gamesFaker.game.title()

        given()
            .accept(MediaType.APPLICATION_JSON)
            .pathParam("name", name)
        .`when`()
            .get("/games/{name}")
        .then()
            .statusCode(Response.Status.OK.statusCode)
            .body("name", equalTo(name))
    }
}