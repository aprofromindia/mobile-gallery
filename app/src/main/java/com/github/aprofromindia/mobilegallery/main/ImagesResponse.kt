package com.github.aprofromindia.mobilegallery.main

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ImagesResponse(val images: List<Image>)

class Image(val uri: String, val set: String)