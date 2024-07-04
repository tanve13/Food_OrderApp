package com.tanveer.assignmenttask2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
    var selectedItem = AdapterDataClass()
    var item = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            item = it.getString("item")?:""
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
        binding?.lvListArrayAdapter?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) { var selectedItem = binding?.lvListArrayAdapter?.selectedItem as AdapterDataClass
                    binding?.item?.setText(selectedItem.item)
                   binding?.etEnterQuantity?.setText(selectedItem.Quantity.toString())
                    
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        binding?.btnFabAdd?.setOnClickListener{
            var selectedItem = binding?.lvListArrayAdapter?.selectedItem as AdapterDataClass
             if((binding?.etEnterQuantity?.text?.toString()?.trim()?.toInt()!! >= selectedItem.Quantity.toString().toInt())){
                     Toast.makeText(requireContext(), "cannot select this quantity", Toast.LENGTH_SHORT).show()
            }
            else{
                var number = binding?.etEnterQuantity?.text.toString().toInt()
                number += 1
                binding?.etEnterQuantity?.setText(number.toString())
            }
        }
        binding?.btnFabSub?.setOnClickListener{
             if((binding?.etEnterQuantity?.text?.toString()?.trim()?.toInt()!! <= 1)){
                 Toast.makeText(requireContext(), "cannot select this quantity", Toast.LENGTH_SHORT).show()
            }
            else{
                var number = binding?.etEnterQuantity?.text.toString().toInt()
                number -= 1
                binding?.etEnterQuantity?.setText(number.toString())
            }
        }
      binding?.btnOrder?.setOnClickListener{
          if(binding?.item?.text?.toString()?.isEmpty() == true){
              Toast.makeText(requireContext(),
                  "select an item from the list",
                  Toast.LENGTH_SHORT).show()
          } else if(binding?.etEnterQuantity?.text?.toString()?.isEmpty() == true){
              Toast.makeText(requireContext(),
                  "select the quantity first",
                  Toast.LENGTH_SHORT).show()
          }
          else{
          Toast.makeText(requireContext(),
              "Ordered Successfully",
              Toast.LENGTH_SHORT).show()
          }
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