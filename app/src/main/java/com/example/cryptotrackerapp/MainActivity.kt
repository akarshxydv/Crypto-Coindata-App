package com.example.cryptotrackerapp


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.cryptotrackerapp.adapter.CurrencyRVAdapter
import com.example.cryptotrackerapp.api.apiUtilities
import com.example.cryptotrackerapp.api.apiinterface
import com.example.cryptotrackerapp.data.Currencymodel
import com.example.cryptotrackerapp.models.MarketModelItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.create
import java.util.*



class MainActivity : AppCompatActivity() {

    private lateinit var currencyRV: RecyclerView
    private lateinit var searchEdt: EditText
    private val currencyModalArrayList = mutableListOf<Currencymodel>()
    private lateinit var loadingPB: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchEdt = findViewById(R.id.idEdtCurrency)
        loadingPB = findViewById(R.id.idPBLoading)
        currencyRV = findViewById(R.id.idRVcurrency)

        searchEdt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

                filter(s.toString())
            }
        })

    }

    private fun filter(filter: String) {
        // Create a new mutable list to store the filtered data.
        val filteredList = mutableListOf<Currencymodel>()

        // Iterate over the original list and add the items that match the filter to the filtered list.

        for (item in currencyModalArrayList) {
            if (item.name.toLowerCase().contains(filter.toLowerCase())) {
                filteredList.add(item)
            }
        }

        // Check if the filtered list is empty.
        if (filteredList.isEmpty()) {
            // Display a toast message indicating that no currency was found.
            Toast.makeText(this, "No currency found..", Toast.LENGTH_SHORT).show()
        } else {
            // Update the recycler view with the filtered list.
            currencyRV.filterList(filteredList)
        }
    }

        private fun getCurrencyList(){
            lifecycleScope.launch(Dispatchers.IO) {
                val res=apiUtilities.getInstance().create(apiinterface::class.java).getMarketData()
                withContext(Dispatchers.Main){
                    currencyRV.adapter=CurrencyRVAdapter(applicationContext,Currencymodel)
                }
            }
        }
}
