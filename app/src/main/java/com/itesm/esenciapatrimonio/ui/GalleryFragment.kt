package com.itesm.esenciapatrimonio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itesm.esenciapatrimonio.*
import com.itesm.esenciapatrimonio.databinding.FragmentGaleriaBinding
import com.itesm.esenciapatrimonio.transactions.TransactionData

class GalleryFragment : Fragment() {

    private var _binding: FragmentGaleriaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var site: SRestoreSite
    lateinit var listCompare:MutableList<SComparePicture>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        site = TransactionData.restoredSite[0]

        _binding = FragmentGaleriaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var recycla = _binding!!.recyclerGaleria

        var layoutManager: RecyclerView.LayoutManager? = null

        layoutManager = LinearLayoutManager(activity)
        //layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycla.layoutManager = layoutManager

        ParseApp.getAllComparePictureBySite(site.site_name){listComp->
            listCompare = listComp

            TransactionData.listCompare = listCompare

            GalleryAdapter.initData()
            val adapter = GalleryAdapter()
            recycla.adapter = adapter

            adapter.setListener(object : GalleryAdapter.GalleryAdapterClickEvents {
                override fun onClick(url: String) {

                    val fragmentManager = fragmentManager
                    val fragmentTransaction = fragmentManager?.beginTransaction()

                    val fragment = FullScreenImageFragment(url)

                    fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                    fragmentTransaction?.addToBackStack(null)
                    fragmentTransaction?.commit()
                }
            })
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
