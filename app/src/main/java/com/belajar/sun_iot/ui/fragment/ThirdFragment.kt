package com.belajar.sun_iot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.sun_iot.R
import com.belajar.sun_iot.data.ModelDummy
import com.belajar.sun_iot.data.ModelTime
import com.belajar.sun_iot.databinding.FragmentFirstBinding
import com.belajar.sun_iot.ui.adapter.AdapterSecond
import com.belajar.sun_iot.ui.adapter.AdapterThird

class ThirdFragment : Fragment() {

    private  var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val time = ModelTime()
    private val mAdapter = AdapterThird()
    var dummy = ArrayList<ModelDummy>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun loadData(){
        dummy.addAll(
            arrayListOf(
                ModelDummy(
                    1000,
                    1000
                ),
                ModelDummy(
                    1000,
                    1000
                ),
                ModelDummy(
                    1000,
                    1000
                ),
                ModelDummy(
                    1000,
                    1000
                ),
                ModelDummy(
                    1000,
                    1000
                ),
                ModelDummy(
                    1000,
                    1000
                ),
                ModelDummy(
                    1000,
                    1000
                ),
            )
        )

        mAdapter.updateAdapter(dummy)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        binding.apply {

            listDataRecap.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(activity)
            }
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, time.time)
            (autoComplete as? AutoCompleteTextView)?.setAdapter(adapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}