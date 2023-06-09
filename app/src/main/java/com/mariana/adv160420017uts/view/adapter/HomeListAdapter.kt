package com.mariana.adv160420017uts.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.util.loadImage
import kotlinx.android.synthetic.main.home_list_item.view.*

class HomeListAdapter(val donationList:ArrayList<Donation>)
    :RecyclerView.Adapter<HomeListAdapter.HomeViewHolder>() {
    class HomeViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateDonationList(newDonationList: ArrayList<Donation>){
        donationList.clear()
        donationList.addAll(newDonationList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.home_list_item, parent, false)

        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.view.donationTitle.text = donationList[position].title
        holder.view.txtUang.text = donationList[position].terkumpul
        holder.view.txtDay.text = donationList[position].hari + " hari lagi"

        holder.view.btnDonate.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeDetail(donationList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(donationList[position].photoUrl, progressBar)
    }

    override fun getItemCount(): Int = donationList.size
}