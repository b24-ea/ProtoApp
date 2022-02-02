package com.aldanmaz.skyq.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aldanmaz.skyq.viewmodel.ProtoDetailViewModel
import com.aldanmaz.skyq.R
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private lateinit var viewModel : ProtoDetailViewModel
    private var protoUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            protoUuid = DetailFragmentArgs.fromBundle(it).protoUuid
        }


        viewModel = ViewModelProviders.of(this).get(ProtoDetailViewModel::class.java)



        observeLiveData()
    }

   private fun observeLiveData() {

       viewModel.protoLiveData.observe(viewLifecycleOwner, Observer{ proto->
           proto?.let {
               titleDetailText.text = proto.title
               idDetailText.text = proto.id.toString()

           }

       })
    }


}