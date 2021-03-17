package ru.geekbrains.filmapp.fragments

import android.Manifest
import ru.geekbrains.filmapp.R
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.geekbrains.filmapp.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding

    private val REQUEST_CODE = 42
    private val contactsList = arrayListOf<String>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_contacts, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkPermissions()
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts()
                } else {
                    context?.let {
                        AlertDialog.Builder(it)
                                .setTitle("Contacts Access")
                                .setMessage("No access to contacts")
                                .setNegativeButton("Close") { dialog, _ -> dialog.dismiss() }
                                .create()
                                .show()
                    }
                }
            }
        }
    }

    private fun checkPermissions() {
        context?.let {
            if (ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED)
            {
                getContacts()
            } else {
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE)
    }

    private fun getContacts() {
        context?.let {
            val contentResolver = it.contentResolver
            val cursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            )

            cursor?.let {
                for (i in 0..cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val number =
                                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        val name =
                                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                        contactsList.add("$name: $number")
                    }
                }
                cursor.close()
            }
        }
        setNumbers()
    }

    private fun setNumbers() {
        val adapter =
                ArrayAdapter(requireContext(), R.layout.simple_list_item_contacts, contactsList)
        binding.contactsList.adapter = adapter
    }
}