package com.example.cryptotrackerapp.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotrackerapp.R
import com.example.cryptotrackerapp.data.Currencymodel
import java.text.DecimalFormat

class CurrencyRVAdapter(
    private val currencyModals: ArrayList<Currencymodel>,
    private val context: Context
) : RecyclerView.Adapter<CurrencyRVAdapter.CurrencyViewholder>() {



    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): CurrencyViewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return CurrencyViewholder(view)
    }

    override fun onBindViewHolder(@NonNull holder: CurrencyViewholder, position: Int) {
        val modal = currencyModals[position]
        holder.nameTV.text = modal.name
        holder.rateTV.text = modal.name
        holder.symbolTV.text = modal.symbol
    }

    override

    fun

            getItemCount(): Int {
        return currencyModals.size
    }

    fun

            filterList(filterlist: ArrayList<Currencymodel>) {
        currencyModals.clear()
        currencyModals.addAll(filterlist)
        notifyDataSetChanged()
    }

    class

    CurrencyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val symbolTV: TextView = itemView.findViewById(R.id.idTVSymbol)
        val rateTV: TextView = itemView.findViewById(R.id.idTVRate)
        val nameTV: TextView = itemView.findViewById(R.id.idTVName)
    }
}