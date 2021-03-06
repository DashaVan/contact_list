package spacekotlin.vaniukova.fragmentscontactlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import spacekotlin.vaniukova.fragmentscontactlist.ContactAdapter
import spacekotlin.vaniukova.fragmentscontactlist.ContactsList
import spacekotlin.vaniukova.fragmentscontactlist.Navigator
import spacekotlin.vaniukova.fragmentscontactlist.R
import spacekotlin.vaniukova.fragmentscontactlist.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val contacts = ContactsList.list

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
        with(binding.contactList) {
            adapter = ContactAdapter(contacts) { id -> openDetailFragment(id) }
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun openDetailFragment(id: Long) {
        if (isTableMode()) {
            (activity as Navigator).navigateToTabletDetail(
                DetailFragment.newInstance(id),
                "detailFragment"
            )
        } else {
            (activity as Navigator).navigateTo(DetailFragment.newInstance(id), "detailFragment")
        }
    }

    private fun isTableMode() = context?.resources?.configuration?.smallestScreenWidthDp!! >= 600
}