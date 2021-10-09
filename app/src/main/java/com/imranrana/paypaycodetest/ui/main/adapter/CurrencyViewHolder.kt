package com.imranrana.paypaycodetest.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.imranrana.paypaycodetest.databinding.ItemCurrencyRatesBinding

class CurrencyViewHolder(itemView: ItemCurrencyRatesBinding): RecyclerView.ViewHolder(itemView.root) {
    val currencyName = itemView.tvCurrencyName
    val currencyRate = itemView.tvCurrencyRate
}
