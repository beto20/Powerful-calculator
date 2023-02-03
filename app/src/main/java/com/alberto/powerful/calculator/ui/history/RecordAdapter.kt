package com.alberto.powerful.calculator.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alberto.powerful.calculator.R
import com.alberto.powerful.calculator.data.dto.RecordResponse
import com.alberto.powerful.calculator.databinding.ItemRecordBinding

class RecordAdapter constructor(
    var records: List<RecordResponse>
) : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding: ItemRecordBinding = ItemRecordBinding.bind(itemView)

        fun bind(recordResponse: RecordResponse) = with(binding) {
            tvOperationRecord.text = recordResponse.operation
            tvResultRecord.text = recordResponse.result
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getAllRecord(recordList: List<RecordResponse>) {
        this.records = recordList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = records[position]
        holder.bind(record)
    }

    override fun getItemCount(): Int {
        return records.size
    }
}