package com.my.profile.di

import androidx.lifecycle.ViewModel

internal class ProfileDiContainer : ViewModel() {

    private var component: ProfileComponent? = null

    fun getComponent(dependencies: ProfileDependencies): ProfileComponent {
        if (component == null) {
            component = DaggerProfileComponent.builder().dependencies(dependencies).build()
        }
        return component!!
    }
}