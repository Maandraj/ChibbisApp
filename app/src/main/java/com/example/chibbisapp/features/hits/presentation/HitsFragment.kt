package com.example.chibbisapp.features.hits.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.chibbisapp.R
import com.example.chibbisapp.databinding.FragmentHitsBinding
import com.example.chibbisapp.features.hits.domain.model.Hit
import com.example.chibbisapp.features.hits.presentation.adapter.HitsAdapter
import dagger.Lazy
import javax.inject.Inject

class HitsFragment : Fragment(R.layout.fragment_hits) {
    @Inject
    lateinit var hitsVMFactory: Lazy<HitsVM.Factory>
    private val viewModel: HitsVM by viewModels {
        hitsVMFactory.get()
    }
    private var hitsAdapter: HitsAdapter? = null
    private val hitsData: MutableList<Hit> = mutableListOf()
    private val binding: FragmentHitsBinding by viewBinding(FragmentHitsBinding::bind)
    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<HitsComponentViewModel>().hitsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
    }

    private fun setUpRecyclerViewClickListener() {
        hitsAdapter?.hitOnClickListener = { hit, position ->
            Toast.makeText(requireContext(),
                "Хит ${hit.productName} позиция $position",
                Toast.LENGTH_SHORT).show()
        }
    }

    //TODO Сюда DiffUtils
    @SuppressLint("NotifyDataSetChanged")
    private fun setObservables() {
        viewModel.hits.observe(viewLifecycleOwner) { hits ->
            if (hits == hitsData)
                return@observe
            hitsData.clear()
            hitsData.addAll(hits)
            binding.includeNotFound.root.visibility =
                if (hitsData.isNotEmpty()) View.GONE else View.VISIBLE
            if (hitsAdapter == null) {
                hitsAdapter = HitsAdapter(hitsData)
                binding.rvHits.layoutManager = LinearLayoutManager(requireContext())
                binding.rvHits.adapter = hitsAdapter
                setUpRecyclerViewClickListener()
            } else {
                hitsAdapter?.notifyDataSetChanged()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.includeLoading.root.visibility = if (loading) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Log.e(TAG, error.exception.message.toString())
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        hitsAdapter = null
    }
}

private const val TAG = "HitsFragment"