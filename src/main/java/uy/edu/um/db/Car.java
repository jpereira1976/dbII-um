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
@Entity(name="CAR_LIST_ANN")
@Table(name="CAR_LIST_ANN")
@AllArgsConstructor
public class Car {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="CAR_ID")
	Integer id;
	String name;
	String color;
}
