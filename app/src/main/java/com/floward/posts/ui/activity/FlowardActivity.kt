package com.floward.posts.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.floward.posts.R

/**
 * Entry point to the app. This is a single activity app as recommended by the Android Jetpack Navigation
 * component documentation.
 */
class FlowardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.floward_activity)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.floward_posts_nav_graph)
        navGraph.setStartDestination(R.id.viewUsersFragment)
        val navController = navHostFragment.navController
        navController.setGraph(navGraph, intent.extras)
    }
}
