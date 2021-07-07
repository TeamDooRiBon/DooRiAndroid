package kr.co.dooribon.ui.existingtrip.tendency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewOtherMemberTripTypeBinding
import kr.co.dooribon.domain.entity.MemberTripType
import kr.co.dooribon.utils.addChip

class MemberTripTypeAdapter : RecyclerView.Adapter<MemberTripTypeAdapter.MemberTripTypeViewHolder>(){

    private val memberTripTypeList = mutableListOf<MemberTripType>()

    class MemberTripTypeViewHolder(private val binding :ViewOtherMemberTripTypeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : MemberTripType){
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberTripTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewOtherMemberTripTypeBinding.inflate(layoutInflater,parent,false)
        return MemberTripTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberTripTypeViewHolder, position: Int) {
        holder.bind(memberTripTypeList[position])
    }

    override fun getItemCount(): Int = memberTripTypeList.size

    fun submitList(list : List<MemberTripType>){
        memberTripTypeList.clear()
        memberTripTypeList.addAll(list)
        notifyDataSetChanged()
    }
}

@BindingAdapter("chip_adapter_item")
fun LinearLayout.setChips(items : List<String>){
    items.forEach {
        addChip(it)
    }
}
