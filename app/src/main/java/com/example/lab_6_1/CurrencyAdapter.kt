package com.example.lab_6_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter(private val currencies: List<Currency>) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencies[position]
        holder.charCodeTextView.text = currency.charCode
        holder.nominalTextView.text = currency.nominal
        holder.nameTextView.text = currency.name
        holder.valueTextView.text = currency.value
    }

    override fun getItemCount(): Int = currencies.size

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val charCodeTextView: TextView = itemView.findViewById(R.id.charCodeTextView)
        val nominalTextView: TextView = itemView.findViewById(R.id.nominalTextView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val valueTextView: TextView = itemView.findViewById(R.id.valueTextView)
    }
}
