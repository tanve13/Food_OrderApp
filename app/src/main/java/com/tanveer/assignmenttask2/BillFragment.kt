package com.tanveer.assignmenttask2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.tanveer.assignmenttask2.databinding.FragmentBillBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BillFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BillFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentBillBinding? = null
    var mainActivity: MainActivity? = null
    lateinit var arrayAdapter:ArrayAdapter<AdapterDataClass>
    var  number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBillBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1,
            mainActivity?.array?: arrayListOf())
        binding?.lvListArrayAdapter?.adapter = arrayAdapter
        var itemSelected = binding?.lvListArrayAdapter?.toString()
        binding?.item?.setText(itemSelected)
        binding?.btnFabAdd?.setOnClickListener{
            if (binding?.etEnterQuantity?.text?.toString()?.trim().isNullOrEmpty()){
                binding?.etEnterQuantity?.error = "enter Quantity"
            } else{
                number++
                binding?.etEnterQuantity?.setText(number.toString())
            }
        }
        binding?.btnFabSub?.setOnClickListener{
            if (binding?.etEnterQuantity?.text?.toString()?.trim().isNullOrEmpty() ){
                binding?.etEnterQuantity?.error = "enter Quantity"
            } else{
                number--
                binding?.etEnterQuantity?.setText(number.toString())
            }
        }
      binding?.btnOrder?.setOnClickListener{
          Toast.makeText(requireContext(),
              "Ordered Successfully",
              Toast.LENGTH_SHORT).show()
      }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BillFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BillFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}