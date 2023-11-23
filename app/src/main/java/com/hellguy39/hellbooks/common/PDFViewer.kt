package com.hellguy39.hellbooks.common

fun extendUrlWithViewer(url: String): String {
    return "https://drive.google.com/viewerng/viewer?embedded=true&url=${url}"
}