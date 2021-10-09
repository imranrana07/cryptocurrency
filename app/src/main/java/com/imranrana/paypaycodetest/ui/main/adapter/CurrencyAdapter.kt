package com.imranrana.paypaycodetest.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.imranrana.paypaycodetest.data.model.Currency
import com.imranrana.paypaycodetest.databinding.ItemCurrencyRatesBinding

class CurrencyAdapter(private val list:ArrayList<Currency>):
    RecyclerView.Adapter<CurrencyViewHolder>(), Filterable {
    private lateinit var context: Context
    private val currencyTmpList = ArrayList<Currency>()
    private lateinit var filteredList: ArrayList<Currency>
    init {
        currencyTmpList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        context = parent.context
        val view = ItemCurrencyRatesBinding.inflate(LayoutInflater.from(context),parent,false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.currencyName.text = list[position].currencyName
        holder.currencyRate.text = list[position].currencyRate.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getFilter(): Filter {
        return filterList
    }

    private val filterList = object: Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            filteredList = ArrayList()
            if (p0 == null || p0.isEmpty()){
                filteredList.addAll(currencyTmpList)
            }else{
                val filterWithCurrency = p0.toString().lowercase().trim()
                currencyTmpList.forEach{
                    if (it.currencyName.contains(filterWithCurrency,true)){
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            results.count = filteredList.size
            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            list.clear()
            list.addAll(p1?.values as ArrayList<Currency>)
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as Currency).currencyName
        }
    }

    fun multiplyWithAmount(amount: Double){
        val tmpList = ArrayList<Currency>()
        if (amount == 0.0){
            list.clear()
            if (filteredList.size==0) {
                list.addAll(currencyTmpList)
            }
            else {
                list.addAll(filteredList)
            }
//            notifyDataSetChanged()
        }else{
            list.clear()
            if (filteredList.size==0) {
                currencyTmpList.forEach {
                    tmpList.add(Currency(it.currencyName,it.currencyRate*amount))
                }
                list.addAll(tmpList)
            }
            else {
                filteredList.forEach {
                    tmpList.add(Currency(it.currencyName,it.currencyRate*amount))
                }
                list.addAll(tmpList)
            }
//            notifyDataSetChanged()
        }
        notifyDataSetChanged()
    }
}