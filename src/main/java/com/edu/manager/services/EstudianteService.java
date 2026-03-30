package com.edu.manager.services;

import com.edu.manager.models.Estudiante;
import com.edu.manager.repositories.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los
 * estudiantes.
 * <p>
 * Proporciona operaciones para listar, guardar, buscar y eliminar estudiantes
 * de la base de datos.
 * </p>
 * 
 * Autor: Kevin
 */
@Service
public class EstudianteService {

	private final EstudianteRepository estudianteRepository;

	/**
	 * Constructor de EstudianteService.
	 * 
	 * @param estudianteRepository repositorio para operaciones CRUD de estudiantes
	 */
	public EstudianteService(EstudianteRepository estudianteRepository) {
		this.estudianteRepository = estudianteRepository;
	}

	/**
	 * Obtiene la lista completa de estudiantes.
	 *
	 * @return lista de todos los estudiantes registrados
	 */
	public List<Estudiante> listarTodos() {
		return estudianteRepository.findAll();
	}

	/**
	 * Guarda o actualiza un estudiante en la base de datos.
	 *
	 * @param estudiante objeto estudiante a persistir
	 */
	public void guardar(Estudiante estudiante) {
		estudianteRepository.save(estudiante);
	}

	/**
	 * Busca un estudiante por su identificador.
	 *
	 * @param id identificador del estudiante
	 * @return el estudiante si existe, o null si no se encuentra
	 */
	public Estudiante buscarPorId(Long id) {
		return estudianteRepository.findById(id).orElse(null);
	}

	/**
	 * Elimina un estudiante por su identificador.
	 * <p>
	 * Esta operación elimina directamente el registro de la base de datos sin
	 * verificar dependencias. Se recomienda usar con precaución.
	 * </p>
	 *
	 * @param id identificador del estudiante a eliminar
	 */
	public void eliminar(Long id) {
		estudianteRepository.deleteById(id);
	}
}