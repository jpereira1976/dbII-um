package uy.edu.um.db;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ENGINE")
public class Engine {
	@Id
	@Column(name="ENGINE_ID")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	private String make;
	private String model;
	private String size;
}
