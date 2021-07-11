package kr.co.dooribon.ui.existingtrip.board.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentBoardBottomBinding
import kr.co.dooribon.ui.existingtrip.board.fragment.adapter.BoardAdapter
import kr.co.dooribon.ui.existingtrip.board.fragment.adapter.BoardListData

class CheckListFragment : Fragment() {

    private lateinit var binding: FragmentBoardBottomBinding
    private lateinit var dummyList: List<BoardListData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_board_bottom, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentDetails()
        setDummyList()
        setBoardAdapter()
        setBgVisibility()
        onAddBtnClickListener()
    }

    /* 추가하기 버튼 클릭 이벤트 처리 함수 */
    private fun onAddBtnClickListener() {
        binding.btAdd.setOnClickListener {
            // TODO view persentation할 때 여기 아래 들어가는 다이얼로그 바꿔줘야 함.
            val bsDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetTheme)
            val sheetView = LayoutInflater.from(requireContext()).inflate(
                R.layout.bottomsheet_add_board_list,
                requireActivity().findViewById(R.id.cl_add_board_bottom_sheet_root)
            )
            sheetView.apply {
                findViewById<Button>(R.id.btn_add_board_delete).setOnClickListener { // TODO 삭제하는 기능 추가해야함.
                    bsDialog.dismiss()
                }
                findViewById<Button>(R.id.btn_add_board_edit).setOnClickListener {
                    val editDlg = Dialog(requireContext())
                    editDlg.apply {
                        setContentView(R.layout.dialog_edit_travel)
                        findViewById<TextView>(R.id.tv_category).text = "체크리스트"
                        findViewById<TextView>(R.id.tv_category_detail).text = "준비는 철저하게! 필요한 것을 미리 체크하세요"
                        findViewById<EditText>(R.id.et_add_content).hint = "Ex. 미리 렌트카 예약"
                        findViewById<ImageView>(R.id.iv_category).setBackgroundResource(R.drawable.ic_icon_board_check_active)
                        findViewById<Button>(R.id.bt_edit_travel_cancel).setOnClickListener {
                            dismiss()
                        }
                        findViewById<Button>(R.id.bt_edit_travel_ok).setOnClickListener {
                            dismiss()
                        }
                        show()
                    }
                }
            }
            bsDialog.setContentView(sheetView)
            bsDialog.show()
        }
    }

    /***
     * 한 프래그먼트를 재활용해서 사용하기 때문에
     * 각 탭 레이아웃에 맞는 텍스트와 이미지를 넣어줘야 한다.
     */
    private fun setFragmentDetails() {
        binding.apply {
            tvTopic.text = getString(R.string.our_checklist)
            ivTopic.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_airplane
                )
            )
            tvMainTodo.text = getString(R.string.chk_first)
            tvSubTodo.text = getString(R.string.chk_first_detail)
        }
    }

    private fun setBgVisibility() {
        if (dummyList.isNotEmpty()) {
            binding.apply {
                ivTopic.visibility = View.GONE
                tvMainTodo.visibility = View.GONE
                tvSubTodo.visibility = View.GONE
            }
        }
    }

    private fun setBoardAdapter() {
        val boardAdapter = BoardAdapter()
        val boardRV = binding.rvTodoList
        boardAdapter.setItemList(dummyList)
        boardRV.adapter = boardAdapter
    }

    private fun setDummyList() {
        dummyList = listOf(
            BoardListData("먹을 것 챙기기", "김민영"),
            BoardListData("여권 챙기기", "김민영"),
            BoardListData("필름 카메라", "김민영"),
            BoardListData("여행사 전화하기", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영")
        )
    }
}