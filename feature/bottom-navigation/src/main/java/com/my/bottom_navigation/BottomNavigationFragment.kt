package com.my.bottom_navigation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.my.bottom_navigation.databinding.FragmentBottomNavigationBinding
import com.my.core.navigation.ProvideNavigation

class BottomNavigationFragment : Fragment() {

    private var _binding: FragmentBottomNavigationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigation = (requireActivity() as ProvideNavigation)
            .provideNavigation(BottomNavigation::class.java)
        var navController: NavController? = null

        // Bug with navigation library(need delay) - work around
        Handler(Looper.getMainLooper()).postDelayed({
            navController =
                view.findViewById<FragmentContainerView>(R.id.bottom_navigation_container)
                    .findNavController()

            navigation.init(navController!!)
        }, 1000)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.title) {
                getString(R.string.home) -> navController?.navigate(R.id.home_dest)
                getString(R.string.tv_shows) -> navController?.navigate(R.id.tv_shows_dest)
                getString(R.string.profile) -> navController?.navigate(R.id.profile_fragment)
                else -> throw IllegalArgumentException("Unknown item ${it.title}")
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}