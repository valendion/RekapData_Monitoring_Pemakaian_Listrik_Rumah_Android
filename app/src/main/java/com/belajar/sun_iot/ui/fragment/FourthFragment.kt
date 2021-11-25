package com.belajar.sun_iot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.sun_iot.R
import com.belajar.sun_iot.data.ModelDummy
import com.belajar.sun_iot.data.ModelResponseRecap
import com.belajar.sun_iot.data.ModelTime
import com.belajar.sun_iot.databinding.FragmentFirstBinding
import com.belajar.sun_iot.ui.adapter.AdapterFirst
import com.belajar.sun_iot.ui.adapter.AdapterFourth
import com.belajar.sun_iot.utils.LoadingList
import com.belajar.sun_iot.utils.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.AdapterView.OnItemClickListener

class FourthFragment : Fragment() {


    private  var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val time = ModelTime()
    private val mAdapter = AdapterFourth()
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            listDataRecap.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(activity)
            }

            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, time.time)
            (autoComplete as? AutoCompleteTextView)?.setAdapter(arrayAdapter)

            autoComplete.setText(autoComplete.adapter.getItem(0).toString(), false)

            noData.root.visibility = View.INVISIBLE
//            LoadingList.showLoading(1, loadingLamp.pbLoading)
            NetworkConfig.instance.getKulkasHour().enqueue(object : Callback<ModelResponseRecap> {
                override fun onResponse(
                    call: Call<ModelResponseRecap>,
                    response: Response<ModelResponseRecap>
                ) {
//                    LoadingList.showLoading(0, loadingLamp.pbLoading)
                    noData.root.visibility = View.INVISIBLE

                    if (response.isSuccessful) {
                        val data = response.body()

                        if (data?.data!!.size != 0) {
                            data.data?.let {

                                mAdapter.updateAdapter(it)
                            }
                        } else if (data.data!!.size == 0) {
                            noData.root.visibility = View.VISIBLE
                            mAdapter.clearItem()
                        }
                    }
                }

                override fun onFailure(call: Call<ModelResponseRecap>, t: Throwable) {
                    Toast.makeText(activity, "Gagal", Toast.LENGTH_SHORT).show()
                }

            })



            (dropdownTime.editText as AutoCompleteTextView).onItemClickListener =
                OnItemClickListener { adapterView, view, position, id ->
                    when (arrayAdapter.getItem(position)!!) {
                        "Jam" -> {
//                            LoadingList.showLoading(1, loadingLamp.pbLoading)
                            noData.root.visibility = View.INVISIBLE
                            NetworkConfig.instance.getKulkasHour()
                                .enqueue(object : Callback<ModelResponseRecap> {
                                    override fun onResponse(
                                        call: Call<ModelResponseRecap>,
                                        response: Response<ModelResponseRecap>
                                    ) {
//                                        LoadingList.showLoading(0, loadingLamp.pbLoading)
                                        noData.root.visibility = View.INVISIBLE

                                        if (response.isSuccessful) {
                                            val data = response.body()

                                            if (data?.data!!.size != 0) {
                                                data.data?.let {

                                                    mAdapter.updateAdapter(it)
                                                }
                                            } else if (data.data!!.size == 0) {
                                                noData.root.visibility = View.VISIBLE
                                                mAdapter.clearItem()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<ModelResponseRecap>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(activity, "", Toast.LENGTH_SHORT).show()
                                    }

                                })

                        }
                        "Hari" -> {
                            noData.root.visibility = View.INVISIBLE
//                            LoadingList.showLoading(1, loadingLamp.pbLoading)
                            NetworkConfig.instance.getKulkasToday()
                                .enqueue(object : Callback<ModelResponseRecap> {
                                    override fun onResponse(
                                        call: Call<ModelResponseRecap>,
                                        response: Response<ModelResponseRecap>
                                    ) {
//                                        LoadingList.showLoading(0, loadingLamp.pbLoading)
                                        noData.root.visibility = View.INVISIBLE

                                        if (response.isSuccessful) {
                                            val data = response.body()

                                            if (data?.data!!.size != 0) {
                                                data.data?.let {

                                                    mAdapter.updateAdapter(it)
                                                }
                                            } else if (data.data!!.size == 0) {
                                                noData.root.visibility = View.VISIBLE
                                                mAdapter.clearItem()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<ModelResponseRecap>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(activity, "", Toast.LENGTH_SHORT).show()
                                    }

                                })

                        }
                        else -> {
                            noData.root.visibility = View.INVISIBLE
//                            LoadingList.showLoading(1, loadingLamp.pbLoading)
                            NetworkConfig.instance.getKulkasWeek()
                                .enqueue(object : Callback<ModelResponseRecap> {
                                    override fun onResponse(
                                        call: Call<ModelResponseRecap>,
                                        response: Response<ModelResponseRecap>
                                    ) {
//                                        LoadingList.showLoading(0, loadingLamp.pbLoading)
                                        noData.root.visibility = View.INVISIBLE

                                        if (response.isSuccessful) {
                                            val data = response.body()

                                            if (data?.data!!.size != 0) {
                                                data.data?.let {

                                                    mAdapter.updateAdapter(it)
                                                }
                                            } else if (data.data!!.size == 0) {
                                                noData.root.visibility = View.VISIBLE
                                                mAdapter.clearItem()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<ModelResponseRecap>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(activity, "Gagal", Toast.LENGTH_SHORT).show()
                                    }

                                })
                        }
                    }
                }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}