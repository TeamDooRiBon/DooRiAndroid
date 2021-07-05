package kr.co.dooribon.ui.newtrip.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentParticipateJoinBinding

class ParticipateJoinFragment : Fragment() {
    private lateinit var binding : FragmentParticipateJoinBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParticipateJoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etCode1.setOnFocusChangeListener{ view, hasFocus ->
            if(hasFocus){
                binding.etCode1.setBackgroundResource(R.drawable.text_focus)
            }
            else {
                binding.etCode1.setBackgroundResource(R.drawable.text_round)
            }

        }
        binding.etCode2.setOnFocusChangeListener{ view, hasFocus ->
            if(hasFocus){
                binding.etCode2.setBackgroundResource(R.drawable.text_focus)
            }
            else {
                binding.etCode2.setBackgroundResource(R.drawable.text_round)
            }

        }
        binding.etCode3.setOnFocusChangeListener{ view, hasFocus ->
            if(hasFocus){
                binding.etCode3.setBackgroundResource(R.drawable.text_focus)
            }
            else {
                binding.etCode3.setBackgroundResource(R.drawable.text_round)
            }

        }
        binding.etCode4.setOnFocusChangeListener{ view, hasFocus ->
            if(hasFocus){
                binding.etCode4.setBackgroundResource(R.drawable.text_focus)
            }
            else {
                binding.etCode4.setBackgroundResource(R.drawable.text_round)
            }

        }
        binding.etCode5.setOnFocusChangeListener{ view, hasFocus ->
            if(hasFocus){
                binding.etCode5.setBackgroundResource(R.drawable.text_focus)
            }
            else {
                binding.etCode5.setBackgroundResource(R.drawable.text_round)
            }

        }
        binding.etCode6.setOnFocusChangeListener{ view, hasFocus ->
            if(hasFocus){
                binding.etCode6.setBackgroundResource(R.drawable.text_focus)
            }
            else {
                binding.etCode6.setBackgroundResource(R.drawable.text_round)
            }

        }
        forPutClickEvent()
    }
    private fun forPutClickEvent(){
        binding.btnParticipatePut.setOnClickListener {
            val code1 = binding.etCode1.text
            val code2 = binding.etCode2.text
            val code3 = binding.etCode3.text
            val code4 = binding.etCode4.text
            val code5 = binding.etCode5.text
            val code6 = binding.etCode6.text
            if (code1.isNullOrBlank() || code2.isNullOrBlank() || code3.isNullOrBlank() || code4.isNullOrBlank() || code5.isNullOrBlank() || code6.isNullOrBlank()){
            }
            else{
                // Todo : 6개 모두 입력했을 경우 버튼 색상 바꾸기
                val participatecheckFragment = ParticipateCheckFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.participate_fragment_container_view, participatecheckFragment).commitNow()
            }
        }
    }
}