package com.belajar.sun_iot.ui.fragment.realtime

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.belajar.sun_iot.data.ModelRealtime
import com.belajar.sun_iot.databinding.FragmentRealFourBinding
import com.belajar.sun_iot.utils.WritingFormat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RealFourFragment : Fragment() {

    private var _binding: FragmentRealFourBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRealFourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var database =
            FirebaseDatabase.getInstance("https://wattmeter-36101-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("jalur_kulkas")
        var user = ModelRealtime()
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    if (user != null) {
                        user = snapshot.getValue(ModelRealtime::class.java)!!
                        binding.fourFragment.apply {
//                            valueCost.text = user.biaya?.let { WritingFormat.formatRupiah(it) }
//                            valueCurrent.text = user.arus?.let { WritingFormat.formatCurrent(it) }
//                            valuePower.text = user.daya?.let { WritingFormat.formatPower(it) }
//                            valuePowerElectricity.text = user.kwh?.let { WritingFormat.formatPowerElectric(it)  }
//                            valueVoltage.text = user.tegangan?.let { WritingFormat.formatVoltage(it) }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}