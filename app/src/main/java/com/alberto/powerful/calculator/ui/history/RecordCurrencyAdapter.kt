package com.alberto.powerful.calculator.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alberto.powerful.calculator.R
import com.alberto.powerful.calculator.data.dto.RecordCurrencyResponse
import com.alberto.powerful.calculator.databinding.ItemRecordConverterBinding

class RecordCurrencyAdapter constructor(
    var recordsCurrency: List<RecordCurrencyResponse>
) : RecyclerView.Adapter<RecordCurrencyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding: ItemRecordConverterBinding = ItemRecordConverterBinding.bind(itemView)

        fun bind(recordCurrencyResponse: RecordCurrencyResponse) = with(binding) {
            tvExchangeRequest.text = recordCurrencyResponse.from
            tvExchangeResult.text = recordCurrencyResponse.to
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getAllRecordCurrency(recordCurrencyList: List<RecordCurrencyResponse>) {
        this.recordsCurrency = recordCurrencyList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_record_converter, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recordCurrency = recordsCurrency[position]
        holder.bind(recordCurrency)
    }

    override fun getItemCount(): Int {
        return recordsCurrency.size
    }
}