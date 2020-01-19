package com.github.aprofromindia.mobilegallery.redux

import com.github.aprofromindia.mobilegallery.AppProvider
import com.github.aprofromindia.mobilegallery.detail.SetDetailImage
import com.github.aprofromindia.mobilegallery.main.FailedImages
import com.github.aprofromindia.mobilegallery.main.FetchImages
import com.github.aprofromindia.mobilegallery.main.Image
import com.github.aprofromindia.mobilegallery.main.SuccessImages
import org.junit.Assert.*
import org.junit.Test

class AppStoreIntTest {
    private val sut = AppProvider.appStore

    @Test
    fun whenFetchImagesDispatched_isLoadingShouldBeTrue() {
        // when
        sut.dispatch(FetchImages())

        //then
        assertTrue(sut.imagesState.isLoading)
        assertEquals(0, sut.imagesState.images.size)
        assertNull(sut.imagesState.ex)
    }

    @Test
    fun whenSuccessImagesDispatched_imagesShouldBeNonEmpty() {
        // given
        val images = listOf(Image("1", "1"))

        // when
        sut.dispatch(SuccessImages(images))

        //then
        assertFalse(sut.imagesState.isLoading)
        assertEquals(images, sut.imagesState.images)
        assertEquals(images.size, sut.imagesState.images.size)
        assertNull(sut.imagesState.ex)
    }

    @Test
    fun whenFailedImagesDispatched_exceptionShouldBeNonNull() {
        //given
        val ex = Exception("test error")

        // when
        sut.dispatch(FailedImages(ex))

        //then
        assertFalse(sut.imagesState.isLoading)
        assertNotNull(sut.imagesState.ex)
        assertEquals(ex, sut.imagesState.ex)
        assertEquals(0, sut.imagesState.images.size)
    }

    @Test
    fun whenSetDetailImage_thenDetailImageShouldBeSet() {
        // given
        val image = Image("2", "2")

        // when
        sut.dispatch(SetDetailImage(image))

        //then
        assertEquals(image, sut.detailImageState.image)
    }

}