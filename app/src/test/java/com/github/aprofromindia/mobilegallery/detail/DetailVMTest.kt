package com.github.aprofromindia.mobilegallery.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.aprofromindia.mobilegallery.main.ImagesState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

class DetailVMTest {

    @get:Rule
    val taskExecutor = InstantTaskExecutorRule()

    private val sut = DetailVM()

    @Test
    fun whenUpdateStateIsDetailImageState_localStateShouldUpdate() {
        // given
        val state = DetailImageState()

        // when
        sut.update(state)

        // then
        assertEquals(state, sut.state.value)
    }

    @Test
    fun whenImagesStateIsUpdated_localStateShouldNotUpdate() {
        // given
        val state = ImagesState()

        // when
        sut.update(state)

        // then
        assertNotEquals(state, sut.state.value)
    }
}