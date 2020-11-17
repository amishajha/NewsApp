package com.example.newsnest

import android.accounts.AuthenticatorDescription
import android.media.Image

data class Article(val author:String, val title:String, val description:String, val url:String, val urlToImage: String) {
}