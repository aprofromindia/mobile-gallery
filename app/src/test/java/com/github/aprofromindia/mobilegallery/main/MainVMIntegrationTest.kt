package com.github.aprofromindia.mobilegallery.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.aprofromindia.mobilegallery.AppProvider
import com.github.aprofromindia.mobilegallery.detail.DetailImageState
import com.github.aprofromindia.mobilegallery.rest.ImageService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainVMIntegrationTest {
    @get:Rule
    val taskExecutor = InstantTaskExecutorRule()

    private val images = listOf(Image("1", "1"), Image("2", "2"))

    private val imageService: ImageService = mock {
        onBlocking { getImages() } doReturn Response.success(ImagesResponse(images))
    }

    private val provider: AppProvider = spy()

    private lateinit var sut: MainVM

    @Before
    fun setup() {
        whenever(provider.imageService).doReturn(imageService)
        sut = MainVM(provider.imageService)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenFetchedImages_thenImagesStateIsUpdated() {
        // when
        sut.fetchImages()

        // then
        assertFalse(sut.state.value?.isLoading!!)
        assertEquals(sut.state.value?.images, images)
        assertNull(sut.state.value?.ex)
    }

    @Test
    fun whenImagesStateIsUpdated_localStateShouldUpdate() {
        // given
        val state = ImagesState()

        // when
        sut.update(state)

        // then
        assertEquals(state, sut.state.value)
    }

    @Test
    fun whenDetailImageStateIsUpdated_localStateShouldNotUpdate() {
        // given
        val state = DetailImageState()

        // when
        sut.update(state)

        // then
        assertNotEquals(state, sut.state.value)
    }
}