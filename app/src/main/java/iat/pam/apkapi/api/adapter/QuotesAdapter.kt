package iat.pam.apkapi.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import iat.pam.apkapi.R
import iat.pam.apkapi.api.model.Quotes


class QuotesAdapter(private val onClick: (Quotes) -> Unit ) :
    ListAdapter<Quotes, QuotesAdapter.QuotesViewHolder>(QuotesCallBack) {

    class QuotesViewHolder(itemView: View, val onClick: (Quotes) -> Unit) :
        RecyclerView.ViewHolder(itemView){

        private val quote: TextView = itemView.findViewById(R.id.quote)
        private val author: TextView = itemView.findViewById(R.id.author)


        private var currentQuotes: Quotes? = null

        init {
            itemView.setOnClickListener{
                currentQuotes?.let{
                    onClick(it)
                }
            }
        }

        fun bind(Quotes : Quotes){
            currentQuotes = Quotes

            quote.text = Quotes.quote
            author.text = Quotes.author


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_quotes,parent, false)
        return QuotesViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quotes = getItem(position)
        holder.bind(quotes)
    }

}

object QuotesCallBack : DiffUtil.ItemCallback<Quotes>(){
    override fun areItemsTheSame(oldItem: Quotes, newItem: Quotes): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Quotes, newItem: Quotes): Boolean {
        return oldItem.id == newItem.id
    }

}