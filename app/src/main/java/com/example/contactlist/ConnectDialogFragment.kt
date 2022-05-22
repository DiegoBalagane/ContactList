package com.example.contactlist

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val CONTACT_NAME_PARAM = "contact name"
private const val CONTACT_POS_PARAM = "contact pos"

/**
 * A simple [Fragment] subclass.
 * Use the [ConnectDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConnectDialogFragment : DialogFragment() {
    lateinit var mListener: OnDeleteDialogInteractionListener
    // TODO: Rename and change types of parameters
    private var contactNameParam: String? = null
    private var contactPosParam: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactNameParam = it.getString(CONTACT_NAME_PARAM)
            contactPosParam = it.getInt(CONTACT_POS_PARAM)
        }
    }

    interface  OnDeleteDialogInteractionListener{
        fun onDialogPositiveClick(pos: Int?)
        fun onDialogNegativeClick(pos: Int?)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConnectDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, pos: Int, interactionListener: OnDeleteDialogInteractionListener) =
            ConnectDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(CONTACT_NAME_PARAM, name)
                    putInt(CONTACT_POS_PARAM, pos)
                }
                mListener = interactionListener
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage("Czy na pewno chcesz zadzwonić do" + " $contactNameParam" + "?")
        builder.setPositiveButton("Zadzwoń", DialogInterface.OnClickListener { dialogInterface, i ->
            mListener?.onDialogPositiveClick(contactPosParam)
        })
        builder.setNegativeButton("Anuluj", DialogInterface.OnClickListener { dialogInterface, i ->
            mListener?.onDialogNegativeClick(contactPosParam)
        })
        return builder.create()
    }
}