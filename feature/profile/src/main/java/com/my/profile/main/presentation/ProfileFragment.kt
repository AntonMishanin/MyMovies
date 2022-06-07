package com.my.profile.main.presentation

import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.my.core.di.DependenciesProvider
import com.my.profile.R
import com.my.profile.databinding.FragmentProfileBinding
import com.my.profile.di.ProfileDependencies
import com.my.profile.di.ProfileDiContainer
import com.my.profile.main.domain.entity.ContentViewType
import com.my.profile.main.domain.entity.ProfileFeature
import com.my.profile.main.domain.entity.TitleViewType
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

private const val GO_TO_NEXT_LINE = "\n"
private const val SPAN_PROPORTION = 2f
private const val SPAN_START = 0
private const val SPAN_FLAGS = 0

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        viewModels<ProfileDiContainer>().value.getComponent(
            (requireActivity().application as DependenciesProvider).provide(ProfileDependencies::class)
        ).provideProfileViewModel()
    }

    private var profilePageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            viewModel.onPageSelected(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribeToObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.doppelgangerViewPager.unregisterOnPageChangeCallback(profilePageChangeCallback)
        _binding = null
    }

    private fun initView() {
        Picasso.get()
            .load(R.drawable.ic_avatar)
            .transform(CropCircleTransformation())
            .placeholder(R.drawable.ic_avatar)
            .into(binding.avatar)

        binding.doppelgangerViewPager.registerOnPageChangeCallback(profilePageChangeCallback)
    }

    private fun subscribeToObservers() {
        viewModel.features.observe(viewLifecycleOwner, ::handleFeatures)
    }

    private fun handleFeatures(features: List<ProfileFeature>) {
        val profileAdapter = ProfileAdapter(fragment = this, features = features)
        binding.doppelgangerViewPager.adapter = profileAdapter
        TabLayoutMediator(binding.tabLayout, binding.doppelgangerViewPager) { tab, position ->
            tab.text = getFeatureTitle(features[position])
        }.attach()
    }

    private fun getFeatureTitle(feature: ProfileFeature): SpannableString {
        val title = getTitleByContentViewType(feature.contentViewType)
        return when (feature.titleViewType) {
            is TitleViewType.WithQuantity -> {
                getTitleWithQuantity(feature.titleViewType.quantity, title)
            }
            is TitleViewType.OnlyTitle -> SpannableString(title)
        }
    }

    private fun getTitleByContentViewType(contentViewType: ContentViewType): String {
        return when (contentViewType) {
            ContentViewType.FAVORITE -> resources.getString(R.string.favorite)
            ContentViewType.WATCHLIST -> resources.getString(R.string.later)
        }
    }

    private fun getTitleWithQuantity(quantity: String, title: String): SpannableString {
        val fullTitle = quantity + GO_TO_NEXT_LINE + title
        val spannableStringTitle = SpannableString(fullTitle)
        val relativeSizeSpan = RelativeSizeSpan(SPAN_PROPORTION)
        spannableStringTitle.setSpan(relativeSizeSpan, SPAN_START, quantity.count(), SPAN_FLAGS)
        return spannableStringTitle
    }
}