package com.demo.schoolglink

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.schoolglink.MainActivity.Constants
import com.demo.schoolglink.databinding.BookItemLayoutBinding

/**
 * Created by Siddharth Shakya on 05-May-21.
 */
class BookListAdapter : RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {
    
    private lateinit var myContext: Context
    private lateinit var bookListData: List<Book> 


    fun bookListAdapter(mContext: Context, bookListData: List<Book>) {
        this.myContext = mContext
        this.bookListData = bookListData
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        // bind book items view 
        val binding : BookItemLayoutBinding = BookItemLayoutBinding.inflate(LayoutInflater.from(myContext))
        val view: View
        view = binding.root
        return MyViewHolder(view)
    }

    

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // load images using glide
        Glide.with(myContext)
                .load(bookListData[position].bookImageUrl)
                .fitCenter()
                .centerCrop()
                .into(holder.bookImage)
        
        holder.bookImage.setOnClickListener { v ->
            val sendData = Intent(myContext, BookPreviewActivity::class.java)
            sendData.putExtra(Constants.BOOK_TITLE, bookListData[position].bookTitle)
            sendData.putExtra(Constants.BOOK_AUTHOR_NAME, bookListData[position].bookAuthorName)
            sendData.putExtra(Constants.BOOK_IMAGE_URL, bookListData[position].bookImageUrl)
            myContext.startActivity(sendData)

        }
    }

    override fun getItemCount(): Int {
        
        return bookListData.size
    }

     class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         //var bookTitle: TextView = itemView.findViewById(R.id.book_name)
         var bookImage: ImageView = itemView.findViewById(R.id.book_image)
         //var bookAuthorName: TextView = itemView.findViewById(R.id.book_author_name)
     }

}