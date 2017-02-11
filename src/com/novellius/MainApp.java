package com.novellius;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.novellius.dao.AdminDao;
import com.novellius.pojo.Admin;

public class MainApp {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");

		AdminDao adminDao = (AdminDao) applicationContext.getBean("adminDao");

		Timestamp ts = new Timestamp(new Date().getTime());

		// Admin admin = new Admin();
		// admin.setCargo("barrendero");
		// admin.setNombre("juan");
		// admin.setFechaCreacion(ts);

		try {
			
			//8.- insertar de forma masiva
			List<Admin> admins = new ArrayList<Admin>();
			admins.add(new Admin("pedro", "jefe de ingenieria", ts));
			admins.add(new Admin("jorge", "subjefe de ingenieria", ts));
			admins.add(new Admin("maria", "representante legal", ts));
			
			int[] vals = adminDao.saveAll(admins);
			int temporal = 0;
			
			for (int i : vals) {
				System.out.println("filas afectadas para la operacion: " + i);
				temporal = temporal + 1;
			}
			
			System.out.println("cantidad de filas insertadas: " + temporal);

			//1.-guardar
			// adminDao.save(admin);
			//
			//2.- listar
			// List<Admin> admins = adminDao.findAll();
			//
			// for (Admin admin2 : admins) {
			// System.out.println(admin2);
			// }
			//
			//3.- Buscar por id
			// System.out.println(adminDao.findById(1));
			//
			//4.- Buscar por nombre
			// System.out.println(adminDao.findByNombre("p").toString());
			//
			//5.- buscar por id
			// Admin admin = adminDao.findById(10);
			// System.out.println("Admin con id 1: " + admin);
			// admin.setCargo("subgerente");
			// admin.setNombre("martin");
			//
			//6.- actualizar
			// if (adminDao.update(admin)) {
			// System.out.println("Actualizado correctamente: " + admin);
			// }
			//
			//7.- Eliminar
			// if (adminDao.delete(admin.getIdAd())) {
			// System.out.println("Admin: " + admin.getNombre() + " eliminado
			// correctamente");
			// }

		} catch (CannotGetJdbcConnectionException ex) {
			ex.printStackTrace();
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}

		((ClassPathXmlApplicationContext) applicationContext).close();

	}

}
