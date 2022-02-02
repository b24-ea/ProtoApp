package com.aldanmaz.skyq.view

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.aldanmaz.skyq.adapter.ProtoAdapter
import com.aldanmaz.skyq.viewmodel.ListViewModel
import com.aldanmaz.skyq.R
import kotlinx.android.synthetic.main.fragment_list.*



class ListFragment : Fragment() {

    private lateinit var viewModel : ListViewModel
    private val protoAdapter = ProtoAdapter(arrayListOf(), listOf())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_list, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refreshData()

        protoList.layoutManager = GridLayoutManager(requireContext(),2)
        protoList.adapter = protoAdapter

        swipeRefreshLayout.setOnRefreshListener {
            protoList.visibility = View.GONE
            protoError.visibility = View.GONE
            protoLoading.visibility = View.VISIBLE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false





        }

        observeLiveData()
    }



    private fun observeLiveData() {
        viewModel.proto.observe(viewLifecycleOwner, Observer { protos ->
            protos?.let {
                protoList.visibility = View.VISIBLE
                protoAdapter.updateProtoList(protos)


            }

        })

        viewModel.protoError.observe(viewLifecycleOwner,Observer { error ->
            error?.let {
                if(it) {
                    protoError.visibility = View.VISIBLE
                } else {
                    protoError.visibility = View.GONE
                }
            }
        })
        viewModel.protoLoading.observe(viewLifecycleOwner,Observer { loading ->
            loading?.let{
                if(it) {
                    protoLoading.visibility = View.VISIBLE
                    protoList.visibility = View.GONE
                    protoError.visibility - View.GONE
                }  else {
                    protoLoading.visibility = View.GONE
                }
            }
        })
    }

    }

