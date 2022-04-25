package com.proyectointegrador.proyecto.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "image", unique = false, nullable = false, length = 10485760
	)
	private byte[] image;

	@OneToMany(targetEntity = Post.class, mappedBy = "image")
	private List<Post> posts;

	public Image(Long id,String name, String type, byte[] image) {
		this.id=id;
		this.name = name;
		this.type = type;
		this.image = image;
	}
	public Image(String name, String type, byte[] image) {
		this.name = name;
		this.type = type;
		this.image = image;
	}

}