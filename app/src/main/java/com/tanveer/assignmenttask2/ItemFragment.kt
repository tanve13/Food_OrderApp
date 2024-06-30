package com.tanveer.assignmenttask2

import android.app.AlertDialog
import android.app.Dialog
import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tanveer.assignmenttask2.databinding.CustomLayoutBinding
import com.tanveer.assignmenttask2.databinding.FragmentItemBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentItemBinding? = null
    var mainActivity: MainActivity? = null
    var array = arrayListOf<AdapterDataClass>()
    var adapter = AdapterClass(array)
    lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lvListArrayAdapter?.adapter = adapter
        binding?.btnFab?.setOnClickListener{
            val dialogBinding = CustomLayoutBinding.inflate(layoutInflater)
            val dialog = Dialog(requireContext()).apply {
                setContentView(dialogBinding.root)
                show()
            }
            dialogBinding.btnAdd.setOnClickListener{
                if (dialogBinding.etEnterItem?.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etEnterItem.error = resources.getString((R.string.enter_item))
                } else if (dialogBinding.etEnterQuantity?.text?.toString()?.trim().isNullOrEmpty()){
                    dialogBinding.etEnterQuantity.error = resources.getString(R.string.enter_quantity)
                }
                else{ array.add(AdapterDataClass(
                    dialogBinding.etEnterItem?.text?.toString()?:"",
                   dialogBinding.etEnterQuantity?.text?.toString()?.toInt())
                )
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            binding?.lvListArrayAdapter?.setOnItemLongClickListener{ _,_,i,Long ->
                var alertDialog = AlertDialog.Builder(requireContext())
                alertDialog.setTitle(resources.getString(R.string.Do_you_want_to_delete_this_item))
                alertDialog.setPositiveButton("Yes"){ _,_ ->
                  array.removeAt(i)
                    adapter.notifyDataSetChanged()
                }
                alertDialog.setNegativeButton("no"){_,_ ->
                }
                alertDialog.show()
                return@setOnItemLongClickListener true
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
         * @return A new instance of fragment ItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}