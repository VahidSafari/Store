package com.example.store.features.dashboard.ui

enum class MainActivityFragmentType(val type: String) {
    PROFILE("fragment_profile"),
    SEARCH("fragment_search"),
    LIST("fragment_list"),
    DASHBOARD("fragment_dashboard");

    override fun toString(): String {
        return type
    }
}