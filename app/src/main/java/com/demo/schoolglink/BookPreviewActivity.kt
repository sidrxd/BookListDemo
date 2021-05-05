package com.demo.schoolglink

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.demo.schoolglink.databinding.ActivityBookPreviewLayoutBinding

/**
 * Created by Siddharth Shakya on 06-May-21.
 */
class BookPreviewActivity : AppCompatActivity() {

    private lateinit var previewLayoutBinding: ActivityBookPreviewLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View Binding
        previewLayoutBinding = ActivityBookPreviewLayoutBinding.inflate(layoutInflater)
        val view: View = previewLayoutBinding.root
        setContentView(view)
        
        // call the action bar and
        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getBooksData()

    }

    @SuppressLint("SetTextI18n")
    private fun getBooksData() {
        val myBookTitle: String = intent.extras?.get(MainActivity.Constants.BOOK_TITLE).toString()
        val myBookImageUrl: String = intent.extras?.get(MainActivity.Constants.BOOK_IMAGE_URL).toString()
        val myBookAuthorName: String = intent.extras?.get(MainActivity.Constants.BOOK_AUTHOR_NAME).toString()

        //load image using glide cache
        Glide.with(this@BookPreviewActivity)
                .load(myBookImageUrl)
                .into(previewLayoutBinding.bookImage)
        // add book title
        previewLayoutBinding.bookName.text = "Book Name : $myBookTitle"
        // add book author name
        previewLayoutBinding.bookAuthorName.text = "Author Name : $myBookAuthorName"

        //add book title in actionBar
        title = myBookTitle

    }

    override fun onBackPressed() {
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}