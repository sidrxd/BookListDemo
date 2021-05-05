package com.demo.schoolglink

import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.demo.schoolglink.MainActivity.Constants.BOOK_AUTHOR_NAME
import com.demo.schoolglink.MainActivity.Constants.BOOK_IMAGE_URL
import com.demo.schoolglink.MainActivity.Constants.BOOK_TITLE
import com.demo.schoolglink.MainActivity.Constants.SCHOOLGLINK_JSON_URL
import com.demo.schoolglink.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * Created by Siddharth Shakya on 05-May-21.
 */
class MainActivity : AppCompatActivity() {
    
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var bookList: ArrayList<Book>
    private lateinit var listAdapter: BookListAdapter
    
    object Constants {
        const val BOOK_TITLE = "book_title"
        const val BOOK_IMAGE_URL = "image"
        const val BOOK_AUTHOR_NAME = "author"
        const val SCHOOLGLINK_JSON_URL = "https://www.mocky.io/v2/5cc008de310000440a035fdf"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View Binding
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = mainBinding.root
        setContentView(view)
        
        //fetch book items from JSON
        fetchBookList()
    }

    // Fetch JSON data using Volley
    private fun fetchBookList() {
        bookList = ArrayList()
        listAdapter = BookListAdapter()
        val mRequestQueue = Volley.newRequestQueue(applicationContext)
        val bookObjectRequest = JsonObjectRequest(Request.Method.GET, SCHOOLGLINK_JSON_URL, null, { bookObject: JSONObject ->
            try {
                val booksArray: JSONArray = bookObject.getJSONArray("book_array")
                // loop for fetching book items
                for (i in 0 until booksArray.length()) {
                    val book = booksArray.getJSONObject(i)
                    val bookTitle = book.getString(BOOK_TITLE)
                    val bookImageUrl = book.getString(BOOK_IMAGE_URL)
                    val bookAuthorName = book.getString(BOOK_AUTHOR_NAME)

                    //add book info to the list
                    bookList.add(Book(bookTitle, bookImageUrl, bookAuthorName))
                    println("$bookTitle\n$bookImageUrl\n$bookAuthorName$\n")
                }
                //show book info in recyclerview
                // initialize layout manager and set it to recyclerview
                val gridLayoutManager = GridLayoutManager(this,2)
                mainBinding.recyclerviewBooks.layoutManager = gridLayoutManager
                mainBinding.recyclerviewBooks.isNestedScrollingEnabled = false
                listAdapter.bookListAdapter(this@MainActivity, bookList)
                mainBinding.recyclerviewBooks.adapter = listAdapter
                //Hide progressbar
                mainBinding.progressBar.visibility = View.GONE

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }) { obj: VolleyError -> obj.printStackTrace() }
        mRequestQueue.add(bookObjectRequest)
    }
}