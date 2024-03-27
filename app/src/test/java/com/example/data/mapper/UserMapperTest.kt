package com.example.data.mapper

import com.example.data.entities.UserEntity
import com.example.domain.model.User
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

private val FAKE_USER = User(
    id = 9073,
    userName = "Margarita England",
    email = "katrina.rivers@example.com",
    website = "ne"
)

private val FAKE_USER_ENTITY = UserEntity(
    id = 9073,
    userName = "Margarita England",
    email = "katrina.rivers@example.com",
    website = "ne"
)

class UserMapperTest {

    private lateinit var mapper: UserMapper

    @Before
    fun setUp() {
        mapper = UserMapper()
    }

    @Test
    fun `should return the correct user from user entity`() {
        val expectedUser = FAKE_USER
        val actualUser = mapper.toData(FAKE_USER_ENTITY)
        assertEquals(expectedUser, actualUser)
    }

    @Test
    fun `should return the correct user entity from user`() {
        val expectedUserEntity = FAKE_USER_ENTITY
        val actualUserEntity = mapper.toRaw(FAKE_USER)
        assertEquals(expectedUserEntity, actualUserEntity)
    }
}