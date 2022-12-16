package com.vitor.coursewsclient;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vitor.coursewsclient.model.Course;

public class CourseWSClient {

	private static final String COURSES_URI = "/courses";
	private static final String SERVICE_URL = "http://localhost:8080/coursews/services/courseservice";
	
	public static void main(String[] args) {
		/*
		 * JAX-RS Client API
		 * 
		 * ClientBuilder -> Create a Client
		 * Client -> Comunication channel
		 * WebTarget -> Unique Rest Resource
		 * Invoke.Builder -> Call methods POST, GET, PUT etc
		 * Entity -> Represents returned Data 
		 * 
		 */
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(SERVICE_URL).path(COURSES_URI);
		
		/** Create new Course */
		Course newCourse = new Course();
		newCourse.setName("Angular");
		newCourse.setPrice(27.90);
		newCourse.setTaughtBy("Vitor Pastuch Lazarotto");
		newCourse.setRating(5);
		
		Course courseCreated = target.request()
				.post(Entity.entity(newCourse, MediaType.APPLICATION_XML), 
						Course.class);
		
		System.out.println("Curso criado com id: " + courseCreated.getId());
		
		
		/** Update a Course */
		Course updateCourse = new Course();
		updateCourse.setId(1);
		updateCourse.setName("Angular 8 Essentials");
		updateCourse.setTaughtBy("Vitor Pastuch");
		updateCourse.setPrice(45);
		
		Response updateResponse = target.request()
				.put(Entity.entity(updateCourse, MediaType.APPLICATION_XML));
		
		System.out.println("Update status: " + updateResponse.getStatus());
		updateResponse.close();
		
		
		/** Get a Course by ID */
		target = target.path("/{id}");
		target = target.resolveTemplate("id", 1);
		
		Course course = target.request().get(Course.class);
		System.out.println("O professor do curso é: " + course.getTaughtBy());
		
		
		/** List all courses */
		WebTarget listTarget = client.target(SERVICE_URL).path(COURSES_URI);
		Builder requestList = listTarget.request();
		
		List<Course> courses = requestList.get(new GenericType<List<Course>>() {});;

		courses.forEach(cur -> {
			System.out.println(cur.getName()+" Price: "+cur.getPrice());
		});
		
		/** Delete a course by id */
		WebTarget delTarget = client.target(SERVICE_URL).path(COURSES_URI);
		delTarget = delTarget.path("/{id}");
		delTarget = delTarget.resolveTemplate("id", 2);
		
		Response deleteResponse = delTarget.request().delete();
		
		System.out.println("Status of delete id 2 is: "+ deleteResponse.getStatus());
		
		/** close the client */
		client.close();
	}
}
