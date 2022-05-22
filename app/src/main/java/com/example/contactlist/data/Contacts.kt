package com.example.contactlist.data

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object Contacts {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<ContactItem> = ArrayList()



    private val COUNT = 5

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addContact(createPlaceholderItem(i))
        }
    }

    fun addContact(item: ContactItem) {
        ITEMS.add(item)
    }

    private fun createPlaceholderItem(position: Int): ContactItem {
        return ContactItem(position.toString())
    }

    fun updateContact(contactToEdit: ContactItem?, newContact: ContactItem) {
        contactToEdit?.let { oldContact ->
            val indexOfOldContact = ITEMS.indexOf(oldContact)
            ITEMS.add(indexOfOldContact,newContact)
            ITEMS.removeAt(indexOfOldContact+1)
        }
    }

}
data class ContactItem(val id: String, val nick: String = "Anonimowy", val dateOfBirth: String = "01/01/2000", val phoneNumber: String = "997", val avatarID: Int = (1..3).random()): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!
    ) {
    }

    override fun toString(): String = nick
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nick)
        parcel.writeString(dateOfBirth)
        parcel.writeString(phoneNumber)
        parcel.writeInt(avatarID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactItem> {
        override fun createFromParcel(parcel: Parcel): ContactItem {
            return ContactItem(parcel)
        }

        override fun newArray(size: Int): Array<ContactItem?> {
            return arrayOfNulls(size)
        }
    }
}
