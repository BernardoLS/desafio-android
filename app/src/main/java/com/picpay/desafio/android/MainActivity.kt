package com.picpay.desafio.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.user.presentation.view.UserListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, UserListFragment.newInstance())
            transaction.commit()
        }
    }
}
