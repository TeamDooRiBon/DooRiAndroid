package kr.co.dooribon.ui.existingtrip.tendency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.dooribon.databinding.FragmentTendencyBinding
import kr.co.dooribon.ui.existingtrip.extension.initializeTab
import kr.co.dooribon.ui.existingtrip.tendency.extension.initializeTendencyNavigation
import kr.co.dooribon.utils.AutoClearBinding

class TendencyFragment : Fragment() {

    private var binding by AutoClearBinding<FragmentTendencyBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTendencyBinding.inflate(inflater, container, false)
        .also { FragmentTendencyBinding ->
            binding = FragmentTendencyBinding
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureTabNavigation()
    }

    private fun configureTabNavigation() {
        binding.tabTendency.initializeTab(listOf("우리들", "살펴보기"))
        binding.tabTendency.initializeTendencyNavigation(childFragmentManager)
    }
}