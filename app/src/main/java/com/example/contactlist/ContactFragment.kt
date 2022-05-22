package com.example.contactlist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.example.contactlist.data.Contacts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.contactlist.databinding.FragmentItemListBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A fragment representing a list of Items.
 */
class ContactFragment : Fragment(), ToContactListListener,
    ConnectDialogFragment.OnDeleteDialogInteractionListener {
    private lateinit var binding: FragmentItemListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(inflater,container,false)

        with(binding.list){
            layoutManager = LinearLayoutManager(context)
            adapter = MyContactRecyclerViewAdapter(Contacts.ITEMS, this@ContactFragment)
        }
        binding.addButton.setOnClickListener { addButtonClick() }
        return binding.root
    }

    private fun addButtonClick() {
        findNavController().navigate(R.id.action_contactFragment_to_addContactFragment)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization

    }

    override fun onItemClick(position: Int) {
        val actionContactFragmentToDisplayContactFragment =
            ContactFragmentDirections.actionContactFragmentToDisplayContactFragment(Contacts.ITEMS.get(position))
        findNavController().navigate(actionContactFragmentToDisplayContactFragment)
    }

    override fun onItemLongClick(position: Int) {
        showConnectDialog(position)
    }

    private fun showConnectDialog(position: Int) {
        val connectDialog = ConnectDialogFragment.newInstance(Contacts.ITEMS.get(position).nick,position,this)
        connectDialog.show(requireActivity().supportFragmentManager,"ConnectDialog")
    }

    override fun onDialogPositiveClick(pos: Int?) {
        //Contacts.ITEMS.removeAt(pos!!)
        Snackbar.make(requireView(), "Połączenia nie działają", Snackbar.LENGTH_LONG).show()
        notifyDataSetChanged()
    }

    private fun notifyDataSetChanged() {
        val rvAdapter = binding.list.adapter
        rvAdapter?.notifyDataSetChanged()
    }

    override fun onDialogNegativeClick(pos: Int?) {
        Snackbar.make(requireView(), "Zrezygnowano z połączenia", Snackbar.LENGTH_LONG)
            .setAction("Redo", View.OnClickListener { showConnectDialog(pos!!) }).show()
    }
}