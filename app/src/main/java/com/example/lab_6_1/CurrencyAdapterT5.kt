package com.example.lab_6_1
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapterT5(private val currencyList: List<CurrencyT5>) : RecyclerView.Adapter<CurrencyAdapterT5.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val charCodeTextView: TextView = itemView.findViewById(R.id.charCodeTextView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val valueTextView: TextView = itemView.findViewById(R.id.valueTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_item_t5, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencyList[position]
        holder.charCodeTextView.text = currency.charCode
        //holder.nameTextView.text = currency.name
        holder.valueTextView.text = currency.value
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }
}

