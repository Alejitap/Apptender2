package com.example.apptender2.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apptender2.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth



class comentariosFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comentarios, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm = view.findViewById<BottomNavigationView>(R.id.buttonnavigation)
        btm.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.Home -> findNavController().navigate(R.id.action_comentariosFragment_to_home2Fragment)
                R.id.Perf -> findNavController().navigate(R.id.action_comentariosFragment_to_misdatosFragment)
                R.id.Map -> findNavController().navigate(R.id.action_comentariosFragment_to_mapasFragment)
                R.id.homeFragment -> findNavController().navigate(R.id.action_comentariosFragment_to_homeFragment)
                R.id.cerrar -> {
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_homeFragment_to_loginActivity)
                    true

                }

    }
            }
        }
    }