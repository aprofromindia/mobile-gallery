package com.github.aprofromindia.mobilegallery.detail

import com.github.aprofromindia.mobilegallery.main.Image
import org.junit.Assert.assertEquals
import org.junit.Test

class DetailImageStateTest {
    private val sut = DetailImageState()

    @Test
    fun whenSetDetailImageDispatched_shouldSetImage() {
        // given
        val image = Image("1", "1")

        // when
        val newState = sut.reducer(SetDetailImage(image))

        // then
        assertEquals(image, newState.image)
    }
}