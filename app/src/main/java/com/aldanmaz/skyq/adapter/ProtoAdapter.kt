package com.aldanmaz.skyq.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aldanmaz.skyq.adapter.ProtoAdapter.*
import com.aldanmaz.skyq.model.Images
import com.aldanmaz.skyq.model.Proto
import com.aldanmaz.skyq.util.downloadFromUrl
import com.aldanmaz.skyq.util.placeholderProgressBar
import com.aldanmaz.skyq.view.ListFragmentDirections
import com.aldanmaz.skyq.R
import kotlinx.android.synthetic.main.item_proto.view.*
import kotlin.coroutines.coroutineContext


class ProtoAdapter(private val protoList: ArrayList<Proto>, private val images: List<Images>): RecyclerView.Adapter<ProtoViewHolder>() {


    class ProtoViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProtoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_proto,parent,false)

        return ProtoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return protoList.size
    }
    override fun onBindViewHolder(holder: ProtoViewHolder, position: Int) {



        holder.view.durationText.text = protoList[position].duration


        holder.view.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(protoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }









        holder.view.protoImage.downloadFromUrl(images, placeholderProgressBar(holder.view.context))

    }





    fun updateProtoList(newProtoList: List<Proto>) {
        protoList.clear()
        protoList.addAll(newProtoList)
        notifyDataSetChanged()
    }



}