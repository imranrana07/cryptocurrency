package com.imranrana.paypaycodetest.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.imranrana.paypaycodetest.R
import com.imranrana.paypaycodetest.data.model.Currency
import com.imranrana.paypaycodetest.data.repository.CurrencyRepository
import com.imranrana.paypaycodetest.databinding.FragmentHomeBinding
import com.imranrana.paypaycodetest.ui.main.adapter.CurrencyAdapter
import com.imranrana.paypaycodetest.ui.main.viewmodel.CurrencyViewModel
import com.imranrana.paypaycodetest.ui.main.viewmodel.CurrencyViewModelFactory
import com.imranrana.paypaycodetest.utils.API_KEY
import com.toptoche.searchablespinnerlibrary.SearchableListDialog
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import java.util.ArrayList

class HomeFragment : Fragment(){
    private lateinit var binding: FragmentHomeBinding
    private lateinit var currencyViewModel: CurrencyViewModel
    private lateinit var adapter: CurrencyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyViewModel = ViewModelProvider(
            this,
            CurrencyViewModelFactory(CurrencyRepository())
        ).get(CurrencyViewModel::class.java)

        observers()
        currencyViewModel.getCurrencies(API_KEY)
        currencyViewModel.getCurrenciesRate(API_KEY)

        binding.etCurrencyAmount.addTextChangedListener(watcher)
    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            if (p0!!.isNotEmpty() && p0.toString().toDouble()>0.0) {
                adapter.multiplyWithAmount(p0.toString().toDouble())
            }else{
                adapter.multiplyWithAmount(0.0)
            }
        }
    }

    private fun currencySelectEvent(){
        binding.spSelectCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0?.selectedItem.toString() == "Select Currency"){
                    adapter.filter.filter("")
                }else{
                    adapter.filter.filter(p0?.selectedItem.toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.v("AD","D")
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun observers() {
        currencyViewModel.progressBar.observe(viewLifecycleOwner, {
            binding.progrssBar.visibility = it
        })
        currencyViewModel.error.observe(viewLifecycleOwner, {
            Snackbar.make(requireContext(),requireView(),it,Snackbar.LENGTH_LONG).show()
        })

        currencyViewModel.currencies.observe(viewLifecycleOwner,{
            //country select adapter
            val currencyList = ArrayList<String>()
            currencyList.add("Select Currency")
            currencyList.addAll(it.currencies.keys)
            val adapter = ArrayAdapter(requireContext(),R.layout.support_simple_spinner_dropdown_item,currencyList)
            binding.spSelectCurrency.adapter = adapter
        })

        currencyViewModel.currencyRates.observe(viewLifecycleOwner,{
            currencySelectEvent()
            val list = ArrayList<Currency>()
            for ((key,value) in it.quotes){
                list.add(Currency(key,value))
            }
            adapter = CurrencyAdapter(list)
            val layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            binding.rvCurrencies.layoutManager = layoutManager
            binding.rvCurrencies.adapter = adapter
        })
    }

}

