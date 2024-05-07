package cr.ac.una.controlfinanciero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cr.ac.una.controlfinanciero.adapter.MovimientoAdapter
import cr.ac.una.controlfinanciero.controller.MovimientoController
import cr.ac.una.controlfinanciero.databinding.FragmentListaBinding
import cr.ac.una.controlfinanciero.entity.Movimiento
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaFragment : Fragment() {

//    var movimientoController: MovimientoController = MovimientoController()


    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!

    private lateinit var movimientos: List<Movimiento>

    private lateinit var vista: movimientoVista
    lateinit var adapter: MovimientoAdapter
    val movimientoController = MovimientoController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

                vista = ViewModelProvider(requireActivity()).get(movimientoVista::class.java)

                val listView = binding.listaMovimientos
                movimientos = mutableListOf<Movimiento>()

                adapter = MovimientoAdapter(
                    requireContext(),
                    vista.movimientos.value ?: mutableListOf(),
                    { movimiento ->
                        vista.deleteMovimiento(movimiento)
                    },
                    { movimiento ->
                        val action = ListaFragmentDirections.actionFirstFragmentToSecondFragment(movimiento)
                        findNavController().navigate(action)
                    }
                )

                listView.adapter = adapter

                vista.movimientos.observe(viewLifecycleOwner) { movimientos ->
                    adapter.updateList(movimientos)
                }

                binding.botonNuevo.setOnClickListener {
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                }
/*
        // Agregar lógica de lifecycleScope.launch
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                movimientoController.listMovimientos()
                val list = binding.listaMovimientos
                adapter = MovimientoAdapter(
                    requireContext(),
                    movimientoController.listMovimientos(),
                    onDeleteClickListener = { movimiento ->
                        vista.deleteMovimiento(movimiento)
                    },
                    onEditClickListener = { movimiento ->
                        val action = ListaFragmentDirections.actionFirstFragmentToSecondFragment(movimiento)
                        findNavController().navigate(action)
                    }
                )
                list.adapter = adapter
            }
        }*/


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}