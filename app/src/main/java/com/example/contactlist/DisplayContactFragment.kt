package com.example.contactlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactlist.data.ContactItem
import com.example.contactlist.databinding.FragmentDisplayContactBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DisplayContactFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayContactFragment : Fragment() {
    val args: DisplayContactFragmentArgs by navArgs()
    lateinit var binding: FragmentDisplayContactBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDisplayContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contact: ContactItem = args.contact
        binding.displayName.text = contact.nick
        binding.displayPhone.text = contact.phoneNumber
        binding.displayDate.text = contact.dateOfBirth
        if(contact.avatarID == 1){
            binding.displayAvatar.setImageResource(R.drawable.dxbddc2xkaaqz34)
        } else if(contact.avatarID == 2){
            binding.displayAvatar.setImageResource(R.drawable.esta)
        } else {
            binding.displayAvatar.setImageResource(R.drawable.doge)
        }
        binding.displayEdit.setOnClickListener {
            val contactToEdit =
                DisplayContactFragmentDirections.actionDisplayContactFragmentToAddContactFragment(
                    contactToEdit = contact,
                    edit = true)
            findNavController().navigate(contactToEdit)
        }
    }

}