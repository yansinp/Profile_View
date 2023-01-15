package com.example.xpay.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.xpay.R
import com.example.xpay.databinding.FragmentDetailsBinding
import com.example.xpay.domain.model.UserDetailsResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        setListeners()
        observeDetailsState()
    }

    private fun setListeners() {
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeDetailsState() = viewLifecycleOwner.lifecycleScope.launch() {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.detailsState.collectLatest {
                when (it) {
                    is DetailsState.Loading -> handleLoading(showLoading = true)
                    is DetailsState.SuccessUsersDetails -> handleSuccess(it.userDetails)
                    is DetailsState.Error -> showError(it.error)
                    else -> Unit
                }
            }
        }
    }

    private fun showError(error: String) {
        handleLoading(false)
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun handleSuccess(userDetails: UserDetailsResponse) {
        handleLoading(false)
        binding.apply {
            photoImg.load(userDetails.image)
            name.text =
                userDetails.firstName + " " + userDetails.maidenName + " " + userDetails.lastName
            displayData(userDetails)

            personeldetails.setOnClickListener {
                contactsInfoLl.visibility = View.GONE
                personalDetailsLl.visibility = View.VISIBLE
                addressLl.visibility = View.GONE
                workDetailsLl.visibility = View.GONE
                bankLl.visibility = View.GONE
                othersLl.visibility = View.GONE

                displayData(userDetails)
            }

            contactInfo.setOnClickListener {
                contactsInfoLl.visibility = View.VISIBLE
                personalDetailsLl.visibility = View.GONE
                addressLl.visibility = View.GONE
                workDetailsLl.visibility = View.GONE
                bankLl.visibility = View.GONE
                othersLl.visibility = View.GONE

                phone.text = "Phone : " + userDetails.phone
                email.text = "Email : " + userDetails.email
                username.text = "User Name : " + userDetails.username
                password.text = "Password : " + userDetails.password
            }

            address.setOnClickListener {
                addressLl.visibility = View.VISIBLE
                personalDetailsLl.visibility = View.GONE
                contactsInfoLl.visibility = View.GONE
                workDetailsLl.visibility = View.GONE
                bankLl.visibility = View.GONE
                othersLl.visibility = View.GONE

                addrss.text = "Address : " + userDetails.address?.address
                city.text = "City : " + userDetails.address?.city
                state.text = "State : " + userDetails.address?.state
                postalcode.text = "Postal Code : " + userDetails.address?.postalCode
                lat.text = "Lat : " + userDetails.address?.coordinates?.lat
                lon.text = "Lon : " + userDetails.address?.coordinates?.lng
            }

            workDetails.setOnClickListener {
                workDetailsLl.visibility = View.VISIBLE
                personalDetailsLl.visibility = View.GONE
                contactsInfoLl.visibility = View.GONE
                addressLl.visibility = View.GONE
                bankLl.visibility = View.GONE
                othersLl.visibility = View.GONE

                firmname.text = "Firm Name : " + userDetails.company?.name
                designtion.text = "Designation : " + userDetails.company?.title
                addressfirm.text = "Address : " + userDetails.company?.address?.address
                cityfirm.text = "City : " + userDetails.company?.address?.city
                latfirm.text = "Lat : " + userDetails.company?.address?.coordinates?.lat
                lonfirm.text = "Lon : " + userDetails.company?.address?.coordinates?.lng
                postcodefirm.text =
                    "Postal Code : " + userDetails.company?.address?.postalCode
                statefirm.text = "State : " + userDetails.company?.address?.state
                dptfirm.text = "Department : " + userDetails.company?.department
            }

            bank.setOnClickListener {
                bankLl.visibility = View.VISIBLE
                personalDetailsLl.visibility = View.GONE
                contactsInfoLl.visibility = View.GONE
                addressLl.visibility = View.GONE
                workDetailsLl.visibility = View.GONE
                othersLl.visibility = View.GONE

                cardnumber.text = "Card Number : " + userDetails.bank?.cardNumber
                cardexpire.text = "Card Expire : " + userDetails.bank?.cardExpire
                currency.text = "Currency : " + userDetails.bank?.currency
                cardtype.text = "Card Type : " + userDetails.bank?.cardType
            }

            others.setOnClickListener {
                othersLl.visibility = View.VISIBLE
                personalDetailsLl.visibility = View.GONE
                contactsInfoLl.visibility = View.GONE
                addressLl.visibility = View.GONE
                workDetailsLl.visibility = View.GONE
                bankLl.visibility = View.GONE

                domain.text = "Domain : " + userDetails.domain
                ip.text = "IP : " + userDetails.ip
                ein.text = "EIN : " + userDetails.ein
                ssn.text = "SSN : " + userDetails.ssn
                useragent.text = "User Agent : " + userDetails.userAgent
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayData(userDetails: UserDetailsResponse) {
        binding.apply {
            gender.text = "Gender : " + userDetails.gender
            gender.text = "Gender : " + userDetails.gender
            age.text = "Age : " + userDetails.age
            dateOfbirth.text = "Date Of Birth : " + userDetails.birthDate
            bloodgroup.text = "Blood Grp : " + userDetails.bloodGroup
            weight.text = "Weight : " + userDetails.weight
            hieght.text = "Height : " + userDetails.height
            eyecolour.text = "Eye Colour : " + userDetails.eyeColor
            haircolour.text = "Hair Colour : " + userDetails.hair?.color
            hairtype.text = "Hair Type : " + userDetails.hair?.type
            university.text = "University : " + userDetails.university
        }
    }

    private fun handleLoading(showLoading: Boolean) = with(binding) {
        progressBar.isVisible = showLoading
    }
}