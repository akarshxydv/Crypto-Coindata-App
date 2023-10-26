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

    private lateinit var loadingPB: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchEdt = findViewById(R.id.idEdtCurrency)
        loadingPB = findViewById(R.id.idPBLoading)
        currencyRV = findViewById(R.id.idRVcurrency)



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
