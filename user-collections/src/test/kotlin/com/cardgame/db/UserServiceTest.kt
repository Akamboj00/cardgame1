package com.cardgame.db

import com.cardgame.CardService
import com.cardgame.FakeData
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import com.cardgame.model.Collection
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension


@Profile("UserServiceTest")
@Primary
@Service
class FakeCardService : CardService(){
    override fun fetchData() {
        val dto = FakeData.getCollectionDto()
        super.collection = Collection(dto)
    }
}

@ActiveProfiles("UserServiceTest", "test")
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
internal class UserServiceTest{

    @Autowired
    private lateinit var userService : UserService

    @Autowired
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun initTest(){
        userRepository.deleteAll()
    }

    @Test
    fun testCreateUser(){
        val id = "foo"
        assertTrue(userService.registerNewUser(id))
        assertTrue(userRepository.existsById(id))
    }


}