package com.github.aprofromindia.mobilegallery.main

import org.junit.Assert.*
import org.junit.Test

class ImagesStateTest {

    private val sut = ImagesState(false, emptyList(), null)

    @Test
    fun whenFetchImagesDispatched_shouldSetIsLoadingTrue() {
        // when
        val newState = sut.reducer(FetchImages())

        // then
        assertTrue(newState.isLoading)
        assertEquals(emptyList<Image>(), newState.images)
        assertNull(newState.ex)
    }

    @Test
    fun whenSuccessImagesDispatched_shouldSetIsLoadingFalse_andReturnImages() {
        // given
        val images = listOf(Image("1", "1"), Image("2", "2"))

        // when
        val newState = sut.reducer(SuccessImages(images))

        // then
        assertFalse(newState.isLoading)
        assertEquals(images, newState.images)
        assertNull(newState.ex)
    }

    @Test
    fun whenFailedImagesDispatched_shouldSetIsLoadingFalse_andReturnException() {
        // given
        val ex = Exception("Test exception")

        // when
        val newState = sut.reducer(FailedImages(ex))

        // then
        assertFalse(newState.isLoading)
        assertEquals(emptyList<Image>(), newState.images)
        assertEquals(ex, newState.ex)
    }
}