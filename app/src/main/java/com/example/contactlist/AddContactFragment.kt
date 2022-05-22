package com.example.contactlist

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactlist.data.ContactItem
import com.example.contactlist.data.Contacts
import com.example.contactlist.data.avatars
import com.example.contactlist.databinding.FragmentAddContactBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [AddContactFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddContactFragment : Fragment() {
    val args: AddContactFragmentArgs by navArgs()
    private lateinit var binding: FragmentAddContactBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener { saveTask() }
        when (args.contactToEdit?.avatarID) {
            1 -> {
                binding.avatarButton.setImageResource(R.drawable.dxbddc2xkaaqz34)
                binding.avatarButton.tag = 1
            }
            2 -> {
                binding.avatarButton.setImageResource(R.drawable.esta)
                binding.avatarButton.tag = 2
            }
            3 -> {
                binding.avatarButton.setImageResource(R.drawable.doge)
                binding.avatarButton.tag = 3
            }
            else -> {
                val avatarHelp = avatars()
                var avatarChosen: Int = avatarHelp.getid()
                when (avatarChosen) {
                    1 -> {
                        binding.avatarButton.setImageResource(R.drawable.dxbddc2xkaaqz34)
                        binding.avatarButton.tag = 1
                    }
                    2 -> {
                        binding.avatarButton.setImageResource(R.drawable.esta)
                        binding.avatarButton.tag = 2
                    }
                    3 -> {
                        binding.avatarButton.setImageResource(R.drawable.doge)
                        binding.avatarButton.tag = 3
                    }
                    else -> {
                        binding.avatarButton.setImageResource(R.drawable.esta)
                        binding.avatarButton.tag = 2
                    }
                }
            }
        }
        binding.nameInput.setText(args.contactToEdit?.nick)
        if(args.contactToEdit?.dateOfBirth == null){
            binding.dateInput.setText("01/01/2000")
        } else {
            binding.dateInput.setText(args.contactToEdit?.dateOfBirth)
        }
        binding.phoneInput.setText(args.contactToEdit?.phoneNumber)
    }

    private fun saveTask() {
        var name: String = binding.nameInput.text.toString()
        var dateOfBirth: String = binding.dateInput.text.toString()
        var phoneNumber: String = binding.phoneInput.text.toString()
        var avatarString: String = binding.avatarButton.tag.toString()
        var avatar: Int
        when (avatarString) {
            "3" -> {
                avatar = 3
            }
            "2" -> {
                avatar = 2
            }
            else -> {
                avatar = 1
            }
        }
        if (name.isEmpty()) name = "Anonimowy"
        if (dateOfBirth.isEmpty()) dateOfBirth = "01/01/2000"
        if (phoneNumber.isEmpty()) phoneNumber = "997"
        if (checkingPhone(phoneNumber) && checkingDate(dateOfBirth)) {
                val contactItem = ContactItem(
                    { name + dateOfBirth + phoneNumber + avatar }.hashCode().toString(),
                    name,
                    dateOfBirth,
                    phoneNumber,
                    avatar
                )
                if (!args.edit) {
                    Contacts.addContact(contactItem)
                } else {
                    Contacts.updateContact(args.contactToEdit, contactItem)
                }

                val inputMethodManager: InputMethodManager =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
                findNavController().popBackStack(R.id.contactFragment, false)
            }
        }
    }

    private fun checkingPhone(phoneNumber: String): Boolean {
        if(phoneNumber.length <= 9){
            for(element in phoneNumber){
                if(element in '0'..'9'){
                    continue
                } else{
                    return false
                }
            }
            return true
        }
        return false
    }

    private fun checkingDate(dateOfBirth: String): Boolean {
        if(dateOfBirth[0] in '0'..'9' && dateOfBirth[1] in '0'..'9'){
            if(dateOfBirth[2] =='/'){
                if(dateOfBirth[3] in '0'..'9' && dateOfBirth[4] in '0'..'9'){
                    if(dateOfBirth[5] =='/'){
                        if(dateOfBirth.length <= 10){
                            for(i in 6 until dateOfBirth.length){
                                if(dateOfBirth[i] in '0'..'9'){
                                    continue
                                }else{
                                    return false
                                }
                            }
                            return true
                        }else{
                            return false
                        }
                    } else{
                        return false
                    }
                }else{
                    return false
                }
            }else{
                return false
            }
    }
        return false

}