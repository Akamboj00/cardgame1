package com.cardgame.user.collections

import com.cardgame.user.collections.db.UserService
import com.cardgame.user.collections.dto.Command
import com.cardgame.user.collections.dto.PatchResultDto
import com.cardgame.user.collections.dto.PatchUserDto
import com.cardgame.user.collections.dto.UserDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Api(value = "/api/user-collections", description = "Operations on card collections owned by users")
@RestController
@RequestMapping(
    path = ["/api/user-collections"],
    produces = [(MediaType.APPLICATION_JSON_VALUE)]
)
class RestAPI(
    private val userService: UserService
) {

    @ApiOperation("Retrieve card collection information for a specific user")
    @GetMapping(path = ["/{userId}"])
    fun getUserInfo(
        @PathVariable("userId") userId: String
    ) : ResponseEntity<UserDto>{

        val user = userService.findByIdEager(userId)
        if(user == null){
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.status(200).body(DtoConverter.transform(user))
    }

    @ApiOperation("Create a new user, with the given id")
    @PutMapping(path = ["/{userId}"])
    fun createUser(
        @PathVariable("userId") userId: String
    ): ResponseEntity<Void>{
        val ok = userService.registerNewUser(userId)
        return if(!ok)  ResponseEntity.status(400).build()
        else ResponseEntity.status(201).build()
    }

    @ApiOperation("Execute a command on a user's collection, like for example buying/milling cards")
    @PatchMapping(
        path = ["/{userId}"],
        consumes = [(MediaType.APPLICATION_JSON_VALUE)]
    )
    fun patchUser(
        @PathVariable("userId") userId: String,
        @RequestBody dto: PatchUserDto
    ): ResponseEntity<PatchResultDto>{

        if(dto.command == null){
            return ResponseEntity.status(400).build()
        }

        if(dto.command == Command.OPEN_PACK){
            val ids = try {
                userService.openPack(userId)
            } catch (e: IllegalArgumentException){
                return ResponseEntity.status(400).build()
            }
            return ResponseEntity.status(200).body(PatchResultDto().apply { cardIdsInOpenedPack.addAll(ids) })
        }

        val cardId = dto.cardId
            ?: return ResponseEntity.status(400).build()

        if(dto.command == Command.BUY_CARD){
            try{
                userService.buyCard(userId, cardId)
            } catch (e: IllegalArgumentException){
                return ResponseEntity.status(400).build()
            }
            return ResponseEntity.status(200).body(PatchResultDto())
        }

        if(dto.command == Command.MILL_CARD){
            try{
                userService.millCard(userId, cardId)
            } catch (e: IllegalArgumentException){
                return ResponseEntity.status(400).build()
            }
            return ResponseEntity.status(200).body(PatchResultDto())
        }

        return ResponseEntity.status(400).build()
    }
}