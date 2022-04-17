package com.example.noteapp.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddBinding
import com.example.noteapp.fragment.SharedViewModel
import com.example.noteapp.data.model.NoteData
import com.example.noteapp.data.viewModel.NoteViewModel

class AddFragment : Fragment() {

    private var _addBinding : FragmentAddBinding? = null
    private val addBinding get() = _addBinding!!

    private val noteViewModel : NoteViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _addBinding = FragmentAddBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
        addBinding.spAddPrioritas.onItemSelectedListener = sharedViewModel.listener

        return addBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val mTitle = addBinding.edtAddTexttitle.text.toString()
        val mPriority = addBinding.spAddPrioritas.selectedItem.toString()
        val mDesc = addBinding.edtAddDesc.text.toString()

        val validation = sharedViewModel.verifyDataFromUser(mTitle,mDesc)
        if (validation) {
            val newData = NoteData(
                0,
                mTitle,
                sharedViewModel.parsePriority(mPriority),
                mDesc
            )
            noteViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Berhasil Ditambahkan",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(),"Data Belum Diisi",Toast.LENGTH_SHORT).show()
        }
    }
}