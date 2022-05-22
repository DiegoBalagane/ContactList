package com.example.contactlist

interface ToContactListListener {
    fun onItemClick(position: Int)
    fun onItemLongClick(position: Int)
}