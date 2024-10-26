package com.example.dawpfinal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView

class actMainMenu:AppCompatActivity() {

    lateinit var nav: BottomNavigationView

    private val mOnNavMenu= BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.fragIntro -> {
                supportFragmentManager.commit {
                    replace<frag_intro>(R.id.framelay)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.fragServ -> {
                supportFragmentManager.commit {
                    replace<frag_servicios>(R.id.framelay)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.fragMap -> {
                supportFragmentManager.commit {
                    replace<frag_map>(R.id.framelay)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.fragCont -> {
                supportFragmentManager.commit {
                    replace<frag_contacto>(R.id.framelay)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_mainmenu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainmenu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nav=findViewById(R.id.navmenu)
        nav.setOnNavigationItemSelectedListener (mOnNavMenu)
        supportFragmentManager.commit {
            replace<frag_intro>(R.id.framelay)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }
}