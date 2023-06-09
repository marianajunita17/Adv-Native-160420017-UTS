package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.util.loadImage
import com.mariana.adv160420017uts.viewmodel.HomeDetailViewModel
import kotlinx.android.synthetic.main.fragment_home_detail.*

class HomeDetailFragment : Fragment() {
    private lateinit var viewModel: HomeDetailViewModel

    fun observeViewModel() {
        viewModel.donationLD.observe(viewLifecycleOwner, Observer {
            imgDonation.loadImage(it.photoUrl, progressBar2)
            txtJudul.setText(it.title)
            txtDonatur.setText(it.donatur + " donatur")
            txtPenggalang.setText(it.penggalang)
            txtSisaHari.setText(it.hari + " hari lagi")
            txtTerdanai.setText(it.terkumpul)
            txtTarget.setText(it.goals)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = HomeDetailFragmentArgs.fromBundle(requireArguments()).id

        viewModel = ViewModelProvider(this).get(HomeDetailViewModel::class.java)
        viewModel.fetch(id)

        btnDonasikan.setOnClickListener {
            val action = HomeDetailFragmentDirections.actionDetailHome()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }
}