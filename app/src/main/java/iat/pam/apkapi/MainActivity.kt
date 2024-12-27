package iat.pam.apkapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import iat.pam.apkapi.api.APIClient
import iat.pam.apkapi.api.adapter.QuotesAdapter
import iat.pam.apkapi.api.model.Quotes
import iat.pam.apkapi.api.model.QuotesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<QuotesResponse>
    private lateinit var QuotesAdapter : QuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefresh = findViewById(R.id.refresh_layout)

        recyclerView = findViewById(R.id.refresh_view)

        QuotesAdapter = QuotesAdapter { quotes -> quotesOnClick(quotes)}
        recyclerView.adapter = QuotesAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        swipeRefresh.setOnRefreshListener {
            getData()
        }
        getData()
    }

    private fun quotesOnClick(quotes: Quotes){
        Toast.makeText(applicationContext, quotes.quote, Toast.LENGTH_SHORT).show()
    }

    private fun getData(){
        swipeRefresh.isRefreshing = true

        call = APIClient.quotesService.getAll()
        call.enqueue(object : Callback<QuotesResponse>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<QuotesResponse>, response: Response<QuotesResponse>) {
                swipeRefresh.isRefreshing = false
                if (response.isSuccessful){
                    QuotesAdapter.submitList(response.body()?.quotes)
                    QuotesAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<QuotesResponse>, t: Throwable) {
                swipeRefresh.isRefreshing = false
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }


        })
    }
}

