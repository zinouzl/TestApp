package com.example.data.mapper

import com.example.data.entities.PostEntity
import com.example.domain.model.Post
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

private val FAKE_POST_ENTITY = PostEntity(
    userId = 9492,
    id = 6771,
    title = "dolor",
    body = "iisque"
)

private val FAKE_POST = Post(
    userId = 9492,
    id = 6771,
    title = "dolor",
    body = "iisque"
)

class PostMapperTest {

    private lateinit var mapper: PostMapper

    @Before
    fun setUP() {
        mapper = PostMapper()
    }

    @Test
    fun `should get the correct post entity from post`() {
        val expectedPost = FAKE_POST
        val actualPost = mapper.toData(FAKE_POST_ENTITY)
        assertEquals(expectedPost, actualPost)
    }

    @Test
    fun `should get the correct post from post entity`() {
        val expectedPostEntity = FAKE_POST_ENTITY
        val actualPostEntity = mapper.toRaw(FAKE_POST)
        assertEquals(expectedPostEntity, actualPostEntity)
    }

}