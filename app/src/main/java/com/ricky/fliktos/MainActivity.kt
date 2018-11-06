package com.ricky.fliktos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ricky.fliktos.model.Item

class MainActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: Item?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment = ItemFragment.newInstance(1)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }
}
