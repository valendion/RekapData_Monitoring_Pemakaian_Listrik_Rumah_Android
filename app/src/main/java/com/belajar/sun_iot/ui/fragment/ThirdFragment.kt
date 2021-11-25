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

import com.belajar.sun_iot.ui.adapter.AdapterThird
import com.belajar.sun_iot.utils.LoadingList
import com.belajar.sun_iot.utils.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.belajar.sun_iot.ui.MainActivity
import com.belajar.sun_iot.ui.MainActivity.FragmentRefreshListener


class ThirdFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
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
            loadingUni.root.visibility = View.VISIBLE

            NetworkConfig.instance.getUniHour()
                .enqueue(object : Callback<ModelResponseRecap> {
                    override fun onResponse(
                        call: Call<ModelResponseRecap>,
                        response: Response<ModelResponseRecap>
                    ) {
//                                LoadingList.showLoading(0, loadingLamp.pbLoading)
                        loadingUni.root.visibility = View.INVISIBLE
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
                        loadingUni.root.visibility = View.INVISIBLE
                        Toast.makeText(activity, "Gagal", Toast.LENGTH_SHORT).show()
                    }

                })

            (dropdownTime.editText as AutoCompleteTextView).onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    when (arrayAdapter.getItem(position)!!) {
                        "Jam" -> {
                            loadingUni.root.visibility = View.VISIBLE
                            noData.root.visibility = View.INVISIBLE
                            NetworkConfig.instance.getUniHour()
                                .enqueue(object : Callback<ModelResponseRecap> {
                                    override fun onResponse(
                                        call: Call<ModelResponseRecap>,
                                        response: Response<ModelResponseRecap>
                                    ) {
                                        loadingUni.root.visibility = View.INVISIBLE
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
                                        loadingUni.root.visibility = View.INVISIBLE
                                        Toast.makeText(activity, "", Toast.LENGTH_SHORT)
                                            .show()
                                    }

                                })

                        }
                        "Hari" -> {
                            noData.root.visibility = View.INVISIBLE
                            loadingUni.root.visibility = View.VISIBLE
//                                    LoadingList.showLoading(1, loadingLamp.pbLoading)
                            NetworkConfig.instance.getUniToday()
                                .enqueue(object : Callback<ModelResponseRecap> {
                                    override fun onResponse(
                                        call: Call<ModelResponseRecap>,
                                        response: Response<ModelResponseRecap>
                                    ) {
//                                                LoadingList.showLoading(0, loadingLamp.pbLoading)
                                        noData.root.visibility = View.INVISIBLE
                                        loadingUni.root.visibility = View.INVISIBLE

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
                                        loadingUni.root.visibility = View.INVISIBLE
                                        Toast.makeText(activity, "", Toast.LENGTH_SHORT)
                                            .show()
                                    }

                                })

                        }
                        else -> {
                            noData.root.visibility = View.INVISIBLE
                            loadingUni.root.visibility = View.VISIBLE
//                                    LoadingList.showLoading(1, loadingLamp.pbLoading)
                            NetworkConfig.instance.getUniWeek()
                                .enqueue(object : Callback<ModelResponseRecap> {
                                    override fun onResponse(
                                        call: Call<ModelResponseRecap>,
                                        response: Response<ModelResponseRecap>
                                    ) {
//
                                        loadingUni.root.visibility = View.INVISIBLE
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
                                        loadingUni.root.visibility = View.INVISIBLE
                                        Toast.makeText(
                                            activity,
                                            "Gagal",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                })
                        }


                    }


                    (activity as MainActivity?)!!.setFragmentRefreshListener(object :
                        FragmentRefreshListener {
                        override fun onRefresh() {

                            loadingUni.root.visibility = View.INVISIBLE

                            NetworkConfig.instance.getUniHour()
                                .enqueue(object : Callback<ModelResponseRecap> {
                                    override fun onResponse(
                                        call: Call<ModelResponseRecap>,
                                        response: Response<ModelResponseRecap>
                                    ) {
//                                LoadingList.showLoading(0, loadingLamp.pbLoading)
                                        loadingUni.root.visibility = View.INVISIBLE
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
                                        loadingUni.root.visibility = View.INVISIBLE
                                        Toast.makeText(activity, "Gagal", Toast.LENGTH_SHORT).show()
                                    }

                                })

                            (dropdownTime.editText as AutoCompleteTextView).onItemClickListener =
                                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                                    when (arrayAdapter.getItem(position)!!) {
                                        "Jam" -> {
                                            loadingUni.root.visibility = View.VISIBLE
                                            noData.root.visibility = View.INVISIBLE
                                            NetworkConfig.instance.getUniHour()
                                                .enqueue(object : Callback<ModelResponseRecap> {
                                                    override fun onResponse(
                                                        call: Call<ModelResponseRecap>,
                                                        response: Response<ModelResponseRecap>
                                                    ) {
                                                        loadingUni.root.visibility = View.INVISIBLE
                                                        noData.root.visibility = View.INVISIBLE

                                                        if (response.isSuccessful) {
                                                            val data = response.body()

                                                            if (data?.data!!.size != 0) {
                                                                data.data?.let {

                                                                    mAdapter.updateAdapter(it)
                                                                }
                                                            } else if (data.data!!.size == 0) {
                                                                noData.root.visibility =
                                                                    View.VISIBLE
                                                                mAdapter.clearItem()
                                                            }
                                                        }
                                                    }

                                                    override fun onFailure(
                                                        call: Call<ModelResponseRecap>,
                                                        t: Throwable
                                                    ) {
                                                        loadingUni.root.visibility = View.INVISIBLE
                                                        Toast.makeText(
                                                            activity,
                                                            "",
                                                            Toast.LENGTH_SHORT
                                                        )
                                                            .show()
                                                    }

                                                })

                                        }
                                        "Hari" -> {
                                            noData.root.visibility = View.INVISIBLE
                                            loadingUni.root.visibility = View.VISIBLE
//                                    LoadingList.showLoading(1, loadingLamp.pbLoading)
                                            NetworkConfig.instance.getUniToday()
                                                .enqueue(object : Callback<ModelResponseRecap> {
                                                    override fun onResponse(
                                                        call: Call<ModelResponseRecap>,
                                                        response: Response<ModelResponseRecap>
                                                    ) {
//                                                LoadingList.showLoading(0, loadingLamp.pbLoading)
                                                        noData.root.visibility = View.INVISIBLE
                                                        loadingUni.root.visibility = View.INVISIBLE

                                                        if (response.isSuccessful) {
                                                            val data = response.body()

                                                            if (data?.data!!.size != 0) {
                                                                data.data?.let {

                                                                    mAdapter.updateAdapter(it)
                                                                }
                                                            } else if (data.data!!.size == 0) {
                                                                noData.root.visibility =
                                                                    View.VISIBLE
                                                                mAdapter.clearItem()
                                                            }
                                                        }
                                                    }

                                                    override fun onFailure(
                                                        call: Call<ModelResponseRecap>,
                                                        t: Throwable
                                                    ) {
                                                        loadingUni.root.visibility = View.INVISIBLE
                                                        Toast.makeText(
                                                            activity,
                                                            "",
                                                            Toast.LENGTH_SHORT
                                                        )
                                                            .show()
                                                    }

                                                })

                                        }
                                        else -> {
                                            noData.root.visibility = View.INVISIBLE
                                            loadingUni.root.visibility = View.VISIBLE
//                                    LoadingList.showLoading(1, loadingLamp.pbLoading)
                                            NetworkConfig.instance.getUniWeek()
                                                .enqueue(object : Callback<ModelResponseRecap> {
                                                    override fun onResponse(
                                                        call: Call<ModelResponseRecap>,
                                                        response: Response<ModelResponseRecap>
                                                    ) {
//
                                                        loadingUni.root.visibility = View.INVISIBLE
                                                        noData.root.visibility = View.INVISIBLE

                                                        if (response.isSuccessful) {
                                                            val data = response.body()

                                                            if (data?.data!!.size != 0) {
                                                                data.data?.let {

                                                                    mAdapter.updateAdapter(it)
                                                                }
                                                            } else if (data.data!!.size == 0) {
                                                                noData.root.visibility =
                                                                    View.VISIBLE
                                                                mAdapter.clearItem()
                                                            }
                                                        }
                                                    }

                                                    override fun onFailure(
                                                        call: Call<ModelResponseRecap>,
                                                        t: Throwable
                                                    ) {
                                                        loadingUni.root.visibility = View.INVISIBLE
                                                        Toast.makeText(
                                                            activity,
                                                            "Gagal",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }

                                                })
                                        }


                                    }
                                }

                        }
                    })
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}