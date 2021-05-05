package com.demo.schoolglink

import android.content.Context

/**
 * Created by Siddharth Shakya on 05-May-21.
 */
class Book(bookTitle: String, bookImageUrl: String, bookAuthorName: String) {
    var bookTitle: String? = bookTitle
    var bookImageUrl: String? = bookImageUrl
    var bookAuthorName: String? = bookAuthorName
    var context: Context? = null
    
}